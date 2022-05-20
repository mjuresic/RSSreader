package com.example.rssreader.feature_feed.domain.use_case

import javax.inject.Inject

class FeedInteractor @Inject constructor(
    val getFeeds: GetFeeds,
    val deleteFeed: DeleteFeed,
    val addFeed: AddFeed,
    val getFeed: GetFeed
)
