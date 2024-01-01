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
 *
 *
 */

package com.wa9nnn.wa9nnnutil

import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId, ZonedDateTime}
import java.util.TimeZone
import scala.language.implicitConversions

object TimeConverters {
  implicit val zoneIdUtc: ZoneId = ZoneId.of("UTC")

  def nanoToSecond(nanoseconds: Double): Double = nanoseconds / 1000000000.0


  /**
   * @param in value from instant.toString
   * @return corresponding Instant
   */
  given Conversion[String, Instant] = (in: String) => Instant.parse(in)


  private val _fileStamp = DateTimeFormatter.ofPattern("YYYY-MM-dd HHmmssz")

  def fileStamp(in: Instant = Instant.now()): String = {
    _fileStamp.format(ZonedDateTime.ofInstant(in, ZoneId.of("UTC")))
  }

  val fmt: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm z")
  val timeFmt: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm z")

  /**
   * UTC from [[Instant]] with local time
   *
   * @param instant any time since 1970
   * @param zoneId  what to consider local.
   * @return
   */
  def instantDisplayUTCLocal(instant: Instant, zoneId: ZoneId = TimeZone.getDefault.toZoneId): String = {
    val sUtc = fmt.format(ZonedDateTime.ofInstant(instant, ZoneId.of("UTC")))
    val local = timeFmt.format(ZonedDateTime.ofInstant(instant, zoneId))

    s"$sUtc ($local)"
  }

  implicit def local(instant: Instant): String = {
    if (instant == Instant.EPOCH)
      "--"
    else
      instantDisplayUTCLocal(instant)
  }

}
