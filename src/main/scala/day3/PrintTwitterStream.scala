package day3

import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import util.Twitter

object PrintTwitterStream {
	def main(args: Array[String]): Unit = {
		Twitter.initialize()

		val ssc = new StreamingContext("local[*]", "Print Twitter Stream", Seconds(3))

		Logger.getRootLogger.setLevel(Level.ERROR)

		val tweets = TwitterUtils.createStream(ssc, None)

		tweets
   		.filter(_.getLang == "en")
			.map(_.getText)
			.print()

		ssc.start()
		ssc.awaitTermination()
	}
}
