name                := "Submin"
version             := "0.3.0-SNAPSHOT"
organization        := "de.sciss"
licenses            := Seq("GPL v3+" -> url("http://www.gnu.org/licenses/gpl-3.0.txt"))
scalaVersion        := "2.12.10"
autoScalaLibrary    := false
crossPaths          := false
homepage            := Some(url(s"https://git.iem.at/sciss/${name.value}"))

def weblafVersion   = "1.2.10"
def rsyntaxVersion  = "2.6.1"

libraryDependencies ++= Seq(
  // "de.sciss"      % "weblaf"          % weblafVersion,
  "com.weblookandfeel" % "weblaf-core"     % weblafVersion,
  "com.weblookandfeel" % "weblaf-ui"       % weblafVersion,
  "com.fifesoft"       % "rsyntaxtextarea" % rsyntaxVersion % Test
)

mainClass in (Test, run) := Some("de.sciss.submin.SubminStyleEditor")

def commonJavaOptions = Seq("-source", "1.8")

javacOptions        := commonJavaOptions ++ Seq("-target", "1.8", "-g", "-Xlint:deprecation" /*, "-Xlint:unchecked" */)
javacOptions in doc := commonJavaOptions
  
// ---- publishing to Maven Central ----

publishMavenStyle := true

publishTo := {
  Some(if (isSnapshot.value)
    "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "Sonatype Releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
  )
}

publishArtifact in Test := false
pomIncludeRepository := { _ => false }
  
pomExtra := { val n = name.value
  <scm>
    <url>git@git.iem.at:sciss/{n}.git</url>
    <connection>scm:git:git@git.iem.at:sciss/{n}.git</connection>
  </scm>
    <developers>
      <developer>
        <id>sciss</id>
        <name>Hanns Holger Rutz</name>
        <url>http://www.sciss.de</url>
      </developer>
    </developers>
}
