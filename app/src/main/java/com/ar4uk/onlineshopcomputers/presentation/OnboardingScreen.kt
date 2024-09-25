package com.ar4uk.onlineshopcomputers.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ar4uk.onlineshopcomputers.R

@Composable
fun OnboardingScreen() {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.intro_pic),
            contentScale = ContentScale.Fit,
            alignment = Alignment.BottomCenter,
            contentDescription = null
        )
    }
}