package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class topicclick2 extends Activity {

    TextView textview;String selectedItem; DatabaseHelper myDb;
    String topic;private Button b1;String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicclick2);

        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        myDb=new DatabaseHelper(this);


        s1=(getIntent().getStringExtra("subject"));

        ArrayList<String> name=db.getalltopics(s1);
        Spinner spinner=(Spinner)findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,name);
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
        b1=(Button)findViewById(R.id.button13);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent (topicclick2.this,topicclick3.class);

                j.putExtra("subject",s1);
                j.putExtra("topic",topic);
                startActivity(j);
            }
        });
    }
}
