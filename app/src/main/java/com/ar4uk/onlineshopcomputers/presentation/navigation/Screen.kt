package com.ar4uk.onlineshopcomputers.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Onboard: Screen(ROUTE_ONBOARD)
    data object Home: Screen(ROUTE_HOME)
    data object Select: Screen(ROUTE_SELECT)
//    data object FullImageScreen: Screen(ROUTE_FULL_IMAGE_SCREEN) {
//
//        private const val ROUTER_FOR_ARGS = "full_image_screen"
//
//        fun getRouteWithArgs(imageId: String): String {
//            return "$ROUTER_FOR_ARGS/${imageId}"
//        }
//    }
//
//    data object FullVideoScreen: Screen(ROUTE_FULL_VIDEO_SCREEN) {
//
//        private const val ROUTER_FOR_ARGS = "full_video_screen"
//
//        fun getRouteWithArgs(videoId: String): String {
//            return "$ROUTER_FOR_ARGS/${videoId}"
//        }
//    }
    companion object {

//        const val KEY_IMAGE_ID = "key_image_id"
//        const val KEY_VIDEO_ID = "key_video_id"
        const val ROUTE_ONBOARD = "onboard"
        const val ROUTE_HOME = "home"
        const val ROUTE_SELECT = "select"
//        const val ROUTE_FULL_IMAGE_SCREEN = "full_image_screen/{$KEY_IMAGE_ID}"
//        const val ROUTE_FULL_VIDEO_SCREEN = "full_video_screen/{$KEY_VIDEO_ID}"
    }
}