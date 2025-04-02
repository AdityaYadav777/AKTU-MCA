package com.aditya.pdf_x.Network

import com.aditya.pdf_x.Constants
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {


    @Singleton
    @Provides
    fun FierbaseProvider(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Singleton
    @Provides
    fun getModele(): GenerativeModel {
        val model = GenerativeModel(
            modelName = "gemini-1.5-flash-001",
            apiKey = Constants.API_KEY
        )
        return model
    }


}