name := """activator-kafka-streams"""

version := "1.0"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.apache.kafka" % "kafka-clients" % "0.10.2.0",
  "org.apache.kafka" % "kafka-streams" % "0.10.2.0",
  "org.twitter4j" % "twitter4j-stream" % "4.0.6",
  "com.typesafe" % "config" % "1.3.1"
)

