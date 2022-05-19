package com.example.rssreader.feature_feed.presentation.feeds

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.util.FeedOrder

sealed class FeedsEvent {
    data class Order(val feedOrder: FeedOrder): FeedsEvent()
    data class DeleteFeed(val feed: Feed): FeedsEvent()
    object RestoreFeed: FeedsEvent()
}
