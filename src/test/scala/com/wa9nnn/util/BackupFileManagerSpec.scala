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

package com.wa9nnn.util

import org.apache.commons.io.file.{PathFilter, PathUtils}
import org.specs2.execute.{AsResult, Result}
import org.specs2.matcher.Matchers.beFalse
import org.specs2.mutable.Specification
import org.specs2.specification.{AroundEach, ForEach}

import java.io.IOException
import java.nio.file.attribute.{BasicFileAttributes, FileTime}
import java.nio.file.{FileVisitResult, FileVisitor, Files, Path, Paths, SimpleFileVisitor}
import java.time.Instant
import java.util
import java.util.stream
import scala.jdk.CollectionConverters.IteratorHasAsScala

class BackupFileManagerSpec extends Specification with WithTempDirectory {


  "BackupFileManagerSpec" should {
    "apply" in { tempDirectory: Path =>
      println(s"cd ${tempDirectory.toFile.toString}")
      val origTargetContents = "myfile.txt"
      val candidateContents = "candidate.txt"
      val target = tempDirectory.resolve(origTargetContents)
      val candidate = tempDirectory.resolve(candidateContents)
      val historyDirectory = tempDirectory.resolve("history")
      Files.writeString(target, origTargetContents)
      Files.writeString(candidate, candidateContents)
      Files.createDirectories(historyDirectory)
      val anOldFile = historyDirectory.resolve("oldfile")
      Files.writeString(anOldFile, "old stuff")
      Files.setLastModifiedTime(anOldFile, FileTime.from(Instant.EPOCH))

      BackupFileManager(target, candidate, historyDirectory)

      val newTargetContents = Files.readString(target)
      newTargetContents must beEqualTo(candidateContents)

      Files.exists(candidate) must beFalse // because we moved it.

      val filesInHistory: Seq[Path] = Files.list(historyDirectory).iterator().asScala.toSeq
      val renamedTarget = filesInHistory.head

      filesInHistory must haveLength(1)


      val renamedFileName = renamedTarget.getFileName.toString
      renamedFileName must startWith("myfile")
    }
  }
}

trait WithTempDirectory extends ForEach[Path] {
  override protected def foreach[R: AsResult](f: Path => R): Result = {
    val path = Files.createTempDirectory("BackupFileManager")
    try {
      try AsResult(f(path))
    } finally {
      PathUtils.deleteDirectory(path)
    }
  }
}