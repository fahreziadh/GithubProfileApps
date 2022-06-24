package com.fahreziadha.githubprofile.main.ui.search

import GithubProfileTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fahreziadha.githubprofile.main.model.CacheUser
import com.fahreziadha.githubprofile.main.model.User
import com.fahreziadha.githubprofile.main.ui.components.LoadMoreButton
import com.fahreziadha.githubprofile.main.ui.components.SearchItem
import com.fahreziadha.githubprofile.model.Destination
import com.fahreziadha.githubprofile.ui.utils.CustomSurface
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    item: List<User>,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            viewModel.refresh()
        }, indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                // Pass the SwipeRefreshState + trigger through
                state = state,
                refreshTriggerDistance = refreshTrigger,
                // Enable the scale animation
                contentColor = Color.White,
                scale = true,
                // Change the color and shape
                backgroundColor = GithubProfileTheme.colors.iconSecondary,
                shape = MaterialTheme.shapes.small,
            )
        }) {
        CustomSurface(
            color = GithubProfileTheme.colors.uiBackground,
            contentColor = GithubProfileTheme.colors.textSecondary,
        ) {
            CustomSurface(
                shape = RoundedCornerShape(4.dp),
                color = GithubProfileTheme.colors.uiBackground,
                elevation = 4.dp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 21.dp, end = 21.dp, top = 8.dp, bottom = 21.dp),
            ) {
                LazyColumn() {
                    itemsIndexed(item) { index, searchItem ->
                        SearchItem(
                            onClick = {
                                navController.navigate(Destination.Detail.path + "/${searchItem.login}")
                                viewModel.insertCacheUser(
                                    CacheUser(
                                        id = searchItem.id,
                                        name = searchItem.name,
                                        login = searchItem.login,
                                        location = searchItem.location,
                                        email = searchItem.email,
                                        bio = searchItem.bio,
                                        avatarUrl = searchItem.avatarUrl,
                                        company = searchItem.company,
                                        timeStamp = System.currentTimeMillis()
                                    )
                                )
                            },
                            user = searchItem,
                        )

                        if (index != item.size - 1) {
                            Divider(
                                color = GithubProfileTheme.colors.textSecondary.copy(alpha = 0.05f),
                                thickness = 1.dp,
                                modifier = modifier.padding(horizontal = 2.dp)
                            )
                        }
                    }
                    item {
                        if (item.isNotEmpty()) LoadMoreButton()
                    }
                }

            }
        }
    }
}
