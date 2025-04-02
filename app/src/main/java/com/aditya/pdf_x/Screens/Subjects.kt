package com.aditya.pdf_x.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Subjects(homeViewModel: HomeViewModel, navController: NavHostController) {

    val subjects = homeViewModel.subjects.collectAsStateWithLifecycle()


    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {

                    Text("Subjects", fontWeight = FontWeight.ExtraBold)

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black.copy(alpha = 0.7f))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (subjects.value.isEmpty()) {
                CircularProgressIndicator(color = Color.Black)
            } else
                LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.padding(12.dp)) {
                    items(subjects.value) { data ->
                        ItemView(data, navController, homeViewModel)
                    }

                }
        }
    }


}


@Composable
fun ItemView(data: SubjectModel, navController: NavHostController, homeViewModel: HomeViewModel) {
    Card(modifier = Modifier
        .size(170.dp)
        .padding(12.dp)
        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(7))
        .clickable {


            homeViewModel.getAllQuestions(data.name)
            navController.navigate(routes.AllQuestionsPapers.routes)


        }

    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.cardbg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            SubcomposeAsyncImage(
                model = data.url,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(50))
            )
            Text(
                data.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 19.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

        }


    }
}