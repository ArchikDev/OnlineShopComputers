package com.ar4uk.onlineshopcomputers.presentation.select

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar4uk.onlineshopcomputers.R
import com.ar4uk.onlineshopcomputers.core.Constants.SIDE_PADDING
import com.ar4uk.onlineshopcomputers.presentation.navigation.NavigationState
import com.ar4uk.onlineshopcomputers.presentation.navigation.Screen


@Composable
fun SelectScreen(navigationState: NavigationState) {
    val paddingStatusBar = WindowInsets.systemBars
        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        .asPaddingValues()

    Scaffold(
        topBar = {
            Row(modifier = Modifier
                .padding(paddingStatusBar)
                .padding(top = 10.dp, start = SIDE_PADDING, end = SIDE_PADDING)
            ) {
                Image(
                    modifier = Modifier
                        .clickable {
                            navigationState.navigateTo(Screen.Home.route)
                        },
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = null
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {

            }
        },
        bottomBar = {

        }
    )
}