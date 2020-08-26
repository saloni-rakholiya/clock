package com.example.clock;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class fornotifications extends ContextWrapper {
    public static final String channel1ID="channel1ID";
    public static final String channel2ID="channel2ID";
    public static final String channel1name="channel1";
    public static final String channel2name="channel2";
    private NotificationManager mmanager;

    public fornotifications(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        createchannels();
    }



    @TargetApi(Build.VERSION_CODES.O)
    private void createchannels() {
        NotificationChannel channel1=new NotificationChannel(channel1ID,channel1name, NotificationManager.IMPORTANCE_HIGH);
        Uri defaultSoundUri=Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lovingly);
        channel1.enableLights(true);
        //here
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();

        channel1.setSound(defaultSoundUri,audioAttributes);
        //tohere
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getMmanager().createNotificationChannel(channel1);


        Uri defaultSoundUri2=Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tuhai);

        //here
        AudioAttributes audioAttributes2 = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();

        NotificationChannel channel2=new NotificationChannel(channel2ID,channel2name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setSound(defaultSoundUri2,audioAttributes2);
        channel2.setLightColor(R.color.colorPrimary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getMmanager().createNotificationChannel(channel2);

    }
    public NotificationManager getMmanager()
    {if(mmanager==null){
        mmanager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    return mmanager;
    }

    int i=0;
    public NotificationCompat.Builder getchannel1notification(String title,String message)
    {
        Intent result=new Intent(this,mathsolving.class);
        i=i+1;
        PendingIntent resultpending=PendingIntent.getActivity(this,i,result,PendingIntent.FLAG_UPDATE_CURRENT);


        Intent intent=new Intent(this,alarm.class);
        //i=i+1;
        PendingIntent content=PendingIntent.getActivity(this,i,intent,0);
        //here0
       // Uri defaultSoundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tuhai);


        return new NotificationCompat.Builder(getApplicationContext(),channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentIntent(resultpending);
                //.addAction(R.drawable.ic_delete_black_24dp,"Dismiss",resultpending)
              //  .setSound(defaultSoundUri)

    }
    public NotificationCompat.Builder getchannel2notification(String title,String message)
    {
        return new NotificationCompat.Builder(getApplicationContext(),channel2ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp);
    }


}
