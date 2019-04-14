package com.example.hello;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class toto2 extends Activity {
    TextView textView,textView1,textView2;
    DatabaseHelper myDb;
     String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toto2);

        textView=(TextView)findViewById(R.id.textView22);
        textView1=(TextView)findViewById(R.id.textView21);
        textView2=(TextView)findViewById(R.id.textView13);


        textView.setText(getIntent().getStringExtra("h"));
        textView1.setText(getIntent().getStringExtra("m"));
        textView2.setText(getIntent().getStringExtra("s"));

    }
}
