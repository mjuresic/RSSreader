package com.example.rssreader.feature_feed.data.domain.use_case
import com.google.common.truth.Truth.assertThat
import com.example.rssreader.feature_feed.data.repository.TestingFeedRepository
import com.example.rssreader.feature_feed.domain.model.Feed
import com.example.rssreader.feature_feed.domain.use_case.GetFeeds
import com.example.rssreader.feature_feed.domain.util.FeedOrder
import com.example.rssreader.feature_feed.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFeedsTest {

    private lateinit var getFeeds: GetFeeds
    private lateinit var testingRepositoriy: TestingFeedRepository

    @Before
    fun setUp() {
        testingRepositoriy = TestingFeedRepository()
        getFeeds = GetFeeds(testingRepositoriy)

        val notesToInsert = mutableListOf<Feed>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Feed(
                    title = c.toString(),
                    UrlLink = c.toString(),
                    timestamp = index.toLong()
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach { testingRepositoriy.insertFeed(it) }
        }
    }


    @Test
    fun `Order notes by date ascending, correct order`() = runBlocking {
        val notes = getFeeds(FeedOrder.Date(OrderType.Ascending)).first()

        for(i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isLessThan(notes[i+1].timestamp)
        }
    }

    @Test
    fun `Order notes by date descending, correct order`() = runBlocking {
        val notes = getFeeds(FeedOrder.Date(OrderType.Descending)).first()

        for(i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isGreaterThan(notes[i+1].timestamp)
        }
    }
}