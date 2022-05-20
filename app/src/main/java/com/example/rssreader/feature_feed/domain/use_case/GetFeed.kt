package com.example.rssreader.feature_feed.domain.use_case

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeed @Inject constructor(
    private val repository: FeedRepository
) {

    suspend operator fun invoke(id: Int): Feed? {
        return repository.getFeedById(id)
    }
}