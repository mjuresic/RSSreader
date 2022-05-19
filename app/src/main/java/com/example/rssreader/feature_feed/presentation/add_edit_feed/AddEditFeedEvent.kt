package com.example.rssreader.feature_feed.presentation.add_edit_feed

import androidx.compose.ui.focus.FocusState

sealed class AddEditFeedEvent{
    data class EnteredTitle(val value: String): AddEditFeedEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditFeedEvent()
    data class EnteredContent(val value: String): AddEditFeedEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditFeedEvent()
    object SaveFeed: AddEditFeedEvent()
}

