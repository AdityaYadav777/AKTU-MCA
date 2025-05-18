package com.aditya.pdf_x.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Splash(navController: NavHostController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkBlue)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LaunchedEffect(true) {
            delay(3500)
            navController.navigate(routes.Home.routes) {
                popUpTo(routes.Splash.routes) {
                    inclusive = true
                }
            }
        }

 Image(painter = painterResource(R.drawable.spalsh),null, modifier = Modifier.size(220.dp)
 )
        Spacer(Modifier.height(10.dp))
 LinearDeterminateIndicator()

    }


}

@Composable
fun LinearDeterminateIndicator() {
    var currentProgress by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {



        LaunchedEffect (true){
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }

            }
        }


            LinearProgressIndicator(

                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth() .height(12.dp).padding(horizontal = 50.dp).clip(RoundedCornerShape(40)),
                strokeCap = StrokeCap.Round,
                trackColor = Color.LightGray,
                color = Color.White
            )


        }
    }



suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(24)
        if (i==50){
            delay(1000)
        }

    }
}