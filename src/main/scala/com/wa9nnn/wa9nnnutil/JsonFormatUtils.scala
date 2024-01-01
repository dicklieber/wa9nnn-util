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

import play.api.libs.json._

import scala.reflect.ClassTag


object JsonFormatUtils {
  // author: Marius Soutier  - https://stackoverflow.com/questions/21737019/create-reads-writes-for-java-enum-without-field
  def javaEnumFormat[E <: Enum[E] : ClassTag]: Format[E] = new Format[E] {
    override def reads(json: JsValue): JsResult[E] = json.validate[String] match {
      case JsSuccess(value, _) => try {
        val clazz = implicitly[ClassTag[E]].runtimeClass.asInstanceOf[Class[E]]
        JsSuccess(Enum.valueOf(clazz, value))
      } catch {
        case _: IllegalArgumentException => JsError("enumeration.unknown.value")
      }
      case JsError(_) => JsError("enumeration.expected.string")
    }

    override def writes(o: E): JsValue = JsString(o.toString)
  }
}