package com.fahreziadha.githubprofile.main.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahreziadha.githubprofile.main.model.User
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchItem(
    modifier: Modifier = Modifier, onClick: () -> Unit,
    user: User
) {

    Row(
        modifier = modifier
            .padding(vertical = 12.dp,horizontal = 24.dp)
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
                    text = user.name?.take(14) ?: "",
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