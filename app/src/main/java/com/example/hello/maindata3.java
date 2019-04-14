package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class maindata3 extends AppCompatActivity {

    Button b1, b2, b3;
    String text;
    TextView t;String subject,button;String topic;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata3);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        t=(TextView)findViewById(R.id.textView24);
        t1=(TextView)findViewById(R.id.textView23);
        t1.setText(getIntent().getStringExtra("date"));
        t.setText(getIntent().getStringExtra("subject"));

        ArrayList<String>name = db.getallsubjecttopics(getIntent().getStringExtra("date"),(getIntent().getStringExtra("subject")));
        Spinner spinner = (Spinner) findViewById(R.id.spinner8);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, name);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                topic= parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        b2=(Button)findViewById(R.id.button20);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (maindata3.this,maindata4.class);
                i.putExtra("subject",(getIntent().getStringExtra("subject")));
                i.putExtra("date",(getIntent().getStringExtra("date")));
                i.putExtra("topic",topic);
                startActivity(i);
            }
        });


    }
}
