package com.fahreziadha.githubprofile.ui.search

import GithubProfileTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.utils.CustomSurface
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    item: List<UserResponseDTO>
) {
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
                .padding(start = 21.dp, end = 27.dp, top = 8.dp, bottom = 50.dp),
        ) {

            LazyColumn(
                modifier = modifier.padding(horizontal = 24.dp)
            ) {
                items(item) { searchItem ->
                    SearchItem(onClick = {}, searchItem = searchItem)
                    Divider(
                        color = GithubProfileTheme.colors.uiBorder.copy(alpha = 0.05f),
                        thickness = 1.dp,
                        modifier = modifier.padding(horizontal = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    modifier: Modifier = Modifier, onClick: () -> Unit,
    searchItem: UserResponseDTO
) {
    Row(
        modifier = modifier
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        GlideImage(
            imageModel = searchItem.avatarUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Column(modifier = modifier.padding(horizontal = 8.dp)) {
            Row {
                Text(
                    text = searchItem.name?:searchItem.login?:"",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "@${searchItem.login}",
                    fontSize = 12.sp,
                    modifier = modifier.padding(horizontal = 4.dp),
                    color = GithubProfileTheme.colors.textHelp
                )
            }

            searchItem.bio?.let { bio ->
                Text(
                    text = bio,
                    maxLines = 1,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = modifier.padding(4.dp))
            Row {
                Text(
                    text = searchItem.location?.replaceFirstChar { it.uppercase() }
                        ?: searchItem.company ?: "null",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = searchItem.email ?: "null",
                    fontSize = 12.sp,
                    modifier = modifier.padding(horizontal = 4.dp),
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}