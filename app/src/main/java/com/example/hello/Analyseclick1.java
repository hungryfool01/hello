package com.example.hello;


import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Analyseclick1 extends AppCompatActivity {

    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyseclick1);
        b1=(Button)findViewById(R.id.button15);
        b4=(Button)findViewById(R.id.button23);
        b2=(Button)findViewById(R.id.button16);
        b3=(Button)findViewById(R.id.button17);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (Analyseclick1.this,maindata1.class);
                startActivity(i);
            }
        });
        b3=(Button)findViewById(R.id.button16);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (Analyseclick1.this,graph1.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (Analyseclick1.this,graph2.class);
                startActivity(i);
            }
        });

    }
}
