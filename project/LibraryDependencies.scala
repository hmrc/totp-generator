import sbt._

object LibraryDependencies {

  val compile: Seq[ModuleID] = Seq(
    "commons-codec"         %  "commons-codec" % "1.16.0"
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest"         %% "scalatest"     % "3.2.17",
    "com.vladsch.flexmark"  %  "flexmark-all"  % "0.62.2",
    "org.pegdown"           %  "pegdown"       % "1.5.0"
  ).map(_ % Test)
}
