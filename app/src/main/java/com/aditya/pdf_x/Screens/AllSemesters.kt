package com.aditya.pdf_x.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
        modifier = Modifier.fillMaxSize()
            .padding(top = 55.dp)
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(colorResource(id = R.color.sembg)),
             horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        Box( modifier = Modifier.align(Alignment.Start).width(150.dp).height(50.dp) .padding( start = 18.dp, top = 16.dp).clip(
            RoundedCornerShape(100)
        ).background(colorResource(R.color.borderYellow).copy(alpha = 0.6f)), contentAlignment = Alignment.Center){

            Text("All Semester", color = Color.White, fontSize = 20.sp)

        }

        Spacer(Modifier.height(16.dp))

        if (data.value.isEmpty()) {
          LoadingScreen()
        } else
            LazyColumn(modifier = Modifier) {
                items(data.value) {
                    ItemView(it, HomeViewModel, navController)
                }
            }
    }
}


@Composable
fun ItemView(data: SemesterModel, homeViewModel: HomeViewModel, navController: NavHostController) {

    val sems=data.name.subSequence(3,14)
    val mca=data.name.subSequence(0,4)


    val brush=Brush.verticalGradient(listOf(Color.Black,Color.LightGray))
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
        .border(width = 1.dp, brush =brush , shape = RoundedCornerShape(20))
        .clip(RoundedCornerShape(20))
        .background(colorResource(R.color.boxColor))

        .clickable {
            homeViewModel.getSubjects(data.name)
            Utils.sememsterName = data.name
            Log.i("SEMESTER", data.name)
            navController.navigate(routes.Subjects.routes)
        }
        ,
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = mca.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 40.dp)
                        .align(Alignment.Start)
                )

                Spacer(Modifier.height(3.dp))

                Text(
                    text = sems.toString(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 38.dp)
                        .align(Alignment.Start)
                )
            }

            Image(painter = painterResource(R.drawable.vector),null, modifier = Modifier.size(60.dp).padding(end = 22.dp))
        }


    }
}