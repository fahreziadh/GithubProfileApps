package com.fahreziadha.githubprofile.ui

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahreziadha.githubprofile.R
import com.fahreziadha.githubprofile.framework.SearchDatabase
import com.fahreziadha.githubprofile.main.ui.search.SearchScreen
import com.fahreziadha.githubprofile.model.Destination
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Destination.Search.path) {
                composable(route = Destination.Search.path) {
                    SearchScreen(navController = navController)
                }
            }
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        database.close()
    }

    @Test
    fun clickSomethin_isVisible() {
        composeTestRule.setContent {
            val textFieldContentDescription = stringResource(id = R.string.search_people)
            composeTestRule.onNodeWithContentDescription(textFieldContentDescription).performTextInput("fahrezi")
        }

    }

    @Test
    fun searchingScrollingSelectingWorksCorrectly() {


    }

}