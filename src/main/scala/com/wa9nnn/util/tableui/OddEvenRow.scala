package com.wa9nnn.util.tableui

import OddEvenRow._
import com.wa9nnn.util.CssClass

/**
  * Generates a css class that indicates if a row is odd or even.
  */
class OddEvenRow {
  private var row = 1

  def nextRowClass: String = {
    val result = row & 1 match {
      case 0 => even
      case d => "odd"
    }
    row = row + 1
    result
  }

  def nextRowCssClass: CssClass = {
    val result = row & 1 match {
      case 0 => Even
      case d => Odd
    }
    row = row + 1
    result
  }
}

object OddEvenRow {
  val odd = "odd"
  val even = "even"

  case object Odd extends CssClass {
    override def cssClass: String = odd
  }

  case object Even extends CssClass {
    override def cssClass: String = even
  }

}

