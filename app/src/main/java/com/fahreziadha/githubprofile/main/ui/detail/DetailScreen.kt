package com.fahreziadha.githubprofile.main.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreziadha.githubprofile.R
import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.GithubRepository
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import com.fahreziadha.githubprofile.ui.utils.covertTimeToText
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val uiUserState = viewModel.uiUserState.collectAsState().value
    val uiReposState = viewModel.uiReposState.collectAsState().value

    LazyColumn(contentPadding = PaddingValues(24.dp)) {

        /** Header Detail **/
        when (uiUserState) {
            is DetailScreenUserState.Loading -> {
                item {
                    Text(text = "Loading...")
                }
            }
            is DetailScreenUserState.Success -> {
                item { HeaderDetail(user = uiUserState.res) }
            }
            is DetailScreenUserState.Error -> {
                item {
                    Text(text = "Failed to Load User Profile")
                }
            }
        }
        item {
            Divider(
                color = GithubProfileTheme.colors.textSecondary.copy(alpha = 0.05f),
                thickness = 1.dp,
                modifier = modifier.padding(horizontal = 2.dp, vertical = 14.dp)
            )
        }

        /** List Repository **/
        when (uiReposState) {
            is DetailScreenReposState.Loading -> {
                item {
                    Text(text = "Loading...")
                }
            }
            is DetailScreenReposState.Success -> {
                itemsIndexed(uiReposState.res) { index, githubRepository ->
                    GitRepositoryItem(onClick = {}, rep = githubRepository)

                    if (index != uiReposState.res.size - 1) {
                        Divider(
                            color = GithubProfileTheme.colors.textSecondary.copy(alpha = 0.05f),
                            thickness = 1.dp,
                            modifier = modifier.padding(horizontal = 2.dp)
                        )
                    }
                }
            }
            is DetailScreenReposState.Error -> {
                item {
                    Text(text = "Failed to Load User Repos")
                }
            }
        }
    }
}


@Composable
fun GitRepositoryItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    rep: GithubReposResponseDTO
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier.padding(vertical = 12.dp)
        ) {
            GlideImage(
                imageModel = rep.owner.avatar_url,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(modifier = modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = rep.name?.take(14) ?: "",
                    fontSize = 16.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                )

                rep?.description?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = modifier.padding(4.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "",
                        modifier = modifier.size(14.dp)
                    )
                    Text(
                        text = rep.stargazers_count.toString(),
                        fontSize = 12.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = modifier.padding(horizontal = 8.dp))
                    Text(
                        text = rep.updated_at?.let { covertTimeToText(it) } ?: "null",
                        fontSize = 12.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }

    }
}


@Composable
fun HeaderDetail(
    modifier: Modifier = Modifier,
    user: UserResponseDTO
) {
    Row {
        GlideImage(
            imageModel = user.avatar_url,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(70.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = modifier.padding(horizontal = 8.dp))

        Column() {
            Text(text = user.name ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "@" + user.login ?: "", fontSize = 12.sp)
            Spacer(modifier = modifier.padding(vertical = 8.dp))
            Text(text = user.bio ?: "")
            Spacer(modifier = modifier.padding(vertical = 8.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_community),
                    contentDescription = "",
                    modifier = modifier.size(24.dp)
                )
                Spacer(modifier = modifier.padding(horizontal = 4.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = GithubProfileTheme.colors.textPrimary
                                )
                            ) {
                                append("${user.followers ?: 0} ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = GithubProfileTheme.colors.textSecondary
                                )
                            ) {
                                append("Followers")
                            }
                            append(" • ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = GithubProfileTheme.colors.textPrimary
                                )
                            ) {
                                append("${user.following ?: 0} ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = GithubProfileTheme.colors.textSecondary
                                )
                            ) {
                                append("Follwing")
                            }
                        }
                    }
                )
            }
            Spacer(modifier = modifier.padding(vertical = 4.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "",
                    modifier = modifier.size(24.dp)
                )
                Spacer(modifier = modifier.padding(horizontal = 4.dp))
                Text(text = user.location?.replaceFirstChar { it.uppercase() } ?: "null")
            }
            Spacer(modifier = modifier.padding(vertical = 4.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = "",
                    modifier = modifier.size(24.dp)
                )
                Spacer(modifier = modifier.padding(horizontal = 4.dp))
                Text(text = user.email ?: "null")
            }
        }
    }

}
