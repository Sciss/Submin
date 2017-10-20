name                := "Submin"
version             := "0.2.2"
organization        := "de.sciss"
licenses            := Seq("GPL v3+" -> url("http://www.gnu.org/licenses/gpl-3.0.txt"))
scalaVersion        := "2.11.11"
autoScalaLibrary    := false
crossPaths          := false
homepage            := Some(url(s"https://github.com/Sciss/${name.value}"))

def weblafVersion   = "2.1.3"
def rsyntaxVersion  = "2.6.1"

libraryDependencies ++= Seq(
  "de.sciss"      % "weblaf"          % weblafVersion,
  "com.fifesoft"  % "rsyntaxtextarea" % rsyntaxVersion % "test"
)

def commonJavaOptions = Seq("-source", "1.6")

javacOptions        := commonJavaOptions ++ Seq("-target", "1.6", "-g", "-Xlint:deprecation" /*, "-Xlint:unchecked" */)
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
    <url>git@github.com:Sciss/{n}.git</url>
    <connection>scm:git:git@github.com:Sciss/{n}.git</connection>
  </scm>
    <developers>
      <developer>
        <id>sciss</id>
        <name>Hanns Holger Rutz</name>
        <url>http://www.sciss.de</url>
      </developer>
    </developers>
}
