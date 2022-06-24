package com.fahreziadha.githubprofile.ui.search

import GithubProfileTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fahreziadha.githubprofile.domain.model.User
import com.fahreziadha.githubprofile.ui.Screen
import com.fahreziadha.githubprofile.utils.CustomSurface
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage


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
                LazyColumn(
                    modifier = modifier.padding(horizontal = 24.dp)
                ) {
                    itemsIndexed(item) { index, searchItem ->
                        SearchItem(
                            onClick = { navController.navigate(Screen.DetailScreen.route + "/${searchItem.login}") },
                            user = searchItem,
                            modifier = modifier
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

@Composable
private fun LoadMoreButton(viewModel: SearchViewModel = hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 14.dp)
    ) {
        Button(
            onClick = { viewModel.getUserByName(isLoadMore = true) },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp), elevation = ButtonDefaults.elevation(
                defaultElevation = 1.dp,
                pressedElevation = 5.dp,
                disabledElevation = 0.dp
            )
        ) {
            if (viewModel.screenState.value.isLoading) {
                CircularProgressIndicator(color = GithubProfileTheme.colors.textSecondary)
            } else {
                Text(text = "Load More")
            }
        }
    }
}

@Composable
fun SearchItem(
    modifier: Modifier = Modifier, onClick: () -> Unit,
    user: User
) {

    Row(
        modifier = modifier
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        GlideImage(
            imageModel = user.avatarUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Column(modifier = modifier.padding(horizontal = 8.dp)) {
            Row {
                Text(
                    text = user.name.take(14) ?: "",
                    fontSize = 16.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "@${user.login}",
                    fontSize = 12.sp,
                    maxLines = 1,
                    modifier = modifier.padding(horizontal = 4.dp),
                    color = GithubProfileTheme.colors.textHelp
                )
            }

            user.bio?.let { bio ->
                Text(
                    text = bio,
                    maxLines = 1,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = modifier.padding(4.dp))
            Row {
                Text(
                    text = user.location?.replaceFirstChar { it.uppercase().take(10) }
                        ?: user.company ?: "null",
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = modifier.padding(horizontal = 8.dp))
                Text(
                    text = user.email ?: "null",
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}