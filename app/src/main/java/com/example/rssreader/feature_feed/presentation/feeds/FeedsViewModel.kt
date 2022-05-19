package com.example.rssreader.feature_feed.presentation.feeds

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.use_case.FeedUseCases
import com.example.rssreader.feature_feed.domain.util.FeedOrder
import com.example.rssreader.feature_feed.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val feedUseCases: FeedUseCases
) : ViewModel() {

    private val _state = mutableStateOf(FeedsState())
    val state: State<FeedsState> = _state

    private var recentlyDeletedFeed: Feed? = null

    private var getFeedJob: Job? = null

    init {
        getFeeds(FeedOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: FeedsEvent) {
        when (event) {
            is FeedsEvent.Order -> {
                if (state.value.feedOrder::class == event.feedOrder::class &&
                    state.value.feedOrder.orderType == event.feedOrder.orderType
                ) {
                    return
                }
                getFeeds(event.feedOrder)
            }
            is FeedsEvent.DeleteFeed -> {
                viewModelScope.launch {
                    feedUseCases.deleteFeed(event.feed)
                    recentlyDeletedFeed = event.feed
                }
            }
            is FeedsEvent.RestoreFeed -> {
                viewModelScope.launch {
                    feedUseCases.addFeed(recentlyDeletedFeed ?: return@launch)
                    recentlyDeletedFeed = null
                }
            }
        }
    }

    private fun getFeeds(feedOrder: FeedOrder) {
        getFeedJob?.cancel()
        getFeedJob = feedUseCases.getFeeds(feedOrder)
            .onEach { feeds ->
                _state.value = state.value.copy(
                    feeds = feeds,
                    feedOrder = feedOrder
                )
            }
            .launchIn(viewModelScope)
    }
}