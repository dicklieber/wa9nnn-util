import sbt.Credentials

name := "util"
organization := "com.wa9nnn"
organizationHomepage := Some(url("http:/www.wa9nnn.com"))


scalaVersion := "2.13.5"

//idePackagePrefix := Some("com.wa9nnn.tableui")
lazy val `fdcluster` = (project in file("."))
  .enablePlugins(GitPlugin, BuildInfoPlugin, SbtTwirl).settings(
  buildInfoKeys ++= Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion,
    git.gitCurrentTags, git.gitCurrentBranch, git.gitHeadCommit, git.gitHeadCommitDate, git.baseVersion,
    BuildInfoKey.action("buildTime") {
      System.currentTimeMillis
    } // re-computed each time at compile)
  ),
  //    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, git.gitCurrentTags, git.gitCurrentBranch),
  buildInfoPackage := "com.wa9nnn.util"
)
scalacOptions in(Compile, doc) ++= Seq("-verbose", "-Ymacro-annotations")
val logbackVersion = "1.2.10"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "org.specs2" %% "specs2-mock" % "4.6.0" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.jsoup" % "jsoup" % "1.13.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "ch.qos.logback" % "logback-classic" % logbackVersion,

)

//publishTo := Some("repo.wa9nnn.net" at "194.113.64.105/releases")
//credentials += Credentials(Path.userHome / ".sbt" / "jfrog.credentials")
//
//credentials += Credentials(Path.userHome
//  / ".sbt" / "wa9nnn.credentials")

//WWW-Authenticate: BASIC realm="Sonatype Nexus Repository Manager"


//credentials += Credentials("Reposilite Repository", "194.113.64.105", "dick", "zDNxOBMR3lr1+V4q+Ys5Wm2vYsN+VtT7Yd7M9BAfzoEvXo65+ny74Mm92veMqTEG")
credentials += Credentials("Sonatype Nexus Repository Manager", "localhost", "wa9nnn", "12AX7")
//http://localhost:8081/repository/maven-snapshots/
publishTo := {
  val server = "http://localhost:8081/repository/maven-"
  if (isSnapshot.value)
    Some(("Sonatype Nexus Repository Manager" at server + "snapshots").withAllowInsecureProtocol(true))
  else
    Some(("Sonatype Nexus Repository Manager"  at server + "releases").withAllowInsecureProtocol(true))
}
//resolvers +=
//  ("Sonatype Snapshots Nexus" at  "http://localhost:8081/repository/maven-snapshots").withAllowInsecureProtocol(true)

