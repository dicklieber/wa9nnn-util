/*
 * Copyright (C) 2022 Dick Lieber, WA9NNN
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.wa9nnn.wa9nnnutil


import scala.None
import String2Option.given

class String2OptionTest extends UtilSpec {

  "String2Option" should {
    "nonEmpty" in {
      val o: Option[String] = "CQCQ"
      o must equal(Some("CQCQ"))
    }
    "empty" in {
      val o: Option[String] = ""
      o must equal (None)
    }
    "null" in {

     val o: Option[String] = null.asInstanceOf[String]
      o must equal (None)
    }
  }
}
