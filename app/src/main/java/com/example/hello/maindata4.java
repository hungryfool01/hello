package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class maindata4 extends AppCompatActivity {

    Button b1, b2, b3;
    String text;
    TextView t;
    String subject, topic,date,hour,min,sec;

    TextView t25,t26,t27,t28,t29,t30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata4);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        subject=(getIntent().getStringExtra("subject"));
        date=(getIntent().getStringExtra("date"));
        topic=(getIntent().getStringExtra("topic"));

       hour=db.gethour( date,subject, topic);
        min=db.getmin( date,subject, topic);
        sec=db.getsec( date,subject, topic);

        t25=(TextView)findViewById(R.id.textView25);
        t26=(TextView)findViewById(R.id.textView26);
        t27=(TextView)findViewById(R.id.textView27);
        t28=(TextView)findViewById(R.id.textView28);
        t29=(TextView)findViewById(R.id.textView29);
        t30=(TextView)findViewById(R.id.textView30);

        t25.setText(date);
        t26.setText(subject);
        t27.setText(topic);
        t28.setText(hour);
        t29.setText(min);
        t30.setText(sec);

        b2=(Button)findViewById(R.id.button21);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (maindata4.this,maindata5.class);
                i.putExtra("subject",subject);
                i.putExtra("date",date);
                i.putExtra("topic",topic);
                startActivity(i);
            }
        });



    }

}
