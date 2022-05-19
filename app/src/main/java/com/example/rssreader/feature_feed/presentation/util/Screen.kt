package com.example.rssreader.feature_feed.presentation.util

sealed class Screen(val route: String) {
    object FeedsScreen: Screen("feeds_screen")
    object AddEditFeedScreen: Screen("add_edit_feed_screen")
}
