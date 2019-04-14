package com.example.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class maindata32 extends AppCompatActivity {

    Button b1, b2, b3;
    String subject, topic;
    String text;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata32);

        topic = (getIntent().getStringExtra("date"));
    }
}
