package com.example.hello;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;


public class topicclick1 extends Activity {
    DatabaseHelper myDb; private Button b1;
    String items1[];String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicclick1);

        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        myDb=new DatabaseHelper(this);

         /*items1=db.getAllLabels2();

        ListView listView=(ListView)findViewById(R.id.listview1);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items1);
        listView.setAdapter(adapter);*/
        ArrayList<String>name=db.getAllLabels();
        Spinner spinner=(Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,name);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text= parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        b1=(Button)findViewById(R.id.button11);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent (topicclick1.this,topicclick2.class);
                j.putExtra("subject",text);
                startActivity(j);
            }
        });



    }
}
