package com.ar4uk.onlineshopcomputers.presentation

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.ar4uk.onlineshopcomputers.presentation.navigation.rememberNavigationState
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.OnlineShopComputersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            OnlineShopComputersTheme(dynamicColor = false) {
                val navigationState = rememberNavigationState()
//                val navigationState = rememberNavigationState()
//                val systemController = rememberSystemUiController()

//                SideEffect {
//                    systemController.setSystemBarsColor(
//                        color = Color.Transparent,
//                        darkIcons = false
//                    )
//                }
                OnlineShopScreen(navigationState)
            }
        }
    }
}