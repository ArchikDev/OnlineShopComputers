package com.ar4uk.onlineshopcomputers.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ar4uk.onlineshopcomputers.presentation.home.HomeScreen
import com.ar4uk.onlineshopcomputers.presentation.home.components.TopBarHome
import com.ar4uk.onlineshopcomputers.presentation.navigation.AppNavGraph
import com.ar4uk.onlineshopcomputers.presentation.navigation.NavigationState
import com.ar4uk.onlineshopcomputers.presentation.select.SelectScreen
import com.ar4uk.onlineshopcomputers.presentation.select.SelectViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnlineShopScreen(navigationState: NavigationState) {
    AppNavGraph(
        navHostController = navigationState.navHostController,
        onboardScreenContent = { OnboardingScreen(navigationState) },
        homeScreenContent = {
//                    val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navigationState)
        },
        selectScreenContent = {
            val viewModel: SelectViewModel = hiltViewModel()

            SelectScreen(navigationState, viewModel)
        }
//                fullImageScreenContent = {
//                    val fullImageViewModel: FullImageViewModel = hiltViewModel()
//
//                    FullImageScreen(
//                        image = fullImageViewModel.image,
//                        onBackClick = { navigationState.navHostController.popBackStack() },
//                        onImageDownloadClick = { url, title ->
//                            fullImageViewModel.downloadImage(url, title)
//                        }
//                    )
//                },
//                fullVideoScreenContent = {
//                    val fullImageViewModel: FullImageViewModel = hiltViewModel()
//
//                    fullImageViewModel.video?.let { it1 ->
//                        FullVideoScreen(
//                            video = it1,
//                            onBackClick = { navigationState.navHostController.popBackStack() },
//                        )
//                    }
//                },
    )

}