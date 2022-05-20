package com.example.rssreader.feature_feed.data.repository

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestingFeedRepository : FeedRepository {

    private val feeds = mutableListOf<Feed>()

    override fun getFeeds(): Flow<List<Feed>> {
        return flow { emit(feeds) }
    }

    override suspend fun getFeedById(id: Int): Feed? {
        return feeds.find { it.id == id }
    }

    override suspend fun insertFeed(feed: Feed) {
        feeds.add(feed)
    }

    override suspend fun deleteFeed(feed: Feed) {
        feeds.remove(feed)
    }
}