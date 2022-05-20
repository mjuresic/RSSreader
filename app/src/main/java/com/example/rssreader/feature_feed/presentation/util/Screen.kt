package com.example.rssreader.feature_feed.presentation.util

const val FEED_ID_KEY = "feedId"

sealed class Screen(val route: String) {
    object FeedsScreen: Screen("feeds_screen")
    object AddEditFeedScreen: Screen("add_edit_feed_screen")
}
