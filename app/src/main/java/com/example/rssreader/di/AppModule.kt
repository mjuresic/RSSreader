package com.example.rssreader.di

import android.app.Application
import androidx.room.Room
import com.example.rssreader.feature_feed.data.data_source.FeedDatabase
import com.example.rssreader.feature_feed.data.repository.FeedRepositoryImpl
import com.example.rssreader.feature_feed.domain.repository.FeedRepository
import com.example.rssreader.feature_feed.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFeedDatabase(app: Application): FeedDatabase {
        return Room.databaseBuilder(
            app,
            FeedDatabase::class.java,
            FeedDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFeedRepository(db: FeedDatabase): FeedRepository {
        return FeedRepositoryImpl(db.feedDao)
    }

    @Provides
    @Singleton
    fun provideFeedUseCases(repository: FeedRepository): FeedUseCases {
        return FeedUseCases(
            getFeeds = GetFeeds(repository),
            deleteFeed = DeleteFeed(repository),
            addFeed = AddFeed(repository),
            getFeed = GetFeed(repository)
        )
    }
}