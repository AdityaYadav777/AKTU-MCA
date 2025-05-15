package com.aditya.pdf_x.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aditya.pdf_x.R
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun Home(homeViewModel: HomeViewModel, navController: NavHostController) {



    // 🎨 Dynamic Wave Motion Background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkBlue))
    ) {
        // 🌀 Add a wave animation layer


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(12.dp))

            // 📸 Shimmering Image Slider (during loading)
            ShimmerEffect {
                Column {
                    ImageSliders(homeViewModel, navController)

                }
            }

            Spacer(Modifier.height(12.dp))

            // 🔥 Trending Text with Animation
            AnimatedVisibility(visible = true, enter = fadeIn() + slideInHorizontally()) {
                Text(
                    "🔥 Trending",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,

                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // 📚 Shimmering Semester List
            ShimmerEffect { AllSemesters(homeViewModel, navController) }
        }
    }
}


@Composable
fun ShimmerEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val shimmerColor = infiniteTransition.animateColor(
        initialValue = Color.Gray.copy(alpha = 0.6f),
        targetValue = Color.White.copy(alpha = 0.3f),
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Shimmer"
    )

    Box(
        modifier = Modifier
            .background(brush = Brush.linearGradient(listOf(shimmerColor.value, Color.Transparent)))
            .clip(RoundedCornerShape(12.dp))
    ) {
        content()
    }
}


@Composable
fun WaveBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,  // Adjust this for speed
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "WaveAnimation"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        val wavePath = Path().apply {
            moveTo(-waveOffset, size.height * 0.8f)
            for (i in 0..size.width.toInt() step 100) {
                quadraticBezierTo(
                    x1 = i.toFloat() + 50f,
                    y1 = size.height * 0.7f,
                    x2 = i.toFloat() + 100f,
                    y2 = size.height * 0.8f
                )
            }
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        drawPath(wavePath, color = Color(0xFF1E88E5).copy(alpha = 0.3f)) // Light blue waves
    }
}

