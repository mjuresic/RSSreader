package com.example.rssreader.feature_feed.presentation.feeds

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.util.FeedOrder
import com.example.rssreader.feature_feed.domain.util.OrderType

data class FeedsState(
    val feeds: List<Feed> = emptyList(),
    val feedOrder: FeedOrder = FeedOrder.Date(OrderType.Descending)
)
