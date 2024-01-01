/*
 *
 *  * Copyright (C) @year  Dick Lieber, WA9NNN
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 *
 */

package com.wa9nnn.wa9nnnutil

import com.wa9nnn.wa9nnnutil.tableui.Row

import java.util.concurrent.atomic.AtomicInteger
import scala.collection.concurrent.TrieMap

class Counted[T] {
  private val map: TrieMap[T, AtomicInteger] = TrieMap.empty

  def apply(thing: T): Unit = {
    map.getOrElseUpdate(thing, new AtomicInteger())
      .incrementAndGet()
  }


  /**
   * result and apply(fromResult) allows transferring an immutable object from one [[Counted]] to another.
   *
   * @return
   */
  def result: CountedThings[T] = CountedThings(map.map { case (t, atomicInteger) => t -> atomicInteger.get }.toMap)

  def apply(fromResult: CountedThings[T]): Unit = {
    fromResult.map.map { case (t, count) =>
      map.getOrElseUpdate(t, new AtomicInteger())
        .addAndGet(count)
    }
  }
}

case class CountedThings[T](map: Map[T, Int]) {
  def size: Int = map.values.sum

  def rows: Seq[Row] = {
    map.iterator.map {
      case (thing, count) =>
        Row(thing.toString, count)
    }
      .toSeq
      .sortBy(row => row.cells.head.value)
  }

}
