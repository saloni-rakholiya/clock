package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class mathsolving extends AppCompatActivity {

    TextView a,b;
    int x,y,z;
    EditText ed;
    Button btn;
    MediaPlayer mp;

    ImageView mujic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathsolving);
        a=findViewById(R.id.textView3);
        b=findViewById(R.id.textView14);
        btn=findViewById(R.id.button10);
        ed=findViewById(R.id.editText);
        mujic=findViewById(R.id.mujic);


       RelativeLayout bg=findViewById(R.id.relativel);
        AnimationDrawable anim=(AnimationDrawable) bg.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(2000);
        anim.start();


        mp = MediaPlayer.create(this, R.raw.lovingly);
        mp.start();
        mp.setLooping(true);


        x=(int)Math.floor(Math.random()*100);
        y=(int)Math.floor(Math.random()*100);

        a.setText(String.valueOf(x));
        b.setText(String.valueOf(y));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed.getText()!=null)
                 {
                int ans=Integer.valueOf(ed.getText().toString());
                if(ans==x+y)
                {
                    mp.stop();
                    Toast.makeText(mathsolving.this,"YOU ARE UP!",3000).show();
                    finish();
                }
                else {
                    Toast.makeText(mathsolving.this,"OOPS",3000).show();
                }}
            }
        });


    }
}
