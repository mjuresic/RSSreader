package com.example.rssreader.feature_feed.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rssreader.feature_feed.presentation.add_edit_feed.AddEditFeedScreen
import com.example.rssreader.feature_feed.presentation.feeds.FeedsScreen
import com.example.rssreader.feature_feed.presentation.util.Screen
import com.example.rssreader.ui.theme.RSSreaderAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RSSreaderAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.FeedsScreen.route
                    ) {
                        composable(route = Screen.FeedsScreen.route) {
                            FeedsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditFeedScreen.route +
                                    "?feedId={feedId}",
                            arguments = listOf(
                                navArgument(
                                    name = "feedId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            AddEditFeedScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
