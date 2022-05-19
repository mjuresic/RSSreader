package com.example.rssreader.feature_feed.domain.use_case

import com.example.rssreader.feature_feed.domain.model.InvalidFeedException
import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository

class AddFeed(
    private val repository: FeedRepository
) {

    @Throws(InvalidFeedException::class)
    suspend operator fun invoke(feed: Feed) {
        if(feed.title.isBlank()) {
            throw InvalidFeedException("Title must be entered")
        }
        if(feed.UrlLink.isBlank()) {
            throw InvalidFeedException("URL must be entered")
        }
        repository.insertFeed(feed)
    }
}