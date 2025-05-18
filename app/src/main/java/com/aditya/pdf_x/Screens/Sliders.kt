package com.aditya.pdf_x.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aditya.pdf_x.Navigation.routes
import com.aditya.pdf_x.R
import com.aditya.pdf_x.ViewModels.HomeViewModel


@Composable
fun ImageSliders(HomeViewModel: HomeViewModel, navController: NavHostController) {


    Box(modifier = Modifier.fillMaxWidth().height(300.dp).padding(top = 36.dp, start = 20.dp, end = 20.dp)){
        Image(painter = painterResource(R.drawable.rectangle),null, contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())
        Box(modifier = Modifier.fillMaxSize().clip(
            RoundedCornerShape(11))
            .background(colorResource(id = R.color.sembg).copy(alpha = 0.7f))) {
            Image(painter = painterResource(R.drawable.aktu),null, modifier = Modifier.align(Alignment.Center).alpha(0.2f))
             TextOnBanner()
             ButtonsOnBanner(navController,HomeViewModel)
        }
    }
}



@Composable
fun TextOnBanner(){
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 6.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text="DR. A.P.J AKTU",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
            )


        Text(
            text="UTTAR PRADESH, LUCKNOW",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(50.dp))


    }
}


@Composable
fun ButtonsOnBanner(navController: NavHostController, HomeViewModel: HomeViewModel) {

    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween) {


        Box(modifier = Modifier.weight(1f).width(180.dp).height(50.dp) .padding(bottom = 12.dp, start = 12.dp)
            .clip(RoundedCornerShape(100))
            .border(width = 1.dp, color = colorResource(R.color.borderYellow), shape = RoundedCornerShape(100)).clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(color = colorResource(R.color.borderYellow)),
        ) {
                //click Action
                HomeViewModel.getHeadlineCollection("Syllabus")
                navController.navigate(route =routes.TopHeadLineCollection.routes + "/Syllabus")



        }, contentAlignment = Alignment.Center){
            Text("View Syllabus",color=Color.White)
        }

        Box(modifier = Modifier.weight(1f).width(180.dp).height(50.dp) .padding(bottom = 12.dp, start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(100))
            .border(width = 1.dp, color = colorResource(R.color.borderYellow), shape = RoundedCornerShape(100)).clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = colorResource(R.color.borderYellow)),
            ) {

                //click Action
                HomeViewModel.getHeadlineCollection("Datesheet")
                navController.navigate(route =routes.TopHeadLineCollection.routes + "/Datesheet")

        }, contentAlignment = Alignment.Center){

            Text("Datesheet",color=Color.White)

        }

    }

}




