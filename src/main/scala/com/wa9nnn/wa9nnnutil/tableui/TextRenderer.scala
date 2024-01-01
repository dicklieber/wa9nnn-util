/*
 * Copyright (C) @year  Dick Lieber, WA9NNN
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

package com.wa9nnn.wa9nnnutil.tableui

import scala.collection.concurrent.TrieMap

object TextRenderer {

  class Max {
    var v = 0

    def apply(s: String): Unit = apply(s.length)

    def apply(n: Int): Unit = {
      v = Math.max(v, n)
    }
  }


  def apply(rows: Seq[Row]): String = {
    val maxColWidths = new TrieMap[Int, Max]
    rows.map { row =>
      row.cells.zipWithIndex.map { case (cell, i) =>
        maxColWidths.getOrElseUpdate(i, new Max())
          .apply(cell.value)
      }
    }

    val s = rows.map { row =>
      row.cells.zipWithIndex.map { case (cell, i) =>
        val maxCell = maxColWidths(i).v
        val value: String = cell.value
        value.padTo(maxCell, ' ')
      }.mkString("|")
    }

    s.map(s => s"|$s|")
  }.mkString("\n")
}
