package com.example.rssreader.feature_feed.domain.use_case

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository

class DeleteFeed(
    private val repository: FeedRepository
) {

    suspend operator fun invoke(feed: Feed) {
        repository.deleteFeed(feed)
    }
}