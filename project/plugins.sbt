resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"
resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.eed3si9n"     %   "sbt-assembly"            % "2.1.1")
addSbtPlugin("uk.gov.hmrc"      %   "sbt-auto-build"          % "3.18.0")
addSbtPlugin("org.scoverage"    %   "sbt-scoverage"           % "2.0.9")
addSbtPlugin("org.scalastyle"   %%  "scalastyle-sbt-plugin"   % "1.0.0")
addSbtPlugin("ch.epfl.scala"    %   "sbt-bloop"               % "1.5.11")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
