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

import org.specs2.mutable.Specification


class PasswordEncrypterSpec extends Specification {
  sequential

  "PasswordEncrypter" should {
    new PasswordEncrypter("swordfish")
    val pwd = "you cant come in here"
    "have prefixed enrypted" in {
      val encrypted = PasswordEncrypter.encrypt(pwd)
      encrypted must startWith(PasswordEncrypter.prefix)
      val encryptedPart = encrypted.drop(PasswordEncrypter.prefix.length)
      encryptedPart must be_!=(pwd)
    }

    "round trip encrypted" >> {
      val encrypted = PasswordEncrypter.encrypt(pwd)
      encrypted must not endingWith ("\r\n")
      val backAgain = PasswordEncrypter.decrypt(encrypted)
      backAgain must beEqualTo(pwd)

    }
    "round trip play" >> {
      val encrypted = "Harpo"
      val backAgain = PasswordEncrypter.decrypt(encrypted)
      backAgain must beEqualTo(encrypted)
    }

  }
  "Mixin after loading" >> {
    val encrypter = new PasswordEncrypter("swordfish")
    PasswordEncrypter.keySpec must beSome()
    PasswordEncrypter.encrypt("hello") must beEqualTo ("{b}:qyCcRGNyzCM=")
  }

}
