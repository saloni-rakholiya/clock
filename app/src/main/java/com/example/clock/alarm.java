package com.example.clock;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clock.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.DuplicateFormatFlagsException;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;


public class alarm extends Fragment /*implements TimePickerDialog.OnTimeSetListener*/ {
    private Button btn;
    /*public static final String ALARM_SERVICE;*/
    private Button cancel;
    private TextView tv;
    private Button third;
    private fornotifications mnotification;
    TimePickerDialog timePickerDialog;
    DatePicker pickerDate;
    TimePicker pickerTime;
    ImageView img;

    private myadapter adapter;
    private ArrayList<mycard> a;
    private RecyclerView rv;

    RadioGroup rg;
    Button forsong;
    int songtoggle;
    RadioButton r1,r2;
    int radioid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentalarm,container,false);
        tv=v.findViewById(R.id.textView);
        cancel=v.findViewById(R.id.button2);
        btn=v.findViewById(R.id.button);
        third=v.findViewById(R.id.button9);
        rv=v.findViewById(R.id.recyclerview);



        loaddate();
        adapter=new myadapter(a);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        forsong=v.findViewById(R.id.toggle);



        forsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(forsong.getText().equals("tuhai"))
                    forsong.setText("lovingly");
                else forsong.setText("tuhai");
                toggle();
            }
        });



        pickerDate = (DatePicker)  v.findViewById(R.id.pickerdate);
        pickerTime = (TimePicker) v.findViewById(R.id.pickertime);
        Calendar now = Calendar.getInstance();

        pickerDate.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH), null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));






        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdatetimedialog();


            }


        });





        final Calendar newCalendar = Calendar.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.set(Calendar.SECOND,0);

                        updatetime(c);
                        startalarm(c);
                    }
                },newCalendar.get(Calendar.HOUR_OF_DAY),newCalendar.get(Calendar.MINUTE),false);
                timePickerDialog.show();
            }
        });






        /*     btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // DialogFragment timepicker=new fortime();
               // timepicker.show(getFragmentManager(),"timepicker");
            }
        });
*/
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelalarmm();
            }
        });


        mnotification=new fornotifications(getContext());

return v;
    }


    int tosongtoggle=0;
    public void toggle()
    {
        if(tosongtoggle==0)
        tosongtoggle=1;
        else tosongtoggle=0;
    }

    private void showdatetimedialog() {

        final Calendar calendar=Calendar.getInstance();
        Calendar current = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        setAlarm(calendar);
                    }
                };
                new TimePickerDialog(getContext(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(getContext(),dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();



    }


    int j=0;

    private void cancelalarmm() {
        AlarmManager alarmManager=(AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        Intent intent=new Intent(getContext(),alertalarm.class);
        for(j=1;j<=i;++j)
       { PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),j,intent,0);
        //1
        alarmManager.cancel(pendingIntent);
       }
        a.clear();

        savedata();
        adapter=new myadapter(a);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);


        i=0;
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


    private void updatetime(Calendar c) {
        String timetxt="Alarm set for: ";
        timetxt+= DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        Toast.makeText(getActivity(),timetxt,3000).show();
        tv.append(timetxt);
        a.add(new mycard(timetxt));
        savedata();
        adapter=new myadapter(a);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }
    int i=0;

    private void startalarm(Calendar c) {
        AlarmManager alarmManager=(AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        Intent intent=new Intent(getContext(),alertalarm.class);
        i=i+1;
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),i,intent,0);


        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }


    private void setAlarm(Calendar targetCal) {

        tv.append("\n\n***\n" + "Alarm is set for " + targetCal.getTime()
                + "\n" + "***\n");
        String t="Alarm is set for " + targetCal.getTime();
        tv.append(t);
        a.add(new mycard(t));
        savedata();
        adapter=new myadapter(a);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        Intent intent = new Intent(getActivity(), alertalarm.class);

        i=i+1;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getActivity(), i, intent, 0);
        //1
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);
    }

    public void savedata()
    {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("sharedpref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(a);
        editor.putString("tasklist",json);
        editor.apply();

    }

/*    public void checkbtn(View v)
    {
         radioid=rg.getCheckedRadioButtonId();
        r1=v.findViewById(radioid);
        Toast.makeText(getActivity(),r1.getText().toString(),3000).show();

    }*/

    public void loaddate()

    {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("sharedpref",Context.MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sharedPreferences.getString("tasklist",null);
        Type type=new TypeToken<ArrayList<mycard>>(){}.getType();
        a=gson.fromJson(json,type);

        if(a==null)
        {
            a=new ArrayList<>();

        }


    }





}






