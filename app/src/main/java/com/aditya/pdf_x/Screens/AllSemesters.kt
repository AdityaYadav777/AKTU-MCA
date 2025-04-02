package com.aditya.pdf_x.Screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Models.SemesterModel
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun AllSemesters(HomeViewModel: HomeViewModel, navController: NavHostController) {
    val data = HomeViewModel.semeterData.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "All Semester",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 12.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
        )
        if (data.value.isEmpty()) {
            CircularProgressIndicator()
        } else
            LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.padding(bottom = 100.dp)) {
                items(data.value) {
                    ItemView(it, HomeViewModel, navController)
                }
            }
    }
}


@Composable
fun ItemView(data: SemesterModel, homeViewModel: HomeViewModel, navController: NavHostController) {
    val scale = remember { Animatable(1f) }
    Card(modifier = Modifier
        .size(200.dp)
        .padding(12.dp)
        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(7))
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    scale.animateTo(0.9f, animationSpec = tween(100))
                    scale.animateTo(1f, animationSpec = tween(100))
                }
            )
        }
        .graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        }
        .clickable {
            homeViewModel.getSubjects(data.name)
            Utils.sememsterName = data.name
            Log.i("SEMESTER", data.name)
            navController.navigate(routes.Subjects.routes)
        }
        ,
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp)

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.cardbg),
                    contentScale = ContentScale.FillBounds
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                data.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }

    }
}