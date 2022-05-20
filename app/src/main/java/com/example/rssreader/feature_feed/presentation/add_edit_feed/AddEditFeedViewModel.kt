package com.example.rssreader.feature_feed.presentation.add_edit_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.feature_feed.domain.model.InvalidFeedException
import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.use_case.FeedInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditFeedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val feedInteractor: FeedInteractor,
    private val timeProvider: () -> Long
) : ViewModel() {

    private val _feedTitle = mutableStateOf(FeedTextFieldState(
        hint = "Feed Title"
    ))
    val feedTitle: State<FeedTextFieldState> = _feedTitle

    private val _feedURL = mutableStateOf(FeedTextFieldState(
        hint = "Feed URL"
    ))
    val feedURL: State<FeedTextFieldState> = _feedURL

    // channel.buffered
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentFeedId: Int? = null

    init {
        savedStateHandle.get<Int>("feedId")?.let { feedId ->
            if(feedId != -1) {
                viewModelScope.launch {
                    feedInteractor.getFeed(feedId)?.also { feed ->
                        currentFeedId = feed.id
                        _feedTitle.value = feedTitle.value.copy(
                            text = feed.title,
                            isHintVisible = false
                        )
                        _feedURL.value = _feedURL.value.copy(
                            text = feed.UrlLink,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditFeedEvent) {
        when(event) {
            is AddEditFeedEvent.EnteredTitle -> {
                _feedTitle.value = feedTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditFeedEvent.ChangeTitleFocus -> {
                _feedTitle.value = feedTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            feedTitle.value.text.isBlank()
                )
            }
            is AddEditFeedEvent.EnteredContent -> {
                _feedURL.value = _feedURL.value.copy(
                    text = event.value
                )
            }
            is AddEditFeedEvent.ChangeContentFocus -> {
                _feedURL.value = _feedURL.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _feedURL.value.text.isBlank()
                )
            }
            is AddEditFeedEvent.SaveFeed -> {
                viewModelScope.launch {
                    try {
                        feedInteractor.addFeed(
                            Feed(
                                title = feedTitle.value.text,
                                UrlLink = feedURL.value.text,
                                timestamp = timeProvider(),
                                id = currentFeedId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveFeed)
                    } catch(e: InvalidFeedException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Error while saving"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveFeed: UiEvent()
    }
}