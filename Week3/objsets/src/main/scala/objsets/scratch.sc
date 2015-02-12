import objsets._
val google = List(
  "android", "Android", "galaxy",
  "Galaxy", "nexus", "Nexus"
)
val tweets = TweetReader.allTweets
tweets.filter(tweet => tweet.text.contains("Android"))
val filtered = tweets.filter(tweet => google.exists(str => tweet.text.contains(str)))
tweets.mostRetweeted.retweets
filtered.mostRetweeted.retweets
