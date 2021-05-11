/*
 * Copyright (C) 2021 Dick Lieber, WA9NNN
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

package com.wa9nnn.util

import org.specs2.matcher.DataTables
import org.specs2.mutable.Specification

import java.time.Instant

class AgeColorSpec extends Specification with DataTables {

  val ageColor: AgeColor = AgeColor()

  "AgeColorSpecs" should {
    "Int to Color" in {
      "Int" | "Style" |
        -1 !  "ageCurrent" |
        0 !   "ageCurrent" |
        179 ! "ageOld" |
        180 ! "ageOlder" |
        250 ! "ageMax" |
        999999 ! "ageMax" |> { (age, color) =>
        ageColor(age) must be equalTo color
      }
    }
    "Instants" >> {
      ageColor(Instant.ofEpochMilli(100)) must be equalTo "ageMax"
    }
    "Instant ended in past!" >> {
      ageColor(Instant.now().plusSeconds(4)) must be equalTo "ageCurrent"
    }
  }
}
