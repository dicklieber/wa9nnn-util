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

package com.wa9nnn.wa9nnnutil

import com.wa9nnn.wa9nnnutil.DurationHelpers.between

import java.time.Instant


/**
 *
 * @param rangeValues ordered
 */
class AgeColor(rangeValues: Seq[(Int, String)] = AgeColor.defaultMapping) {
  val segments: Seq[(Int, String)] = rangeValues.sortBy(_._1)

  def apply(secondsOld: Int): String = {
    segments.find(rv ⇒ secondsOld < rv._1).getOrElse(segments.last)._2
  }

  def apply(instant: Instant): String = {
    val ageInSeconds: Int = between(instant, Instant.now).toSeconds.toInt
    if (ageInSeconds < 0) {
      segments.head._2
    }
    else {
      apply(ageInSeconds)
    }
  }
}

object AgeColor {
  lazy val defaultAgeColor: AgeColor = new AgeColor()

  def apply(rangeValues: Seq[(Int, String)] = defaultMapping): AgeColor = new AgeColor(rangeValues)

  private val prefix: String = "age"
  /**
   * Mapping seconds old to css classes.
   */
  val defaultMapping: Seq[(Int, String)] = Seq[(Int, String)](
    50 → s"${prefix}Current",
    100 → s"${prefix}NotCurrent",
    180 → s"${prefix}Old",
    240 → s"${prefix}Older",
    0x7fffffff → s"${prefix}Max"
  )

}
