package com.example.rssreader.feature_feed.domain.use_case

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository

class GetFeed(
    private val repository: FeedRepository
) {

    suspend operator fun invoke(id: Int): Feed? {
        return repository.getFeedById(id)
    }
}