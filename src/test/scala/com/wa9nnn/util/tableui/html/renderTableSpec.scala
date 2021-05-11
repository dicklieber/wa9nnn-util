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

package com.wa9nnn.util.tableui.html

import com.wa9nnn.util.tableui.Header.singleRow
import com.wa9nnn.util.tableui.{Cell, Header, Row, Table, TableRenderer}
import org.specs2.mutable.Specification

import java.nio.file.{Files, Paths}
import java.time.Instant

class renderTableSpec extends Specification {

  "renderTableSpec" should {
    "render" in {
      import com.wa9nnn.util.tableui.Header.s2cell
      val header = Header("Overall", "Alpha", "Beta", "Charlie")

      val rows = Seq(
        Row("Row1", "a string", Cell(Instant.EPOCH))
      )

      val table = Table(header, rows)

      val html = TableRenderer(table)

      val path = Paths.get("renderhtml.html")
      Files.writeString(path, html)
      html must beEqualTo("""""")
    }
  }
}
