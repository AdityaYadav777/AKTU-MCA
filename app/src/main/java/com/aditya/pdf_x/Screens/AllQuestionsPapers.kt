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
import com.aditya.pdf_x.Models.AllQuestionModel
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel

@Composable
fun AllQuestionsPapers(HomeViewModel: HomeViewModel, navController: NavHostController) {


    val questions = HomeViewModel.allQuestions.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {

            MyTopBar("All Questions Papers",navController)
        }
    ) { innerPadding ->


        if (questions.value.isEmpty()) {
          LoadingScreen()
        } else
            Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.darkBlue)))
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 30.dp)
                    .clip(RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp))
                    .background(colorResource(R.color.sembg))
                    .fillMaxSize()
            ) {
                items(questions.value) {
                    itemView(it, navController)
                }
            }
    }
}


@Composable
fun itemView(allQuestionModel: AllQuestionModel, navController: NavHostController) {

    val year=allQuestionModel.name.subSequence(allQuestionModel.name.length-4,allQuestionModel.name.length)
    val name=allQuestionModel.name.subSequence(0,allQuestionModel.name.length-4)

    val brush=Brush.verticalGradient(listOf(colorResource(R.color.darkBlue),Color.LightGray))

    Box(modifier = Modifier.fillMaxWidth().height(180.dp).padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 30.dp)
        .clip(RoundedCornerShape(20))
        .border(width = 1.dp, brush = brush, shape = RoundedCornerShape(20))
        .background(
        colorResource(R.color.boxColor)
    ), contentAlignment = Alignment.Center){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    Utils.url = allQuestionModel.url
                    navController.navigate(routes.PdfViewer.routes)
                }
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Spacer(Modifier.width(20.dp))

            Text(
                text= name.toString(),
                color = Color.White,
                fontSize = 22.sp,
                style = MaterialTheme.typography.titleLarge
            )


            Spacer(Modifier.height(12.dp))


            Text(
               text= "Question Papers - $year",
                color = colorResource(R.color.borderYellow),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge
                )
        }
    }


}

