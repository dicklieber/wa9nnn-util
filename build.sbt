
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

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "org.specs2" %% "specs2-mock" % "4.6.0" % "test",
)

publishTo := Some("Artifactory Realm" at "https://wa9nnn.jfrog.io/artifactory/wa9nnn")
credentials += Credentials("Artifactory Realm", "wa9nnn.jfrog.io", "Wa9nnn@u505.com", "71Lol+aLane")

publishTo := Some("Artifactory Realm" at "https://wa9nnn.jfrog.io/artifactory/wa9nnn;build.timestamp=" + new java.util.Date().getTime)
credentials += Credentials("Artifactory Realm", "wa9nnn.jfrog.io", "Wa9nnn@u505.com", "71Lol+aLane")