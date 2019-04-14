package com.example.hello;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main4Activity extends AppCompatActivity {
     String subject1,topic1;
    static String topicvalue; static String subjectvalue; static Integer _utfValue; static int hours;long s;
    static int minutes ; static int seconds; static Integer _utfValue1; static Integer _utfValue2;
    static String _utfValue3; static String _utfValue4;
    Button btnaddtime; Button btnstudy;
    Button btnpause; Button btnfinish; Button btncards; Chronometer mchronometer;
    private long pauseOffset; private  boolean running; DatabaseHelper myDb;


    private long calculateElapsedTime(Chronometer mchronometer) {

        long stoppedMilliseconds = 0;

        String chronoText = mchronometer.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                    + Integer.parseInt(array[1]) * 1000;
        } else if (array.length == 3) {
            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                    + Integer.parseInt(array[1]) * 60 * 1000
                    + Integer.parseInt(array[2]) * 1000;
        }

        return stoppedMilliseconds;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Bundle extras=getIntent().getExtras();
        if(extras!= null){
            subjectvalue=extras.getString("key");
            topicvalue=extras.getString("key1");
        }
        btnaddtime=(Button)findViewById(R.id.button_addtime);
        btnstudy=(Button)findViewById(R.id.button_study);
        btnpause=(Button)findViewById(R.id.button_pause);
        btnfinish=(Button)findViewById(R.id.button_finish);
        btncards=(Button)findViewById(R.id.button_cards);
        mchronometer=(Chronometer)findViewById(R.id.chronometer1);
        btnstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!running){
                    mchronometer.setBase(SystemClock.elapsedRealtime()- pauseOffset);
                    mchronometer.start();
                    running= true;
                }
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    mchronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - mchronometer.getBase();
                    running = false;
                }
            }
        });

        btncards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (Main4Activity.this,toto2.class);
                startActivity(i);

            }
        });

        btnaddtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent (Main4Activity.this,toto2.class);
                startActivity(i);

            }
        });

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    mchronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - mchronometer.getBase();
                    running = false;
                }

                long timeElapsed = SystemClock.elapsedRealtime() - mchronometer.getBase();
                 hours = (int) (timeElapsed / 3600000);

                 minutes = (int) (timeElapsed - hours * 3600000) / 60000;
                 seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;

                 String h=Integer.toString(hours);
                 String m=Integer.toString(minutes);
                 String s=Integer.toString(seconds);

                subject1=  (getIntent().getStringExtra("subject"));
                topic1=  (getIntent().getStringExtra("topic"));
                String currentDate=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                Intent i=new Intent (Main4Activity.this,toto.class);
                i.putExtra("h",h);
                i.putExtra("m",m);
                i.putExtra("s",s);
                i.putExtra("subject",subject1);
                i.putExtra("topic",topic1);
                i.putExtra("date",currentDate);
                startActivity(i);

                /*Intent i = new Intent(Main4Activity.this, CustomDialogClass.class);
                i.putExtra("hours", hours);
                i.putExtra("minutes", minutes);
                i.putExtra("seconds", seconds);*/

            }
        });




            }


    static  Integer gethour()
    {
        _utfValue=hours;
        return _utfValue;
    }
    static Integer getminutes()
    {
        _utfValue1=minutes;
        return _utfValue1;
    }
    static Integer getseconds()
    {
       _utfValue2=seconds;
       return _utfValue2;
    }
    static  String getTopicvalue()
    {
        //_utfValue3=topic1;
        return _utfValue3;
    }
    static  String getSubjectvalue()
    {
        //_utfValue4=subject1;
        return _utfValue4;
    }
    /*public void showMessage(String title , String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/




}
