package com.example.rssreader.feature_feed.data.data_source

import androidx.room.*
import com.example.rssreader.feature_feed.domain.model.Feed
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Query("SELECT * FROM feed")
    fun getFeeds(): Flow<List<Feed>>

    @Query("SELECT * FROM feed WHERE id = :id")
    suspend fun getFeedById(id: Int): Feed?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeed(feed: Feed)

    @Delete
    suspend fun deleteFeed(feed: Feed)
}