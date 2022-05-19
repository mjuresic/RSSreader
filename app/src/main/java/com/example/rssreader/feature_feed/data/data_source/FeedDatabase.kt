package com.example.rssreader.feature_feed.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssreader.feature_feed.domain.model.Feed

@Database(
    entities = [Feed::class],
    version = 1
)
abstract class FeedDatabase: RoomDatabase() {

    abstract val feedDao: FeedDao

    companion object {
        const val DATABASE_NAME = "feeds_db"
    }
}