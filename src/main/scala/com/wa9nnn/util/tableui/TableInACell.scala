package com.wa9nnn.util.tableui

import play.twirl.api.Html

/**
  * Render a [[Table]] in a [[Cell]]
  */
object TableInACell {
  def apply(uiTable: Table):Cell = {
    val render: Html = com.wa9nnn.util.tableui.html.renderTable.render(uiTable)
    val t = render.body
    Cell.rawHtml(t)
  }
}
