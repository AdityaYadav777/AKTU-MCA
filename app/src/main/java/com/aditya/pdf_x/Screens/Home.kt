package com.aditya.pdf_x.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aditya.pdf_x.R
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun Home(homeViewModel: HomeViewModel, navController: NavHostController) {




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkBlue))
    ) {



        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(12.dp))



                Column {
                    ImageSliders(homeViewModel, navController)


            }

            Spacer(Modifier.height(12.dp))





             AllSemesters(homeViewModel, navController)
        }
    }
}

