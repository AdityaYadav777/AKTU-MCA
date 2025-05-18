package com.aditya.pdf_x.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Models.NoteModel
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.Utils
import com.aditya.pdf_x.ViewModels.HomeViewModel

@Composable
fun AllNotes(homeViewModel: HomeViewModel, navController: NavHostController) {

    val notes=homeViewModel.getNote.collectAsStateWithLifecycle()

    Scaffold (
        topBar = { MyTopBar("Notes",navController) }
    ) { innerPadding->

        Box(Modifier.fillMaxSize().background(colorResource(R.color.darkBlue)))

        Column (modifier = Modifier.padding(innerPadding).fillMaxSize().padding(top = 12.dp)
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(
            colorResource(R.color.subjectTitleColor)
        )) {

            if (notes.value.isEmpty()) {
             LoadingScreen()
            } else{
                    LazyColumn {
                    items(notes.value){
                    Notes(it,navController)

                    }

                }
            }
        }
    }
}


@Composable
fun Notes(data: NoteModel, navController: NavHostController) {


    Box(modifier = Modifier.fillMaxWidth().padding(start = 22.dp, end = 22.dp, top = 14.dp).height(140.dp)){
        Column (modifier = Modifier.fillMaxWidth().padding(4.dp).height(140.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(colorResource(R.color.boxColor))

            .clickable {
                Utils.url=data.url
                navController.navigate(route = routes.PdfViewer.routes)
            }
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            Text(
                text = data.name,
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


            Spacer(Modifier.height(2.dp))

            Text(
                text = data.unitName,
                fontSize = 20.sp,
                color = colorResource(R.color.borderYellow),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LoadingScreen(){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            strokeWidth = 14.dp,
            trackColor = Color.LightGray,
            color = Color.White

        )
    }
}