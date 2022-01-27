/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.totp

import java.security.Key
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Base32
import uk.gov.hmrc.totp.TotpGenerator.{args, getTotpCode}

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId, ZonedDateTime}
import scala.concurrent.duration._
import scala.math._

sealed trait CryptoAlgorithm
case object HmacSHA512 extends CryptoAlgorithm
case object HmacSHA1 extends CryptoAlgorithm

trait HmacShaTotp {

  def totpTimeInterval: FiniteDuration
  def codeLength : Int
  def crypto: CryptoAlgorithm

  final def getTotpCode(secret: String, dateNowInMillis: Long): String = {

    getTotp(secret, dateNowInMillis)
  }

  final def getTotp(secret: String, totpGenerationTimeInMillis: Long): String = {
    val timeWindow = totpGenerationTimeInMillis / totpTimeInterval.toMillis

    val msg: Array[Byte] = BigInt(timeWindow).toByteArray.reverse.padTo(8, 0.toByte).reverse

    val hash = hmacSha(crypto.toString, new Base32().decode(secret), msg)
    val offset: Int = hash(hash.length - 1) & 0xf
    val binary: Long = ((hash(offset) & 0x7f) << 24) |
      ((hash(offset + 1) & 0xff) << 16) |
      ((hash(offset + 2) & 0xff) << 8 |
        (hash(offset + 3) & 0xff))

    val otp: Long = binary % pow(10, codeLength).toLong

    ("0" * codeLength + otp.toString).takeRight(codeLength)
  }

  private def hmacSha(crypto: String, keyBytes: Array[Byte], text: Array[Byte]): Array[Byte] = {
    val hmac: Mac = Mac.getInstance(crypto)
    val macKey: Key = new SecretKeySpec(keyBytes, "RAW")
    hmac.init(macKey)
    hmac.doFinal(text)
  }

}


trait TotpSha512 extends HmacShaTotp {
  final override val totpTimeInterval = 30.seconds
  final override val codeLength = 8
  final override val crypto = HmacSHA512
}

trait TotpSha1 extends HmacShaTotp {
  final override val totpTimeInterval = 30.seconds
  final override val codeLength = 6
  final override val crypto = HmacSHA1
}

trait Totp extends TotpSha512

object TotpGenerator extends Totp with App {
  if (args.length < 1) println("Secret is missing.")
  else {
    val dateNowInMillis = System.currentTimeMillis()
    val instant = Instant.ofEpochMilli(dateNowInMillis)

    val zonedDateTimeUtc = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"))
    val dateTimeFormatter2 = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val zonedDateTimeUtcString = dateTimeFormatter2.format(zonedDateTimeUtc)
    println(s"TOTP for $zonedDateTimeUtcString: " + getTotpCode(args(0), dateNowInMillis))

    // specific date date
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val dateInMillis = format.parse("2021-06-01 00:00:00.000").toInstant().toEpochMilli()
    println(s"TOTP for 2021-06-01T00:00:00.000: " + getTotpCode(args(0), dateInMillis))

  }
}

object TotpSha1Generator extends TotpSha1 with App {
  if (args.length < 1) println("Secret is missing.")
  else {
    val dateNowInMillis = System.currentTimeMillis()

    val instant = Instant.ofEpochMilli(dateNowInMillis)

    val zonedDateTimeUtc = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"))
    val dateTimeFormatter2 = DateTimeFormatter.ISO_ZONED_DATE_TIME

    val zonedDateTimeUtcString = dateTimeFormatter2.format(zonedDateTimeUtc)

    //zonedDateTimeUtc: java.time.ZonedDateTime = 2017-02-13T12:14:20.666Z[UTC]
    println(s"TOTP for $zonedDateTimeUtcString: " + getTotpCode(args(0), dateNowInMillis))

    // specific date date
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val dateInMillis = format.parse("2021-06-01 00:00:00.000").toInstant().toEpochMilli()
    println(s"TOTP for 2021-06-01T00:00:00.000: " + getTotpCode(args(0), dateInMillis))
  }
}

