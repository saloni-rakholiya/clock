package com.example.clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity /*implements TimePickerDialog.OnTimeSetListener*/ {

    private Button btn;
    private Button cancel;
    private TextView tv;
    private fornotifications mnotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView b=findViewById(R.id.bottom_nav);
        b.setOnNavigationItemSelectedListener(navlisten);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragcontainer,new alarm()).commit();


        /*tv=findViewById(R.id.textView);

        cancel=findViewById(R.id.button2);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timepicker=new fortime();
                timepicker.show(getSupportFragmentManager(),"timepicker");
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelalarmm();
            }
        });


        mnotification=new fornotifications(this);*/
       /* sendonchannel1("1","heyyyyyyyyyy");
        sendonchannel2("2","byeeeeeeeeeeee");*/
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlisten=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedfrag=null;
                    switch (menuItem.getItemId())
                    {
                        case R.id.alarm: selectedfrag=new alarm();break;
                        case R.id.timer:selectedfrag=new timer();break;
                        case R.id.stopwatch:selectedfrag=new stopwatch();break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragcontainer,selectedfrag).commit();
                return true;
                }
            };

  /*  private void cancelalarmm() {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(Main2Activity.this,alertalarm.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,intent,0);
        alarmManager.cancel(pendingIntent);
        tv.setText("Alarm cancelled");

    }

    private void sendonchannel2(String title,String msg) {
        NotificationCompat.Builder nb2=mnotification.getchannel2notification(title,msg);
        mnotification.getMmanager().notify(2,nb2.build());
    }

    private void sendonchannel1(String title,String msg) {
        NotificationCompat.Builder nb=mnotification.getchannel1notification(title,msg);
        mnotification.getMmanager().notify(1,nb.build());
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updatetime(c);
        startalarm(c);
       // multiplealarm(c);
        *//*TextView tv=findViewById(R.id.textView);
        tv.setText("Hour:"+hourOfDay+" Minute"+minute);*//*
    }


    private void updatetime(Calendar c) {
        String timetxt="Alarm set for: ";
        timetxt+= DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        tv.setText(timetxt);


    }

    private void startalarm(Calendar c) {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(Main2Activity.this,alertalarm.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }

    private void multiplealarm(Calendar c) {

    }*/

}


