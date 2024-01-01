/*
 * Copyright (C) ${year} Dick Lieber, WA9NNN
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

package com.wa9nnn.wa9nnnutil.macos

import com.apple.eawt.Application
import com.typesafe.scalalogging.LazyLogging
import com.wa9nnn.wa9nnnutil.OsDetector

object DockIcon extends LazyLogging {
  def apply(imagePath: String): Unit = {
    try {
     if(OsDetector.macos) {
        val application: Application = Application.getApplication
        import java.awt.{Image, Toolkit}

        val image: Image = Toolkit.getDefaultToolkit.getImage(getClass.getResource(imagePath))
        application.setDockIconImage(image)
      }
    } catch {
      case e: Throwable =>
        logger.error("DockIcon (MacOS specific", e)
    }
  }
}
