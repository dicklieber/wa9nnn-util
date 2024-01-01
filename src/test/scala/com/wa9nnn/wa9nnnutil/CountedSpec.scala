
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

package com.wa9nnn.wa9nnnutil

import com.wa9nnn.wa9nnnutil.tableui.Row


class CountedSpec extends UtilSpec {

  "Counted" should {
    "empty" in {
      val counted = new Counted[Int]
      val result: CountedThings[Int] = counted.result
      result.rows must have length 0
    }

    "apply" in {
      val counted = new Counted[Int]
      counted(42)
      counted.result.rows must have length 1
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
      firstBatch.result.size must equal (7)

      val rows: Seq[Row] = firstBatch.result.rows
      rows must have length (4)
      rows.head.cells.head.value must equal("CW")
      rows.head.cells(1).value must equal("1")

      rows(3).cells.head.value must equal("USB")
      rows(3).cells(1).value must equal("4")

    }
  }
}
