/*
 * Copyright 2020 HM Revenue & Customs
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

import java.text.SimpleDateFormat

import org.scalatest.{FunSpec, Matchers}

class TotpGeneratorSpec extends FunSpec with Matchers {

  private val secret: String = "-TOTP-SECRET-12345-"

  private val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

  private val window1_time1 = dateFormat.parse("2016-12-12 09:45:00.001").getTime
  private val window1_time2 = dateFormat.parse("2016-12-12 09:45:29.999").getTime
  private val window2_time1 = dateFormat.parse("2016-12-12 09:45:30.000").getTime
  private val window2_time2 = dateFormat.parse("2016-12-12 09:45:59.999").getTime

  private val window1_totpCode1 = TotpGenerator.getTotp(secret, window1_time1)
  private val window1_totpCode2 = TotpGenerator.getTotp(secret, window1_time2)
  private val window2_totpCode1 = TotpGenerator.getTotp(secret, window2_time1)
  private val window2_totpCode2 = TotpGenerator.getTotp(secret, window2_time2)

  describe("TOTP generator") {
    it("should generate a different TOTP code for each 30 seconds window") {
      window1_totpCode1 should not equal window2_totpCode1
      window1_totpCode1 should not equal window2_totpCode2

      window1_totpCode2 should not equal window2_totpCode1
      window1_totpCode2 should not equal window2_totpCode2
    }

    it("should generate the same TOTP code in the same 30 seconds window") {
      window1_totpCode1 shouldBe window1_totpCode2
      window2_totpCode1 shouldBe window2_totpCode2
    }
  }

  describe("TOTP generator using SHA1") {

    val sha1_totpCode = TotpSha1Generator.getTotp(secret, window1_time1)
    val sha512_totpCode = TotpGenerator.getTotp(secret, window1_time1)

    it("should generate a different TOTP code than SHA512") {
      sha1_totpCode should not equal sha512_totpCode
    }
  }
}
