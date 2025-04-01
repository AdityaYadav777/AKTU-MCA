package com.aditya.pdf_x.Navigation

sealed class routes(val routes:String) {

    object  Home:routes("Home")
    object  Splash:routes("Splash")
    object Subjects:routes("Subjects")
    object PdfViewer:routes("PdfViewer")
    object AllQuestionsPapers:routes("AllQuestionsPapers")

}