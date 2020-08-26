package com.example.clock;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class fortime extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int min=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,min,false);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        updatetime(c);
        startalarm(c);
    }

    private void updatetime(Calendar c) {
        String timetxt="Alarm set for: ";
        timetxt+= java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(c.getTime());


        Toast.makeText(getActivity(),timetxt,3000).show();
    /*   TextView t= .findViewById(R.id.textView);
       t.setText(timetxt);
*/
        //  tv.setText(timetxt);


    }

    int i=0;
    private void startalarm(Calendar c) {
        AlarmManager alarmManager=(AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(getContext(),alertalarm.class);
        i=i+1;
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),i,intent,0);
//1
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }
}
