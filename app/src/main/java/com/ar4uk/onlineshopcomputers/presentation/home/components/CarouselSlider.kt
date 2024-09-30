package com.ar4uk.onlineshopcomputers.presentation.home.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Size
import com.ar4uk.onlineshopcomputers.presentation.helpers.SIDE_PADDING
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Grey
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CarouselSlider(images: List<String>) {
    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current
    var imgs = remember { mutableStateListOf<ImageRequest>() }

    scope.launch {
        imgs = images.map { img ->
            async {
                ImageRequest
                    .Builder(ctx)
                    .data(img)
                    .size(Size.ORIGINAL)
                    .build()
            }
        }.map {
            it.await()
        }.toMutableList() as SnapshotStateList<ImageRequest>

        Log.d("imgs", imgs.toString())
    }



    if (imgs.isNotEmpty()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            imgs.size
        }


        Log.d("imgs", "111")

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(
                end = 48.dp,
                start = SIDE_PADDING
            ),
            pageSpacing = 16.dp
        ) { index ->
//        val model = ImageRequest
//            .Builder(LocalContext.current)
//            .data(images[index])
//            .size(Size.ORIGINAL)
//            .build()
        val imageState = rememberAsyncImagePainter(model = imgs[index]).state


            imageState.painter?.let {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    painter = it,
                    contentDescription = null
                )
            }


//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(14.dp))
//                .background(MaterialTheme.colorScheme.secondaryContainer),
//        ) {
//            if (imageState is AsyncImagePainter.State.Success) {
//                Image(
//                    modifier = Modifier.fillMaxWidth(),
//                    contentScale = ContentScale.Crop,
//                    painter = imageState.painter,
//                    contentDescription = null
//                )
//            }
//
//            if (imageState is AsyncImagePainter.State.Loading) {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .heightIn(min = 140.dp)
//                        .size(50.dp)
//                        .align(Alignment.Center),
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//
//            if (imageState is AsyncImagePainter.State.Error) {
//                Icon(
//                    imageVector = Icons.Rounded.Warning,
//                    contentDescription = null
//                )
//            }
////            AsyncImage(
////                modifier = Modifier.fillMaxWidth(),
////                model = images[index],
////                contentDescription = null
////            )
//        }
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
