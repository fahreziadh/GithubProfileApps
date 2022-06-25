package com.fahreziadha.githubprofile.ui

import GithubProfileTheme
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fahreziadha.githubprofile.R
import com.fahreziadha.githubprofile.framework.SearchDatabase
import com.fahreziadha.githubprofile.main.framework.db.SearchDao
import com.fahreziadha.githubprofile.main.ui.search.SearchScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
class GithubProfileAppTest {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var database: SearchDatabase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        database.close()
    }

    private fun inputSearch(text: String) {
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.search_people))
            .performTextInput(text)
    }

    private fun waitUntilVisibleWithText(text: String) {
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText(text)
                .fetchSemanticsNodes().size == 1
        }
    }

    private fun enqueueActivityResponse(activityJson: String) {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(activityJson)
        )
    }

    private fun clickOnNodeWithContentDescription(@StringRes cdRes: Int) {
        val contentDescription = ApplicationProvider.getApplicationContext<Context>()
            .getString(cdRes)

        composeTestRule.onNodeWithContentDescription(contentDescription)
            .performClick()
    }

}