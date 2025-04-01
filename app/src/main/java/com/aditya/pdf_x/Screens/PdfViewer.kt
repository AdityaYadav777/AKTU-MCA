package com.aditya.pdf_x.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aditya.pdf_x.Utils
import com.rajat.pdfviewer.compose.PdfRendererViewCompose


@Composable
fun PdfViewer() {
        val url=Utils.url?:""
        myPdf2(url)

}






@Composable
fun myPdf2(url:String){


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding->

        Box(modifier = Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {

            CircularProgressIndicator()

            PdfRendererViewCompose(
                url = url,
            )

        }

        }

}