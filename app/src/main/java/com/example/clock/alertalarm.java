package com.example.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Config;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class alertalarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        fornotifications n=new fornotifications(context);
        NotificationCompat.Builder nb=n.getchannel1notification("WAKE UP ","TIME TO GET UP");
        Vibrator vibrator=(Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(10000);

       /* Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        nb.setSound(uri);*/
        n.getMmanager().notify(1,nb.build());


    }
}
