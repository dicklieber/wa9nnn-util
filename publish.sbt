import sbt.Keys.{developers, organization}

val homePage = "https://github.com/dicklieber/wa9nnn-util"
ThisBuild / organization := "com.wa9nnn"
ThisBuild / organizationName := "wa9nnn"
ThisBuild / organizationHomepage := Some(url(homePage))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/dicklieber/wa9nnn-util"),
    "git@github.com:dicklieber/wa9nnn-util.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "dicklieber",
    name = "Dick Lieber",
    email = "dick@u505.com",
    url = url("http://u505.com")
  )
)

ThisBuild / description := "Utils for scala3 and playframework 3.x."
ThisBuild / licenses := List(
  "GPL3" -> new URL("http://www.gnu.org/licenses/")
)
ThisBuild / homepage := Some(url(homePage))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  // For accounts created after Feb 2021:
   val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

releasePublishArtifactsAction := PgpKeys.publishSigned.value