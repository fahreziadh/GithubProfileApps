package com.fahreziadha.githubprofile.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User
import com.fahreziadha.githubprofile.ui.Screen
import com.fahreziadha.githubprofile.ui.components.SearchItem
import com.fahreziadha.githubprofile.utils.CustomSurface

@Composable
fun SearchHistoryResult(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val data = viewModel.cacheState.value.res

    if (data.isNotEmpty()) {
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
                LazyColumn(modifier = modifier.padding(bottom = 24.dp)) {
                    item {
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Last Seen",
                                modifier = modifier
                                    .padding(12.dp)
                                    .align(alignment = Alignment.Center),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Button(
                                onClick = { viewModel.clearCacheSearch() },
                                colors = ButtonDefaults.buttonColors(Color.White),
                                modifier = modifier
                                    .padding(12.dp)
                                    .align(alignment = Alignment.CenterEnd)
                                    .height(30.dp)
                                    .width(70.dp),
                                elevation =
                                ButtonDefaults.elevation(
                                    defaultElevation = 1.dp,
                                    pressedElevation = 5.dp,
                                    disabledElevation = 0.dp
                                ),
                            ) {
                                Text(text = "Clear", fontSize = 12.sp)
                            }
                        }

                    }
                    itemsIndexed(data.take(5)) { index, item ->
                        SearchItem(
                            onClick = {
                                navController.navigate(Screen.DetailScreen.route + "/${item.login}")
                            }, user = User(
                                id = item.id,
                                name = item.name,
                                login = item.login,
                                location = item.location,
                                email = item.email,
                                bio = item.bio,
                                avatarUrl = item.avatarUrl,
                                company = item.company
                            )
                        )
                        if (index != data.size - 1) {
                            Divider(
                                color = GithubProfileTheme.colors.textSecondary.copy(alpha = 0.05f),
                                thickness = 1.dp,
                                modifier = modifier.padding(horizontal = 2.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}
