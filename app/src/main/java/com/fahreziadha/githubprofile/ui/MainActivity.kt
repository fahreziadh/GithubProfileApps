package com.fahreziadha.githubprofile.ui

import GithubProfileTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.fahreziadha.githubprofile.utils.CustomSurface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubProfileTheme() {
                CustomSurface(color = GithubProfileTheme.colors.uiFloated) {
                    Text(text = "Astaghfirullah")
                }
            }
        }
    }
}