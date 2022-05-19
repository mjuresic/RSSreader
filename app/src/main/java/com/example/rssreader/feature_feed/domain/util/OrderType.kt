package com.example.rssreader.feature_feed.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
