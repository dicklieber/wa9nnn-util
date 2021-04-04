
/*
 * Copyright (C) ${year} Dick Lieber, WA9NNN
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

import org.specs2.mutable.Specification

class CountedSpec extends Specification {

  "Counted" should {
    "empty" in {
      val counted = new Counted[Int]
      val result: CountedThings[Int] = counted.result
      result.size must beEqualTo (0)
    }

    "apply" in {
      val counted = new Counted[Int]
      counted(42)
      counted.result.size must beEqualTo (1)
    }

    "rollup" in {
      val firstBatch = new Counted[String]
      firstBatch.apply("Hello")
      val strings = new Counted[String]
      strings("CW")
      strings("FT-8")
      strings("USB")
      strings("USB")
      strings("USB")
      strings("USB")

      firstBatch(strings.result)
      firstBatch.result.size must beEqualTo (7)

      val rows = firstBatch.result.rows
      rows must haveSize(4)
      rows.head.cells.head.value must beEqualTo ("CW")
      rows.head.cells(1).value must beEqualTo ("1")

      rows(3).cells.head.value must beEqualTo ("USB")
      rows(3).cells(1).value must beEqualTo ("4")

    }
  }
}
