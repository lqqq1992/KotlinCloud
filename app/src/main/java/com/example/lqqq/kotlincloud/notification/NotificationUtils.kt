package com.example.lqqq.kotlincloud.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.lqqq.kotlincloud.MainActivity
import com.example.lqqq.kotlincloud.R

class NotificationUtils(private val context: Context) {
    companion object{
        private const val CHANNEL_ID = "android_cloud_channel_id"
        private const val CHANNEL_NAME = "android_cloud_name"
        private const val CHANNEL_DESC = "android_cloud_desc"
        private const val NOTIFICATION_ID = 1
    }
    private val manager: NotificationManagerCompat
        get() = NotificationManagerCompat.from(context)
    /**
     * 设置通知渠道
     */
    private fun setNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.description = CHANNEL_DESC
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
    fun createNotification(){
        setNotificationChannel()
        val remoteViews = RemoteViews(context.packageName, R.layout.notification_view)
        val smallRemoteViews = RemoteViews(context.packageName, R.layout.notification_view_small)
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_ONE_SHOT)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(1,2,3))
            .addAction(R.drawable.music_favorite_24,"1",pendingIntent)
            .addAction(R.drawable.music_favorite_24,"2",pendingIntent)
            .addAction(R.drawable.music_favorite_24,"3",pendingIntent)
            .addAction(R.drawable.music_favorite_24,"4",pendingIntent)
            .addAction(R.drawable.music_favorite_24,"5",pendingIntent)
            .setContentTitle("123")
            .setContentText("456")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.mipmap.ic_launcher))
//            .setOngoing(true)
//            .setCustomBigContentView(remoteViews)
//            .setCustomContentView(smallRemoteViews)
//            .setShowWhen(true)
            .setPriority(NotificationCompat.PRIORITY_MAX)
        val notification: Notification = builder.build()
        manager.notify(NOTIFICATION_ID,notification)
    }
}