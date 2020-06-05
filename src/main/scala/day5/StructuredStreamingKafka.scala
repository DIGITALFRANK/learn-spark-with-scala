package day5

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object StructuredStreamingKafka {
	def startStreamingProcess(): Unit = {
		val spark = SparkSession
   		.builder
   		.appName("Kafka Structured Streaming")
   		.getOrCreate()

		Logger.getRootLogger.setLevel(Level.ERROR)

		val df = spark
   		.readStream
   		.format("kafka")
   		.option("kafka.bootstrap.servers", "localhost:9092")
   		.option("subscribe", "spark-stream")
   		.load()

		val query = df
			.writeStream
   		.outputMode("complete")
   		.format("console")
   		.start()

		query.awaitTermination()
	}
}
