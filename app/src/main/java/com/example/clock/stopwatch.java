package com.example.clock;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class stopwatch extends Fragment {
    private Chronometer chronometer;
    long tmillisec,tstart,tbuff,tupdate=00L;
    int sec,min,millisec;
    int mlaps;
    TextView laps;
    private boolean run;
    private long pause;
    Handler handler;
    ScrollView svlaps;


    float fakesec,fakemin,fakemilli;

    Button tostart,topause,toreset,tolap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentstopwatch,container,false);





        laps=v.findViewById(R.id.textView12);
        tostart=v.findViewById(R.id.button3);
        topause=v.findViewById(R.id.button4);
        svlaps=v.findViewById(R.id.scrolling);
        toreset=v.findViewById(R.id.button5);
        tolap=v.findViewById(R.id.button8);
        chronometer=v.findViewById(R.id.chrono1);
        handler=new Handler();
   /*     chronometer.setFormat("TIME: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());*/


        tostart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startchronometer(v);
            }
        });

        topause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausechronometer(v);
            }
        });

        toreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetchronometer(v);
            }
        });

        tolap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lap();
            }
        });

        //here
     /*   chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                    long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                    int h   = (int)(time /3600000);
                    int m = (int)(time - h*3600000)/60000;
                    int s= (int)(time - h*3600000- m*60000)/1000 ;
                    String t = (h < 10 ? "0"+h: h)+":"+(m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
                    chronometer.setText(t);

        }});
        chronometer.setBase(SystemClock.elapsedRealtime());*/

     //here

        chronometer.setText("00:00:000");



        return v;
    }

    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
      tmillisec=System.currentTimeMillis()-tstart;
      tupdate=tbuff+tmillisec;


      sec=(int)((tupdate/1000)%60);
      min=(int)sec/60;
      millisec=(int)tupdate%1000;


      chronometer.setText(String.format("%02d",min)+":"+String.format("%02d",sec)+":"+String.format("%03d",millisec));

      handler.postDelayed(this,00);
        }
    };


 /* sec=(int)(tupdate/1000)%60;
      min=(int)((millisec/(60000))%60);*/
//min=(int)((tupdate/60000)%60);

  /* fakesec=(tupdate/1000)%60;
   fakemin=((tupdate/60000)%60);
   fakemilli=tupdate%1000;

   sec=(int)(fakesec%100);
   min=(int)(fakemin%100);
   millisec=(int)fakemilli;*/



    private void lap() {
        if(run==false) return;
        laps.append("LAP"+String.valueOf(mlaps)+"  "+chronometer.getText()+"\n");
        mlaps++;
        svlaps.post(new Runnable() {
            @Override
            public void run() {
           svlaps.smoothScrollTo(0,laps.getBottom());
            }
        });
    }


    public void startchronometer(View v)
    {
        if(!run)
        { tstart=System.currentTimeMillis();
        handler.postDelayed(runnable,00);
          //  chronometer.setBase(SystemClock.elapsedRealtime()-pause);
        chronometer.start();
        run=true;
        mlaps=1;}

    }
    public void pausechronometer(View v)
    {if(run){
        tbuff+=tmillisec;
        handler.removeCallbacks(runnable);
        chronometer.stop();
        //pause=SystemClock.elapsedRealtime()-chronometer.getBase();
        run=false;
    }

    }
    public void resetchronometer(View v)
    {

        tmillisec=0L;
        tstart=0L;
        tbuff=0L;
        tupdate=0L;
        sec=0;
        min=0;
        millisec=0;
        //chronometer.setBase(SystemClock.elapsedRealtime());
        if(run)
       { handler.removeCallbacks(runnable);
        chronometer.stop();
        chronometer.setText("00:00:000");
        run=false;
        pause=0;
        laps.setText(" ");
        //here
        startchronometer(v);}
        else {
            handler.removeCallbacks(runnable);
            chronometer.stop();
            chronometer.setText("00:00:000");
            run=false;
            pause=0;
            laps.setText(" ");

        }


    }
}
