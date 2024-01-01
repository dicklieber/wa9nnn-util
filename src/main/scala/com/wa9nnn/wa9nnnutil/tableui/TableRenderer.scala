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

package com.wa9nnn.wa9nnnutil.tableui

import com.wa9nnn.wa9nnnutil.tableui.html.renderTable
import org.jsoup.select.Elements

object TableRenderer {
  def apply(table: Table): String = {
    val appendable = renderTable(table)

    import org.jsoup.Jsoup
    val document = Jsoup.parse(appendable.body)

    val elements: Elements = document.getElementsByTag("table")
    val element = elements.first()
    val nodes = document.childNode(0).childNode(0)
    val html = element.toString
    html
  }
}
