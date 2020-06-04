package day3

import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import util.Twitter

object TwitterStreamTransformations {
	def main(args: Array[String]): Unit = {
		Twitter.initialize()

		val ssc = new StreamingContext("local[*]", "Twitter Stream Transformations", Seconds(30))

		Logger.getRootLogger.setLevel(Level.ERROR)

		val tweets = TwitterUtils.createStream(ssc, None)

		tweets
			.filter(_.getLang == "en")
   		.filter(_.getContributors.length < 3)
			.map(status => status.isRetweet -> status.getText.length.toDouble)
   		.reduceByKey((val1, val2) => (val1 + val2) / 2.0)
			.print()

		ssc.start()
		ssc.awaitTermination()
	}
}
