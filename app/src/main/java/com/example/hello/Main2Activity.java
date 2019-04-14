package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Main2Activity extends Activity {
   DatabaseHelper myDb;
   String text;
   Button b;

   @Override
    protected  void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);
       myDb=new DatabaseHelper(this);
       DisplayMetrics dm=new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);
       int width=dm.widthPixels;
       int height=dm.heightPixels;
       getWindow().setLayout((int)(width*0.8),(int)(height*0.8));
       DatabaseHelper db=new DatabaseHelper(getApplicationContext());
       b=(Button)findViewById(R.id.button);

       ArrayList<String>name=db.getAllLabels();
       Spinner spinner=(Spinner)findViewById(R.id.spinner);
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
      b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            Intent j=new Intent (Main2Activity.this,Subjectbuttonclick.class);
            j.putExtra("subject",text);
            startActivity(j);
         }
      });
   }
}
