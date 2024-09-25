package com.ar4uk.onlineshopcomputers.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnlineShopScreen() {
    Scaffold {
        Box {
            OnboardingScreen()
        }
    }
}