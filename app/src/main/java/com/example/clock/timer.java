package com.example.clock;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clock.R;

import org.w3c.dom.Text;

import java.util.Locale;

public class timer extends Fragment {

    private static final long starttime=600000;
   private TextView textView;
   private  Chronometer chronometer;
private NumberPicker numberPicker;
   private boolean run;
    private Button start,reset;
    private CountDownTimer countDownTimer;
    private boolean running;
    private long leftinmillis=starttime;
    private View v;
    private TextView min,sec;
    private NumberPicker formin,forsec;
    private  TextView t7;
    private long mendtime;
    private TextView show1,show2,show3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragmenttimer,container,false);
        textView=v.findViewById(R.id.textView2);
        min=v.findViewById(R.id.textView5);
        sec=v.findViewById(R.id.textView6);
      //  chronometer=v.findViewById(R.id.chrono2);
        numberPicker=v.findViewById(R.id.numpicker);

        show1=v.findViewById(R.id.textView8);
        show2=v.findViewById(R.id.textView9);
        show3=v.findViewById(R.id.textView10);



        running=false;
        t7=v.findViewById(R.id.textView7);

        formin=v.findViewById(R.id.min);
        forsec=v.findViewById(R.id.forsec);

        show1.setVisibility(View.VISIBLE);
        show2.setVisibility(View.VISIBLE);
        show3.setVisibility(View.VISIBLE);
        numberPicker.setVisibility(View.VISIBLE);
        formin.setVisibility(View.VISIBLE);
        forsec.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);


        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(50);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                String toset=String.valueOf(newVal);
                t7.setText(toset);
            }
        });

        formin.setMinValue(0);
        formin.setMaxValue (60);
        formin.setValue (10);
        formin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String toset=String.valueOf(newVal);
                min.setText(toset);
            }
        });

        forsec.setMinValue(0);
        forsec.setMaxValue(60);
        forsec.setValue(0);
        forsec.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String toset=String.valueOf(newVal);
                sec.setText(toset);
            }
        });


        //here
       /* new CountDownTimer(10000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                chronometer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                chronometer.setText("done!");
            }
        }.start();*/
        //tohere


       /* textView.setText("POO");
        Toast.makeText(getActivity(),textView.getText(),3000).show();
        textView.setText(textView.getText());*/




        start=v.findViewById(R.id.button6);
        reset=v.findViewById(R.id.button7);

        String h= (String) t7.getText();
        String m= (String) min.getText();
        String s= (String) sec.getText();

        leftinmillis=(Integer.parseInt(h)*3600+Integer.parseInt(m)*60+Integer.parseInt(s))*1000;


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mendtime=System.currentTimeMillis()+leftinmillis;

                if(start.getText().equals("START"))
                {
                    String h= (String) t7.getText();
                    String m= (String) min.getText();
                    String s= (String) sec.getText();

                    leftinmillis=(Integer.parseInt(h)*3600+Integer.parseInt(m)*60+Integer.parseInt(s))*1000;
                }


                min.setVisibility(View.INVISIBLE);
                sec.setVisibility(View.INVISIBLE);
                t7.setVisibility(View.INVISIBLE);

                show1.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.INVISIBLE);
                numberPicker.setVisibility(View.INVISIBLE);
                formin.setVisibility(View.INVISIBLE);
                forsec.setVisibility(View.INVISIBLE);


                textView.setVisibility(View.VISIBLE);





                if(running){

                    countDownTimer.cancel();
                    running=false;
                    start.setText("RESUME");
                    reset.setVisibility(View.VISIBLE);

                    //pausetimer(v);
                }
                else {


                    countDownTimer=new CountDownTimer(leftinmillis,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        leftinmillis=millisUntilFinished;
                        int hours=(int)(leftinmillis/60000)/60;
                        int minutes=(int)(leftinmillis/60000)%60;
                        int seconds=(int)(leftinmillis/1000)%60;
                        //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                        String timeleft=""+hours+":";
                        if(minutes<10) timeleft+="0";
                        timeleft+=minutes+":";
                        if(seconds<10) timeleft+="0";
                        timeleft+=seconds;
                        //TextView t=v.findViewById(R.id.textView2);
                        textView.setText(timeleft);
                    }

                    @Override
                    public void onFinish() {

                running=false;
                start.setText("START");
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);

                    }
                }.start();
                    running=true;
                    start.setText("PAUSE");
                    reset.setVisibility(View.INVISIBLE);
                    //starttimer(v);
                    }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                start.setText("START");
                textView.setVisibility(View.INVISIBLE);
                min.setVisibility(View.VISIBLE);
                sec.setVisibility(View.VISIBLE);
                t7.setVisibility(View.VISIBLE);


                show1.setVisibility(View.VISIBLE);
                show2.setVisibility(View.VISIBLE);
                show3.setVisibility(View.VISIBLE);
                numberPicker.setVisibility(View.VISIBLE);
                formin.setVisibility(View.VISIBLE);
                forsec.setVisibility(View.VISIBLE);


                leftinmillis=starttime;
                int hours=(int)(leftinmillis/60000)/60;
                int minutes=(int)(leftinmillis/60000)%60;
                int seconds=(int)(leftinmillis/1000)%60;
                //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                String timeleft=""+hours+":";
                if(minutes<10) timeleft+="0";
                timeleft+=minutes+":";
                if(seconds<10) timeleft+="0";
                timeleft+=seconds;
                //TextView t=v.findViewById(R.id.textView2);
                textView.setText(timeleft);
                start.setVisibility(View.VISIBLE);
                reset.setVisibility(View.INVISIBLE);
                // resettimer(v);
            }
        });
       /* int hours=(int)(leftinmillis/60000)/60;
        int minutes=(int)(leftinmillis/60000)%60;
        int seconds=(int)(leftinmillis/1000)%60;
        //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        String timeleft=""+hours+":";
        if(minutes<10) timeleft+="0";
        timeleft+=minutes+":";
        if(seconds<10) timeleft+="0";
        timeleft+=seconds;
        //TextView t=v.findViewById(R.id.textView2);
        textView.setText(timeleft);*/
        return v;
    }

    private void updatetext(View v) {

        int hours=(int)(leftinmillis/60000)/60;
        int minutes=(int)(leftinmillis/60000)%60;
        int seconds=(int)(leftinmillis/1000)%60;
      //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        String timeleft=""+hours+":";
        if(minutes<10) timeleft+="0";
        timeleft+=minutes+":";
        if(seconds<10) timeleft+="0";
        timeleft+=seconds;
        //TextView t=v.findViewById(R.id.textView2);
        textView.setText(timeleft);
      //  Toast.makeText(getActivity(),timeleft,8000).show();

    }

    private void resettimer(View v) {

        leftinmillis=starttime;
        updatetext(v);
        start.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);
    }

    private void starttimer() {
        countDownTimer=new CountDownTimer(leftinmillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                leftinmillis=millisUntilFinished;

                int hours=(int)(leftinmillis/60000)/60;
                int minutes=(int)(leftinmillis/60000)%60;
                int seconds=(int)(leftinmillis/1000)%60;
                //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                String timeleft=""+hours+":";
                if(minutes<10) timeleft+="0";
                timeleft+=minutes+":";
                if(seconds<10) timeleft+="0";
                timeleft+=seconds;
                //TextView t=v.findViewById(R.id.textView2);
                textView.setText(timeleft);
            }

            @Override
            public void onFinish() {

                running=false;
                start.setText("START");
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);

            }
        }.start();
        running=true;
        start.setText("PAUSE");
        reset.setVisibility(View.INVISIBLE);
    }



    private void pausetimer(View v) {
    countDownTimer.cancel();
    running=false;
    start.setText("START");
    reset.setVisibility(View.VISIBLE);

    }

   /* @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("millisleft",leftinmillis);
        outState.putBoolean("timerrunning",running);
        outState.putLong("endtime",mendtime);


    }*/


/*    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        leftinmillis=savedInstanceState.getLong("leftinmillis");
        running=savedInstanceState.getBoolean("timerrunning");


        if(running){
            mendtime=savedInstanceState.getLong("endtime");
            leftinmillis=mendtime-System.currentTimeMillis();
            starttimer();
        }
    }*/

   /* @Override
    public void onStart() {
        super.onStart();
        SharedPreferences pref=this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        leftinmillis=pref.getLong("leftinmillis",starttime);
        running=pref.getBoolean("timerrunning",false);

        int hours=(int)(leftinmillis/60000)/60;
        int minutes=(int)(leftinmillis/60000)%60;
        int seconds=(int)(leftinmillis/1000)%60;
        //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        String timeleft=""+hours+":";
        if(minutes<10) timeleft+="0";
        timeleft+=minutes+":";
        if(seconds<10) timeleft+="0";
        timeleft+=seconds;
        //TextView t=v.findViewById(R.id.textView2);
        textView.setText(timeleft);

//updatebuttons

        if(running){
            mendtime=pref.getLong("endtime",0);
            leftinmillis=mendtime-System.currentTimeMillis();

            if(leftinmillis<0){
                leftinmillis=0;
                running=false;
                int hours2=(int)(leftinmillis/60000)/60;
                int minutes2=(int)(leftinmillis/60000)%60;
                int seconds2=(int)(leftinmillis/1000)%60;
                //  String timeleft=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                String timeleftt=""+hours2+":";
                if(minutes2<10) timeleftt+="0";
                timeleftt+=minutes2+":";
                if(seconds2<10) timeleftt+="0";
                timeleftt+=seconds2;
                //TextView t=v.findViewById(R.id.textView2);
                textView.setText(timeleftt);
                //update

            }
            else {
                starttimer();
            }

        }

    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences pref=this.getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putLong("leftinmillis",leftinmillis);
        editor.putBoolean("timerrunning",running);
        editor.putLong("endtime",mendtime);
        editor.apply();
    }*/
}
