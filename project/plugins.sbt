import sbt.addSbtPlugin

logLevel := Level.Warn

resolvers += ("Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/").withAllowInsecureProtocol(true)

addSbtPlugin("org.jetbrains" % "sbt-ide-settings" % "1.1.0")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1")


addSbtPlugin("com.github.sbt" % "sbt-release" % "1.3.0")

addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.3")

addSbtPlugin("org.playframework.twirl" % "sbt-twirl" % "2.0.3")

