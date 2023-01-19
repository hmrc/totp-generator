/*
 * Copyright 2023 HM Revenue & Customs
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
import scala.concurrent.duration._
import scala.math._

import org.apache.commons.codec.binary.Base32

sealed trait CryptoAlgorithm
case object HmacSHA512 extends CryptoAlgorithm
case object HmacSHA1   extends CryptoAlgorithm

trait HmacShaTotp {

  def totpTimeInterval: FiniteDuration
  def codeLength: Int
  def crypto: CryptoAlgorithm

  final def getTotpCode(secret: String): String = {
    getTotp(secret, System.currentTimeMillis())
  }

  final def getTotp(secret: String, totpGenerationTimeInMillis: Long): String = {
    val timeWindow = totpGenerationTimeInMillis / totpTimeInterval.toMillis

    val msg: Array[Byte] = BigInt(timeWindow).toByteArray.reverse.padTo(8, 0.toByte).reverse

    val hash         = hmacSha(crypto.toString, new Base32().decode(secret), msg)
    val offset: Int  = hash(hash.length - 1) & 0xf
    val binary: Long = ((hash(offset) & 0x7f) << 24) |
      ((hash(offset + 1) & 0xff) << 16) |
      ((hash(offset + 2) & 0xff) << 8 |
        (hash(offset + 3) & 0xff))

    val otp: Long = binary % pow(10, codeLength).toLong

    ("0" * codeLength + otp.toString).takeRight(codeLength)
  }

  private def hmacSha(crypto: String, keyBytes: Array[Byte], text: Array[Byte]): Array[Byte] = {
    val hmac: Mac   = Mac.getInstance(crypto)
    val macKey: Key = new SecretKeySpec(keyBytes, "RAW")
    hmac.init(macKey)
    hmac.doFinal(text)
  }

}

trait TotpSha512 extends HmacShaTotp {
  final override val totpTimeInterval = 30.seconds
  final override val codeLength       = 8
  final override val crypto           = HmacSHA512
}

trait TotpSha1 extends HmacShaTotp {
  final override val totpTimeInterval = 30.seconds
  final override val codeLength       = 6
  final override val crypto           = HmacSHA1
}

trait Totp extends TotpSha512

object TotpGenerator extends Totp with App {
  if (args.length < 1) println("Secret is missing.")
  else println("TOTP: " + getTotpCode(args(0)))
}

object TotpSha1Generator extends TotpSha1 with App {
  if (args.length < 1) println("Secret is missing.")
  else println("TOTP: " + getTotpCode(args(0)))
}
