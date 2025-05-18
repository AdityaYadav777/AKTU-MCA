package com.aditya.pdf_x.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Models.HeadLineCollection
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun TopHeadLineCollection(
    HomeViewModel: HomeViewModel,
    navController: NavHostController,
    data: String,

    ) {


    Scaffold (
        topBar = {
            MyTopBar(data,navController)
        })
    { innerPadding->
        Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.darkBlue)))
        HeadLineScreen(Modifier.padding(innerPadding), HomeViewModel,navController)

    }

}

@Composable
fun HeadLineScreen(
    modifier: Modifier,
    HomeViewModel: HomeViewModel,
    navController: NavHostController
) {
    val data=HomeViewModel.headLineCollection.collectAsStateWithLifecycle()

    Column(
        modifier=modifier
            .padding(top = 22.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp))
            .background(colorResource(R.color.sembg))
    ) {

        if (data.value.isEmpty()){


          LoadingScreen()


        }else
        LazyColumn {
            items(data.value){
                HeadLineCard(it,navController)
            }
        }
    }
}


@Composable
fun HeadLineCard(data: HeadLineCollection, navController: NavHostController) {

    val brush= Brush.verticalGradient(listOf(colorResource(R.color.darkBlue), Color.LightGray))

    Box (modifier = Modifier.fillMaxWidth().padding(start = 22.dp, end = 22.dp, top = 12.dp, bottom = 12.dp)){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(15))
                .border(width = 1.dp, brush= brush, shape = RoundedCornerShape(15))
                .background(colorResource(R.color.boxColor)),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(

                text = data.name,
                color = colorResource(R.color.borderYellow),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )


            Spacer(Modifier.height(10.dp))

            Box(Modifier.width(180.dp).height(40.dp)
                .clip(RoundedCornerShape(100))
                .border(width = 1.dp, color = colorResource(R.color.borderYellow), shape = RoundedCornerShape(100))
                .clickable {
                  Utils.url=data.url
                  navController.navigate(routes.PdfViewer.routes)
                }
                , contentAlignment = Alignment.Center){
                Text(data.description, fontSize = 20.sp)
            }





        }
    }

}