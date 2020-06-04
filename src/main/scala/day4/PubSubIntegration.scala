package day4

import java.nio.charset.StandardCharsets

import org.apache.log4j.{Level, Logger}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.pubsub.{PubsubUtils, SparkGCPCredentials}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object PubSubIntegration {
	val projectId = "x-launchpad"
	val subscriptionName = "spark-subscription"
	val serviceAccountPath = "c:/Users/marku/git/learn-spark-with-scala/src/main/resources/x-launchpad-4e89c7e98759.json"

	def startStreamingProcess(): Unit = {
		val ssc = new StreamingContext("local[*]", "Google PubSub Integration", Seconds(5))

		Logger.getRootLogger.setLevel(Level.ERROR)

		val credentials = SparkGCPCredentials
			.builder
   		.jsonServiceAccount(serviceAccountPath)
   		.build()

		val stream = PubsubUtils.createStream(
			ssc,
			projectId,
			None,
			subscriptionName,
			credentials,
			StorageLevel.MEMORY_AND_DISK_SER_2
		)

		stream
   		.foreachRDD { rdd =>
			   rdd.foreach { msg =>
				   val msgText = new String(msg.getData(), StandardCharsets.UTF_8)
				   println(s"New message arrived at ${msg.getPublishTime()}: $msgText")
			   }
		   }

		ssc.start()

		ssc.awaitTermination()
	}
}
