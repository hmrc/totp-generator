/*
 * Copyright 2018 HM Revenue & Customs
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

import org.scalatest.FunSpec

class TotpGeneratorSpec extends FunSpec {

  private val secret: String = "-TOTP-SECRET-12345-"

  private val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

  private val window1_time1 = dateFormat.parse("2016-12-12 09:45:00.001").getTime
  private val window1_time2 = dateFormat.parse("2016-12-12 09:45:29.999").getTime

  private val window2_time1 = dateFormat.parse("2016-12-12 09:45:30.000").getTime
  private val window2_time2 = dateFormat.parse("2016-12-12 09:45:59.999").getTime

  val totpCode11 = TotpGenerator.getTotp(secret, window1_time1)
  val totpCode12 = TotpGenerator.getTotp(secret, window1_time2)

  val totpCode21 = TotpGenerator.getTotp(secret, window2_time1)
  val totpCode22 = TotpGenerator.getTotp(secret, window2_time2)

  describe("TOTP generator") {
    it("should generate a different TOTP code for each 30 seconds window") {
      assert(totpCode11 != totpCode21)
      assert(totpCode12 != totpCode21)

      assert(totpCode11 != totpCode22)
      assert(totpCode12 != totpCode22)
    }

    it("should generate the same TOTP code in the same 30 seconds window") {
      assert(totpCode11 == totpCode12)
      assert(totpCode22 == totpCode22)
    }
  }

}
