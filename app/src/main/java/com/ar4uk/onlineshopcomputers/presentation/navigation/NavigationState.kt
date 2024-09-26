package com.ar4uk.onlineshopcomputers.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.startDestinationId) {
                inclusive =  true
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }

//    fun navigateToFullImage(imageId: String) {
//        navHostController.navigate(Screen.FullImageScreen.getRouteWithArgs(imageId))
//    }
//
//    fun navigateToFullVideo(videoId: String) {
//        navHostController.navigate(Screen.FullVideoScreen.getRouteWithArgs(videoId))
//    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}