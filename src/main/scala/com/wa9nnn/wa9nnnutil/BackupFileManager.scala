/*
 * Copyright (C) 2023 Dick Lieber, WA9NNN
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

import com.wa9nnn.wa9nnnutil.TimeConverters.fileStamp
import org.apache.commons.io.file.SimplePathVisitor

import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path}
import java.time.{Duration, Instant}

object BackupFileManager {

  /**
   *
   * @param target           may or maynoit exist,
   * @param candidate        incoming file. If in same directory as target, then the name mus be different. Can be same name if located elsewhere.
   * @param historyDirectory Move renamed file to this directory..
   * @param discardOlderThan how much to keep in history.
   */
  def apply(target: Path, candidate: Path, historyDirectory: Path, discardOlderThan: Duration = Duration.parse("P30D")): Unit = {
    val fileTime: Instant = Files.getLastModifiedTime(target).toInstant
    val stampName = fileStamp(fileTime)
    val historicFileName: String = target.getFileName.toString.replace(".", stampName + ".")
    val histFinal = historyDirectory.resolve(historicFileName)
    Files.createDirectories(historyDirectory)
    Files.move(target, histFinal)
    Files.move(candidate, target)

    // cleanup history.
    val visitor = OldFileDeleter(discardOlderThan)
    Files.walkFileTree(historyDirectory, visitor)
  }
}

case class OldFileDeleter(discardOlderThan: Duration) extends SimplePathVisitor {
  val deathLine: Instant = Instant.now().minus(discardOlderThan)

  override def visitFile(path: Path, attrs: BasicFileAttributes): FileVisitResult = {
    val lastMod = attrs.lastModifiedTime().toInstant
    if (lastMod.isBefore(deathLine))
      Files.delete(path)
    FileVisitResult.CONTINUE
  }
}