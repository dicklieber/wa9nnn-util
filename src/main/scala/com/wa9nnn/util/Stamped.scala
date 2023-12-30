/*
 * Copyright (C) 2022 Dick Lieber, WA9NNN
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


import com.wa9nnn.util.DurationHelpers.{between, given}

import java.time.{Clock, Instant}
import scala.concurrent.duration.Duration

/**
 * A class that has a timestamp.
 */
trait Stamped {
  val stamp: Instant = Instant.now

  /**
   *
   * @param clock provide for unit tests.
   * @return how old this object is.
   */
  def age(clock: Clock = Clock.systemUTC()): String =
    duration

  def duration: Duration = between(stamp)

  /**
   *
   * @param duration compare to this.
   * @param clock    for unit tests
   * @return if the object is older then the passed in duration.
   */
  def olderThan(duration: Duration, clock: Clock = Clock.systemUTC()): Boolean = {
    between(stamp, Instant.now(clock)).toMillis > duration.toMillis
  }

  def olderThan(stamped: Stamped): Boolean = {
    olderThan(stamped.duration)
  }
}

case class StampedCount(count: Int = 1)(implicit clock: Clock = Clock.systemUTC()) extends Stamped {

  override val stamp: Instant = Instant.now(clock)

  def add: StampedCount = StampedCount(count + 1)
}