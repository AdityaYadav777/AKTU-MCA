package com.aditya.pdf_x

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelId="aditya"
const val channelName="aditya"



 class MyFirebaseMessagingService:FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
             generateNotification(message.notification?.title!!,message.notification?.body!!)
    }

    @SuppressLint("SuspiciousIndentation")
    fun generateNotification(title:String, message:String){

        val intent=Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent=PendingIntent.getActivity(this,0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)


        val builder=NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.lofo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setContentText(message)
            .setContentTitle(title)
            .build()
        

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel=NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }


        notificationManager.notify(100,builder)

    }
}