package com.example.rssreader.feature_feed.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Feed(
    val title: String,
    val UrlLink: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
) {

}
// u novu klasu
class InvalidFeedException(message: String): Exception(message)