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
    githubOwner := "dicklieber",
    githubRepository := "util"
  )

//https://github.com/djspiewak/sbt-github-packages
  

scalacOptions in(Compile, doc) ++= Seq("-verbose", "-Ymacro-annotations")

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
/*resolvers +=
  ("ReposiliteXYZZY" at "http://repo.wa9nnn.net:8080/releases").withAllowInsecureProtocol(true)

//credentials += Credentials("Reposilite", "127.0.0.1", "wa9nnn-deploy", "T/d7hlJWwdYMIj1GxmmVIB3IwuZ4X1FfZq7KDCtgbrjpTvBwLdxT2mSYGkfW025F")
credentials += Credentials(Path.userHome / ".sbt" / "credentials-reposolite")

publishTo := Some(("ReposilitePLUGH" at "http://repo.wa9nnn.net:8080/releases").withAllowInsecureProtocol(true))
//publishTo := Some(("ReposilitePLUGH" at "http://127.0.0.1:8080/releases").withAllowInsecureProtocol(true))
*/

