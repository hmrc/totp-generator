/*
 * Copyright 2016 HM Revenue & Customs
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

  describe("A TOTP generator") {
    it("should generate a different TOTP code for each 30 seconds window") {

      val date1 = dateFormat.parse("2016-12-12 09:45:00.001").getTime
      val date2 = dateFormat.parse("2016-12-12 09:45:29.999").getTime

      val date3 = dateFormat.parse("2016-12-12 09:45:30.000").getTime
      val date4 = dateFormat.parse("2016-12-12 09:45:59.999").getTime

      val totpCode1 = TotpGenerator.getTotp(secret, date1)
      val totpCode2 = TotpGenerator.getTotp(secret, date2)

      val totpCode3 = TotpGenerator.getTotp(secret, date3)
      val totpCode4 = TotpGenerator.getTotp(secret, date4)

      assert(totpCode1 == totpCode2)
      assert(totpCode2 != totpCode3)
      assert(totpCode3 == totpCode4)
    }
  }

}
