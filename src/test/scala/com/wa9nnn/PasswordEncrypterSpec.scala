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

package com.wa9nnn

import com.wa9nnn.util.UtilSpec


class PasswordEncrypterSpec extends UtilSpec {

  "PasswordEncrypter" should {
    new PasswordEncrypter("swordfish")
    val pwd = "you cant come in here"
    "have prefixed enrypted" in {
      val encrypted: String = PasswordEncrypter.encrypt(pwd)
      encrypted must startWith(PasswordEncrypter.prefix)
      val encryptedPart = encrypted.drop(PasswordEncrypter.prefix.length)
      encryptedPart must not equal (pwd)
    }

    "round trip encrypted" should {
      val encrypted = PasswordEncrypter.encrypt(pwd)
      "ensure no EOL" in {
        encrypted must not endWith ("\r\n")
      }
      "Not same as input" in {
        val backAgain: String = PasswordEncrypter.decrypt(encrypted)
        backAgain must equal(pwd)
      }
    }
    "round trip if not encrypted" in {
      val encrypted = "Harpo"
      val backAgain = PasswordEncrypter.decrypt(encrypted)
      backAgain must equal (encrypted)
    }

  }
  "Mixin after loading" in {
    val encrypter = new PasswordEncrypter("swordfish")
    PasswordEncrypter.encrypt("hello") must equal("{b}:qyCcRGNyzCM=")
  }

}
