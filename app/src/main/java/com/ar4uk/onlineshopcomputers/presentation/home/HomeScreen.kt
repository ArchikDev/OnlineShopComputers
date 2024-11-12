package com.ar4uk.onlineshopcomputers.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ar4uk.onlineshopcomputers.core.Constants.SIDE_PADDING
import com.ar4uk.onlineshopcomputers.presentation.components.DefaultButton
import com.ar4uk.onlineshopcomputers.presentation.home.components.CarouselSlider
import com.ar4uk.onlineshopcomputers.presentation.home.components.TopBarHome
import com.ar4uk.onlineshopcomputers.presentation.navigation.NavigationState
import com.ar4uk.onlineshopcomputers.presentation.navigation.Screen
import com.ar4uk.onlineshopcomputers.presentation.select.SelectViewModel
import com.ar4uk.onlineshopcomputers.presentation.select.State
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navigationState: NavigationState,
    viewModel: HomeViewModel
) {
    val images = listOf(
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner1.png?alt=media&token=758464f0-46e7-426f-8211-5cb7a9f9c82a",
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner1.png?alt=media&token=758464f0-46e7-426f-8211-5cb7a9f9c82a",
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner2.png?alt=media&token=42927d77-945a-49b0-8109-de049cc86756",
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner2.png?alt=media&token=42927d77-945a-49b0-8109-de049cc86756"
    )

    val checkList = viewModel.checkList.collectAsState(0)

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
                    .padding(top = 20.dp)
                    .fillMaxSize()
            ) {
                CarouselSlider(images)
                Spacer(modifier = Modifier.height(24.dp))
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = SIDE_PADDING, end = SIDE_PADDING),
                    onClick = {
                        navigationState.navigateTo(Screen.Select.route)
                    }
                ) {
                    Text(
                        text = "open"
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "${checkList.value}",
                    color = Color.White
                )
            }
        },
        bottomBar = {

        }
    )
}

