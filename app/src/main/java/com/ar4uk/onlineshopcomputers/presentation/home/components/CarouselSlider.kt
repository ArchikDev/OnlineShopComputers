package com.ar4uk.onlineshopcomputers.presentation.home.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.ar4uk.onlineshopcomputers.presentation.helpers.SIDE_PADDING
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Grey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.math.log

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CarouselSlider(images: List<String>) {
    val ctx = LocalContext.current

    var isLoading by remember { mutableStateOf(true) }
    var listImages = remember { mutableStateListOf<ImageRequest?>() }


    loadImages(
        context = ctx,
        imageURLs = images
    ) { loadedImages ->
        listImages.addAll(loadedImages)
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .heightIn(min = 140.dp)
                    .size(50.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    } else {
        Log.d("deferredImages", listImages.toString())
        Slider(images = listImages)
    }

//    if (isLoading) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(14.dp))
//                .background(MaterialTheme.colorScheme.secondaryContainer),
//        ) {
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .heightIn(min = 140.dp)
//                    .size(50.dp)
//                    .align(Alignment.Center),
//                color = MaterialTheme.colorScheme.primary
//            )
//        }
//
//    }

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

@Composable
fun Slider(
    images: List<ImageRequest?>
) {
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(initialPage = 0) {
        images.size
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(
            end = 48.dp,
            start = SIDE_PADDING
        ),
        pageSpacing = 16.dp
    ) { index ->
        val imageState = rememberAsyncImagePainter(model = images[index]).state
        imageState.painter?.let {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = it,
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

fun loadImages(
    context: Context,
    imageURLs: List<String>,
    callback: (List<ImageRequest?>) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val deferredImages = imageURLs.map { url ->
            async {
                try {
                    ImageRequest
                        .Builder(context)
                        .data(url)
                        .size(Size.ORIGINAL)
                        .build()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }
        val loadedImages: List<ImageRequest?> = deferredImages.awaitAll()

        callback(loadedImages)
    }
}