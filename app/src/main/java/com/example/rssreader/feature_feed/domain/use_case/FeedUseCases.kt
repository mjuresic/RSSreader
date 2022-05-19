package com.example.rssreader.feature_feed.domain.use_case

data class FeedUseCases(
    val getFeeds: GetFeeds,
    val deleteFeed: DeleteFeed,
    val addFeed: AddFeed,
    val getFeed: GetFeed
)
