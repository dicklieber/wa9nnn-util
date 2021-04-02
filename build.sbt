
name := "util"
organization := "com.wa9nnn"
organizationHomepage := Some(url("http:/www.wa9nnn.com"))


scalaVersion := "2.13.5"

//idePackagePrefix := Some("com.wa9nnn.tableui")

lazy val `fdcluster` = (project in file("."))
  .enablePlugins(JavaAppPackaging, GitPlugin, BuildInfoPlugin, SbtTwirl).settings(
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

publishTo := Some(Resolver.file("local-ivy", file("path/to/ivy-repo/releases")))
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.6.0" % "test",
  "org.specs2" %% "specs2-mock" % "4.6.0" % "test",
)
