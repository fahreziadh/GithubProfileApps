package com.fahreziadha.githubprofile.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.domain.model.GithubRepository
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val state = viewModel.state.value
    val stateRep = viewModel.stateRep.value
    LazyColumn(contentPadding = PaddingValues(24.dp)) {
        stateRep.res?.let { githubListRepository ->
            item {
                state.user?.let { user ->
                    HeaderDetail(user = user)
                }
                Divider(
                    color = GithubProfileTheme.colors.textSecondary.copy(alpha = 0.05f),
                    thickness = 1.dp,
                    modifier = modifier.padding(horizontal = 2.dp, vertical = 14.dp)
                )
            }
            itemsIndexed(githubListRepository) { index, githubRepository ->
                GitListRepository(onClick = {}, rep = githubRepository)

                if (index != githubListRepository.size - 1) {
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

@Composable
fun GitListRepository(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    rep: GithubRepository
) {

    Row(
        modifier = modifier
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        GlideImage(
            imageModel = rep.owner_avatar,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Column(modifier = modifier.padding(horizontal = 8.dp)) {
            Text(
                text = rep.rep_name.take(14) ?: "",
                fontSize = 16.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = rep.rep_description,
                maxLines = 1,
                fontSize = 14.sp
            )
            Spacer(modifier = modifier.padding(4.dp))
            Row {
                Text(
                    text = rep.rep_stargazers_count.toString(),
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = modifier.padding(horizontal = 8.dp))
                Text(
                    text = rep.rep_updated_at ?: "null",
                    fontSize = 12.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}


@Composable
fun HeaderDetail(
    modifier: Modifier = Modifier,
    user: UserResponseDTO
) {
    Row() {
        GlideImage(
            imageModel = user.avatarUrl,
            contentScale = ContentScale.Crop,
            modifier = androidx.compose.ui.Modifier
                .size(70.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = modifier.padding(horizontal = 8.dp))

        Column() {
            Text(text = user.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "@" + user.login, fontSize = 12.sp)
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
