package com.aditya.pdf_x.Screens

import androidx.annotation.ColorLong
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aditya.pdf_x.R
import com.aditya.pdf_x.ViewModels.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun Home(HomeViewModel: HomeViewModel, navController: NavHostController) {

  //  val brush=Brush.linearGradient(listOf(Color.Black.copy(alpha = 0.9f),Color.White.copy(alpha = 0.3f),Color.Black))
        Column(modifier = Modifier.fillMaxSize()
            .background(Color.LightGray)
           , horizontalAlignment = Alignment.CenterHorizontally)
//            .paint(painter = painterResource(R.drawable.appbg),
//                contentScale = ContentScale.FillBounds), horizontalAlignment = Alignment.CenterHorizontally)

        {

            Spacer(Modifier.height(12.dp))

            ImageSliders(HomeViewModel,navController)

            Text("Trending", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)

            AllSemesters(HomeViewModel,navController)

    }

}


