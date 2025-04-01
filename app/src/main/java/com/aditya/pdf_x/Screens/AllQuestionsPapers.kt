package com.aditya.pdf_x.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Models.AllQuestionModel
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AllQuestionsPapers(HomeViewModel: HomeViewModel, navController: NavHostController) {


    val questions=HomeViewModel.allQuestions.collectAsStateWithLifecycle()


    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("All Question Papers", fontWeight = FontWeight.ExtraBold) }, colors = TopAppBarDefaults.topAppBarColors(
            Color.Black.copy(alpha = 0.7f))) }
    ) {innerPadding->


      if (questions.value.isEmpty()){
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
              CircularProgressIndicator()
          }
      }else
      LazyColumn (modifier = Modifier.padding(innerPadding).padding(top = 10.dp)   .background(Color.LightGray)

          .fillMaxSize()){
          items(questions.value){
              itemView(it,navController)
          }
      }
    }
}



@Composable
fun itemView(allQuestionModel: AllQuestionModel, navController: NavHostController) {

  Row(modifier = Modifier.fillMaxWidth()

      .padding(8.dp) .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(20)).height(70.dp).
  clickable {
      Utils.url=allQuestionModel.url
      navController.navigate(routes.PdfViewer.routes )
  }.padding(16.dp)

  ) {

        Image(painter = painterResource(R.drawable.pdf), contentDescription = null,)

        Spacer(Modifier.width(20.dp))

        Text(allQuestionModel.name, color = Color.Black, modifier = Modifier.align(Alignment.CenterVertically))

  }

}

