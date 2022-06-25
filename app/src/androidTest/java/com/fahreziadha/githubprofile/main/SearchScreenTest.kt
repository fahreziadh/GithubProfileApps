package com.fahreziadha.githubprofile.main

import GithubProfileTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.fahreziadha.githubprofile.main.ui.search.SearchBar
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkIsComponentLoaded() {
        composeTestRule.setContent {
            GithubProfileTheme {
                SearchBar(
                    query = "",
                    onQueryChange = {},
                    searchFocused = false,
                    onSearchFocusChange = {},
                    onClearQuery = { },
                    searching = false,
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("Search People").assertIsDisplayed()
    }
}