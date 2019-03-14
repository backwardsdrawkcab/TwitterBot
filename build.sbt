name := "TwitterBot"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.+"
libraryDependencies += "org.apache.httpcomponents" % "fluent-hc" % "4.5.+"
libraryDependencies += "com.taskadapter" % "trello-java-wrapper" % "0.+"


libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.3.2" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-engine" % "5.3.2" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-params" % "5.3.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.+" % Test
libraryDependencies += "org.apache.commons" % "commons-collections4" % "4.0"
