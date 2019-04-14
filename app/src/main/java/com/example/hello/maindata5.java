package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class maindata5 extends AppCompatActivity {

    Button b1, b2, b3;
    String text;
    TextView t;
    String subject, topic, date, hour, min, sec;
TextView t1,t2,t3;
    EditText e3,e4,e5;DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata5);



        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        myDb=new DatabaseHelper(this);
        subject = (getIntent().getStringExtra("subject"));
        topic = (getIntent().getStringExtra("topic"));
        date = (getIntent().getStringExtra("date"));

        t1=(TextView)findViewById(R.id.textViewt1);
        t2=(TextView)findViewById(R.id.textViewt2);
        t3=(TextView)findViewById(R.id.textViewt3);

        t1.setText(date);
        t2.setText(subject);
        t3.setText(topic);

        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);

        b2=(Button)findViewById(R.id.button22);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdated=myDb.updateall(date,subject,topic,e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
               // boolean isupdated=myDb.updateall(date,subject,topic,"1","2","3");

                if(isupdated==true)
                    Toast.makeText(maindata5.this, "Data edited", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(maindata5.this, "Data  not edited", Toast.LENGTH_LONG).show();
            }
        });


    }
}
