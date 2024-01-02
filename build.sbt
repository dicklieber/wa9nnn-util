import sbt.Credentials

name := "wa9nnnutil"
organization := "com.wa9nnn"
organizationHomepage := Some(url("http:/www.wa9nnn.com"))


scalaVersion := "3.3.1"

//idePackagePrefix := Some("com.wa9nnn.tableui")
lazy val `util` = (project in file("."))
  .enablePlugins(GitPlugin, BuildInfoPlugin, SbtTwirl).settings(
    buildInfoKeys ++= Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion,
      git.gitCurrentTags, git.gitCurrentBranch, git.gitHeadCommit, git.gitHeadCommitDate, git.baseVersion,
      BuildInfoKey.action("buildTime") {
        System.currentTimeMillis
      } // re-computed each time at compile)
    ),
    //    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, git.gitCurrentTags, git.gitCurrentBranch),
    buildInfoPackage := "com.wa9nnn.util",
    versionScheme := Some("early-semver")
  )



Compile / scalacOptions ++= Seq("-verbose")

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.17",
  "org.scalatest" %% "scalatest" % "3.2.17" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % "test",

  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.jsoup" % "jsoup" % "1.13.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "ch.qos.logback" % "logback-classic" % "1.4.7",
  "ch.qos.logback" % "logback-classic" % "1.4.7",
  "ch.qos.logback" % "logback-core" % "1.4.7",
  "com.typesafe.play" %% "twirl-api" % "1.6.0-RC4",
  "com.typesafe.play" %% "play-json" % "2.10.0-RC9",
  "commons-io" % "commons-io" % "2.11.0"

)
resolvers += ("Reposilite" at "http://127.0.0.1:8080/releases").withAllowInsecureProtocol(true)
//  ("Reposilite=" at "http://127.0.0.1:8080/releases").withAllowInsecureProtocol(true)
//  ("ReposiliteXYZZY" at "http://repo.wa9nnn.net:8080/releases").withAllowInsecureProtocol(true)

credentials += Credentials(Path.userHome / ".sbt" / "credentials-reposilite")

/**
 * The file is like:
 * {{{
 * realm=Reposilite   // Must be this as it's what reposilite authenticate with.
 * host=127.0.0.1     // Nogte no port1!
 * user=publisher     // token name in reposilite.
 * password=xxxx      // secret for the reposilite user.
 * }}}
 */


publishTo := {
  val url = "http://repo.wa9nnn.tech:8080"
  if (isSnapshot.value)
    Some(("Reposilite Repository" at s"$url/snapshots").withAllowInsecureProtocol(true))
  else
    Some(("Reposilite Repository" at "s$url/releases").withAllowInsecureProtocol(true))
}
