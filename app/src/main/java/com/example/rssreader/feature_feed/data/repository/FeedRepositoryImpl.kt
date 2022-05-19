package com.example.rssreader.feature_feed.data.repository

import com.example.rssreader.feature_feed.data.data_source.FeedDao
import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class FeedRepositoryImpl(
    private val dao: FeedDao
) : FeedRepository {

    override fun getFeeds(): Flow<List<Feed>> {
        return dao.getFeeds()
    }

    override suspend fun getFeedById(id: Int): Feed? {
        return dao.getFeedById(id)
    }

    override suspend fun insertFeed(feed: Feed) {
        dao.insertFeed(feed)
    }

    override suspend fun deleteFeed(feed: Feed) {
        dao.deleteFeed(feed)
    }
}