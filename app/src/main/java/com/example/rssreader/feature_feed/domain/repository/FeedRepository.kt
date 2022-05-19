package com.example.rssreader.feature_feed.domain.repository

import com.example.rssreader.feature_feed.domain.model.Feed
import kotlinx.coroutines.flow.Flow

interface FeedRepository {

    fun getFeeds(): Flow<List<Feed>>

    suspend fun getFeedById(id: Int): Feed?

    suspend fun insertFeed(feed: Feed)

    suspend fun deleteFeed(feed: Feed)
}