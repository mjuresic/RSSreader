package com.example.rssreader.feature_feed.domain.use_case

import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.repository.FeedRepository
import com.example.rssreader.feature_feed.domain.util.FeedOrder
import com.example.rssreader.feature_feed.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFeeds(
    private val repository: FeedRepository
) {

    operator fun invoke(
        feedOrder: FeedOrder = FeedOrder.Date(OrderType.Ascending)
    ): Flow<List<Feed>> {
        return repository.getFeeds().map { feeds ->
            when(feedOrder.orderType) {
                is OrderType.Ascending -> {
                    when(feedOrder) {
                        is FeedOrder.Date -> feeds.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(feedOrder) {
                        is FeedOrder.Date -> feeds.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}