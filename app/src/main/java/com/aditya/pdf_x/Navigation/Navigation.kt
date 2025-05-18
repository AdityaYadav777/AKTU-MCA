package com.aditya.pdf_x.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditya.pdf_x.Screens.AllNotes
import com.aditya.pdf_x.Screens.AllQuestionsPapers
import com.aditya.pdf_x.Screens.Home
import com.aditya.pdf_x.Screens.PdfViewer
import com.aditya.pdf_x.Screens.Splash
import com.aditya.pdf_x.Screens.Subjects
import com.aditya.pdf_x.Screens.TopHeadLineCollection
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun Navigation(HomeViewModel: HomeViewModel) {

    val navController = rememberNavController()
    NavHost(
        navController, startDestination = routes.Splash.routes,

        //    enterTransition = { slideInHorizontally(animationSpec = tween(300))}, exitTransition = { slideOutVertically  (animationSpec = tween(1000)) }

    ) {
        composable(routes.Splash.routes) {
            Splash(navController)
        }

        composable(routes.Home.routes) {
            Home(HomeViewModel, navController)
        }

        composable(routes.Subjects.routes) {
            Subjects(HomeViewModel, navController)
        }

        composable(routes.PdfViewer.routes) {
            PdfViewer(HomeViewModel)
        }
        composable(routes.AllQuestionsPapers.routes) {
            AllQuestionsPapers(HomeViewModel, navController)
        }

        composable(routes.TopHeadLineCollection.routes + "/{data}"){
           val data=it.arguments?.getString("data")?:""
            TopHeadLineCollection(HomeViewModel,navController, data)
        }

        composable(routes.AllNotes.routes){
            AllNotes(HomeViewModel,navController)
        }

    }


}