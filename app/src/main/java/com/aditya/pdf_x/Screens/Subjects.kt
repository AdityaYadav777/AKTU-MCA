package com.aditya.pdf_x.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import com.aditya.pdf_x.Models.SubjectModel
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun Subjects(homeViewModel: HomeViewModel, navController: NavHostController) {

    val subjects = homeViewModel.subjects.collectAsStateWithLifecycle()


    Scaffold(

        topBar = {
            MyTopBar("Subject",navController)
        }
    ) { innerPadding ->

        Box(Modifier.fillMaxSize().background(colorResource(R.color.darkBlue)))

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(colorResource(R.color.sembg)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (subjects.value.isEmpty()) {
               LoadingScreen()
            } else
                LazyColumn(modifier = Modifier.padding(12.dp)) {
                    items(subjects.value) { data ->
                        ItemView(data, navController, homeViewModel)
                    }
                }
        }
    }


}


@Composable
fun ItemView(data: SubjectModel, navController: NavHostController, homeViewModel: HomeViewModel) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(230.dp)
        .padding(start = 12.dp, end = 12.dp, bottom = 16.dp, top = 2.dp)
        .clip(RoundedCornerShape(12))
        .border(width = 1.dp, color = colorResource(R.color.borderYellow), shape = RoundedCornerShape(12))
        .clickable {
            homeViewModel.getAllQuestions(data.name)
            navController.navigate(routes.AllQuestionsPapers.routes)

        }

    ) {

        SubcomposeAsyncImage(
            model = data.url,//dfg

            loading = {
                Box(modifier = Modifier.size(60.dp), contentAlignment = Alignment.Center){
                    LoadingScreen()

                }
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {


            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter){
                Box(modifier = Modifier.width(300.dp).height(60.dp).padding(start = 12.dp, end = 12.dp, top = 12.dp).clip(RoundedCornerShape(100)) .background(colorResource(R.color.subjectTitleColor).copy(alpha = 0.7f)), contentAlignment = Alignment.Center){
                    Text(
                        data.name,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }


            BottomSectionOfSubjectItem(homeViewModel,data,navController)

        }
    }
}


@Composable
fun BottomSectionOfSubjectItem(
    homeViewModel: HomeViewModel,
    data: SubjectModel,
    navController: NavHostController
) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){

        Row (modifier = Modifier.fillMaxWidth().height(60.dp).background(colorResource(R.color.subjectTitleColor)).padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){

            Box(modifier = Modifier.width(120.dp).height(50.dp)
                .clip(RoundedCornerShape(100))
                .border(width = 1.dp, color = colorResource(R.color.borderYellow), shape = RoundedCornerShape(100)).weight(1f)
                .clickable {

                    homeViewModel.getAllNotes(data.name)
                    navController.navigate(routes.AllNotes.routes)

                }
                , contentAlignment = Alignment.Center){

                Text(
                    text = "Notes",
                    color = Color.White
                )
            }

            Spacer(Modifier.width(18.dp))

            Box(modifier = Modifier.width(120.dp).height(50.dp).border(width = 1.dp, color = colorResource(R.color.borderYellow), shape = RoundedCornerShape(100)).weight(1f), contentAlignment = Alignment.Center){
                Text(
                    text = "Question Papers",
                    color = Color.White
                )
            }


        }


    }


}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title: String, navController: NavHostController,){

    TopAppBar(

        navigationIcon = {
            IconButton(onClick ={
           navController.popBackStack()
            } ) {
                Icon(Icons.Default.ArrowBack,null, tint = Color.White)
            }
        },
        title = {
            Text(title, fontWeight = FontWeight.ExtraBold, color = Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(R.color.darkBlue))
    )

}