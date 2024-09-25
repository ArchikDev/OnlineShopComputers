package com.ar4uk.onlineshopcomputers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.OnlineShopComputersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineShopComputersTheme(dynamicColor = false) {
//                val navigationState = rememberNavigationState()
//                val systemController = rememberSystemUiController()

//                SideEffect {
//                    systemController.setSystemBarsColor(
//                        color = Color.Transparent,
//                        darkIcons = false
//                    )
//                }
                OnlineShopScreen()
            }
        }
    }
}