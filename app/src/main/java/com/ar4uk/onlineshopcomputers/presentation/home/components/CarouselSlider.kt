package com.ar4uk.onlineshopcomputers.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ar4uk.onlineshopcomputers.presentation.helpers.SIDE_PADDING
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Grey
import kotlinx.coroutines.launch

@Composable
fun CarouselSlider(images: List<String>) {
    val pagerState = rememberPagerState(initialPage = 0) {
        images.size
    }

    val scope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(
            end = 48.dp,
            start = SIDE_PADDING
        ),
        pageSpacing = 16.dp
    ) { index ->
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = images[index],
                contentDescription = null
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    PageIndicator(
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
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Grey,
    unselectedColor: Color = Green,
    onClick: (page: Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageSize) { page ->
            Box(modifier = Modifier
                .padding(horizontal = 2.dp)
                .size(14.dp)
                .clip(CircleShape)
                .clickable { onClick(page) }
                .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}
