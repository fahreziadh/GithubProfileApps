package com.fahreziadha.githubprofile.ui.search

import GithubProfileTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahreziadha.githubprofile.R
import com.fahreziadha.githubprofile.utils.CustomSurface


@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    itemCount: Int //Temporary
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
                .padding(start = 27.dp, end = 27.dp, top = 8.dp, bottom = 50.dp),
        ) {

            LazyColumn(
                modifier = modifier.padding(horizontal = 24.dp)
            ) {
                items(itemCount) {
                    SearchItem(onClick = {})
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
fun SearchItem(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.mipmap.avatar),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Column(modifier = modifier.padding(horizontal = 8.dp)) {
            Row {
                Text(
                    text = "Wang Shi Eun",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "@wangshieun",
                    fontSize = 12.sp,
                    modifier = modifier.padding(horizontal = 4.dp),
                    color = GithubProfileTheme.colors.textHelp
                )
            }
            Text(
                text = "Director of Mihoyo Company",
                fontSize = 14.sp
            )
            Spacer(modifier = modifier.padding(4.dp))
            Row {
                Text(
                    text = "Taipei, Taiwan",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "wangshi@mihoyo.com",
                    fontSize = 12.sp,
                    modifier = modifier.padding(horizontal = 4.dp),
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchResultPreview() {
    GithubProfileTheme() {
        CustomSurface(color = GithubProfileTheme.colors.uiBackground) {
            SearchResult(itemCount = 5)
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchItemPreview() {
    GithubProfileTheme() {
        CustomSurface(color = GithubProfileTheme.colors.uiBackground) {
            SearchItem(onClick = {})
        }
    }
}