package com.aditya.pdf_x.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun ImageSliders(HomeViewModel: HomeViewModel, navController: NavHostController) {


    val slider = HomeViewModel.slider.collectAsStateWithLifecycle()

    val state = rememberPagerState(initialPage = 0, pageCount = { slider.value.size })

    HorizontalPager(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 30.dp, start = 12.dp, end = 12.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10))
      ,
        verticalAlignment = Alignment.CenterVertically

    ) { page ->

        SubcomposeAsyncImage(modifier = Modifier
            .clip(shape = RoundedCornerShape(10))
            .clickable {
                Utils.url=slider.value[page].url
                navController.navigate(routes.PdfViewer.routes)
            },
            model = slider.value[page].img, loading = { Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {CircularProgressIndicator()   } }, contentDescription = null, contentScale = ContentScale.FillBounds)


    }

    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(state.pageCount) { iteration ->
            val color = if (state.currentPage == iteration) Color.White else Color.DarkGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }
    }
}






