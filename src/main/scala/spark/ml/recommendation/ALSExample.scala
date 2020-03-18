package spark.ml.recommendation

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.recommendation.ALS
import org.apache.spark.sql.SparkSession

object ALSExample {

  // $example on$
  case class Rating(userId: Int, movieId: Int, rating: Float, timestamp: Long)

  def parseRating(str: String): Rating = {
    val fields = str.split("::")
    assert(fields.size == 4)
    Rating(fields(0).toInt, fields(1).toInt, fields(2).toFloat, fields(3).toLong)
  }

  // $example off$

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("ALSExample").master("local[2]")
      .getOrCreate()
    import spark.implicits._

    // $example on$
    val ratings = spark.read.textFile("data/mllib/als/sample_movielens_ratings.txt")
      .map(parseRating)
      .toDF()
    val Array(training, test) = ratings.randomSplit(Array(0.8, 0.2))

    // Build the recommendation model using ALS on the training data
    val als = new ALS()
      .setRank(50)
      .setMaxIter(5)
      .setRegParam(0.01)
      //      .setImplicitPrefs(true)
      .setUserCol("userId")
      .setItemCol("movieId")
      .setRatingCol("rating")
    val model = als.fit(training)
    model.userFactors.show(10)
    model.itemFactors.show(10)
    // Evaluate the model by computing the RMSE on the test data
    val predictions = model.transform(test)

    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("rating")
      .setPredictionCol("prediction")
    val rmse = evaluator.evaluate(predictions)
    println(s"Root-mean-square error = $rmse")
    // $example off$

    // Generate top 10 movie recommendations for each user
    val userRecs = model.recommendForAllUsers(10)
    //userRecs.show(20)
    // Generate top 10 user recommendations for each movie
    val movieRecs = model.recommendForAllItems(10)
    //movieRecs.show(20)
    // Generate top 10 movie recommendations for a specified set of users
    val users = ratings.select(als.getUserCol).distinct().limit(3)
    //users.show(10)
    val userSubsetRecs = model.recommendForUserSubset(users, 10)
    //userSubsetRecs.show(10)
    // Generate top 10 user recommendations for a specified set of movies
    val movies = ratings.select(als.getItemCol).distinct().limit(3)
    movies.show(10)
    val movieSubSetRecs = model.recommendForItemSubset(movies, 10)
    spark.stop()
  }
}
