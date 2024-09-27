package com.ar4uk.onlineshopcomputers.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar4uk.onlineshopcomputers.presentation.home.components.TopBarHome
import com.ar4uk.onlineshopcomputers.presentation.navigation.NavigationState

@Composable
fun HomeScreen(
    navigationState: NavigationState
) {
    Scaffold(
        topBar = {
            TopBarHome(
                navigationState = navigationState
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) { }
        },
        bottomBar = {

        }
    )
}