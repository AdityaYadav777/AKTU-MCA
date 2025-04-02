package com.aditya.pdf_x.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import kotlinx.coroutines.delay


@Composable
fun Splash(navController: NavHostController) {

    val brush =
        Brush.linearGradient(listOf(Color.Black, Color.Blue.copy(alpha = 0.8f), Color.Black))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        LaunchedEffect(true) {
            delay(1000)
            navController.navigate(routes.Home.routes) {
                popUpTo(routes.Splash.routes) {
                    inclusive = true
                }
            }
        }


        AnimatedVisibility(
            true,
            enter = scaleIn(tween(4000)),
            exit = scaleOut(tween(5000))
        ) {
            Image(
                painter = painterResource(R.drawable.lofo), contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

        }


    }


}