package com.ar4uk.onlineshopcomputers.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ar4uk.onlineshopcomputers.presentation.home.components.TopBarHome
import com.ar4uk.onlineshopcomputers.presentation.navigation.NavigationState
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navigationState: NavigationState
) {
    val images = listOf(
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner1.png?alt=media&token=758464f0-46e7-426f-8211-5cb7a9f9c82a",
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner1.png?alt=media&token=758464f0-46e7-426f-8211-5cb7a9f9c82a",
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner2.png?alt=media&token=42927d77-945a-49b0-8109-de049cc86756",
        "https://firebasestorage.googleapis.com/v0/b/onlineshopcomputers.appspot.com/o/banner2.png?alt=media&token=42927d77-945a-49b0-8109-de049cc86756"
    )
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
            ) {
                Carousel(images)
            }
        },
        bottomBar = {

        }
    )
}

@Composable
fun CarouselSlider(images: List<String>) {
    var index by remember { mutableIntStateOf(0) }

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            delay(1000)
            if (index == images.size - 1) index = 0
            else index ++

            scrollState.animateScrollToItem(index)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(images) { index, image ->
                    Card(
                        modifier = Modifier.height(300.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                        AsyncImage(
                            model = image,
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.width(300.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Carousel(images: List<String>) {
    val pagerState = rememberPagerState(initialPage = 0) {
        images.size
    }
    val scope = rememberCoroutineScope()
    HorizontalPager(state = pagerState) { index ->
        Card(
            modifier = Modifier.height(300.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            AsyncImage(
                model = images[index],
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.width(300.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
    PageIndicator(
        modifier = Modifier.width(52.dp),
        pageSize = images.size,
        selectedPage = pagerState.currentPage
    ) { pageInt ->
        scope.launch {
            pagerState.animateScrollToPage(page = pageInt)
        }

    }
}

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Green,
    onClick: (page: Int) -> Unit
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) { page ->
            Box(modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .clickable { onClick(page)  }
                .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}
