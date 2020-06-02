import Versions._

name := "learn-spark-with-scala"

version := "1.0.0"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-streaming" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
	//"org.apache.spark" % "spark-sql-kafka-0-10_2.11" % sparkVersion,
	//"org.apache.spark" % "spark-streaming-kafka-0-8_2.11" % sparkVersion,
	//"org.apache.spark" %% "spark-streaming-kinesis-asl" % sparkVersion,
	//"org.apache.bahir" %% "spark-streaming-twitter" % "2.2.0",
	//"com.amazonaws" % "amazon-kinesis-client" % "1.8.9"
)