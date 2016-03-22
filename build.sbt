name                := "Submin"
version             := "0.1.0-SNAPSHOT"
organization        := "de.sciss"
licenses            := Seq("GPL v3+" -> url("http://www.gnu.org/licenses/gpl-3.0.txt"))
scalaVersion        := "2.11.8"
autoScalaLibrary    := false
crossPaths          := false
homepage            := Some(url(s"https://github.com/Sciss/${name.value}"))

def weblafVersion   = "1.29-SNAPSHOT"
def rsyntaxVersion  = "2.5.8"

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
  
pomExtra := { val n = name.value.toLowerCase
  <scm>
    <url>git@github.com:mgarin/{n}.git</url>
    <connection>scm:git:git@github.com:mgarin/{n}.git</connection>
  </scm>
    <developers>
      <developer>
        <id>mgarin</id>
        <name>Mikle Garin</name>
        <url>http://weblookandfeel.com</url>
      </developer>
    </developers>
}
