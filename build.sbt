

name := "wa9nnnutil"
//organization := "com.wa9nnn"
//organizationHomepage := Some(url("https://github.com/dicklieber/wa9nnn-util"))


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

credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")

