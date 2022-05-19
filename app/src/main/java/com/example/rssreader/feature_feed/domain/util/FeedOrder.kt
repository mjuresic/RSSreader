package com.example.rssreader.feature_feed.domain.util

sealed class FeedOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): FeedOrder(orderType)
}
