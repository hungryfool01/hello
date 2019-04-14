package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class maindata1 extends AppCompatActivity {

    Button b1, b2, b3;String text; String startdate,enddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata1);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        ArrayList<String> dates= new ArrayList<String>();

        /*ArrayList<String> name=db.getalldate();
        Spinner spinner=(Spinner)findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,name);
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
        Intent i=new Intent (maindata1.this,maindata2.class);
        i.putExtra("date",text);
        startActivity(i);*/
        startdate = db.getfirstdate();

         enddate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        if (startdate.equals(enddate) == true)
        {
            dates.add(enddate);
        }
        else {

            while (startdate.equals(enddate) == false) {
                dates.add(startdate);

                //dateInString = s;  // Start date
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                Calendar c1 = Calendar.getInstance();
                try {
                    c1.setTime(sdf1.parse(startdate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c1.add(Calendar.DATE, 1);
                sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                Date resultdate1 = new Date(c1.getTimeInMillis());
                startdate = sdf1.format(resultdate1);

            }
            dates.add(startdate);
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dates);
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
       // if (db.isthere2(text) == true) {
            b1 = (Button) findViewById(R.id.button19);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(maindata1.this, maindata2.class);
                    i.putExtra("date", text);
                    startActivity(i);
                }
            });
       /* }
         else
        {
            b1 = (Button) findViewById(R.id.button19);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(maindata1.this, maindata32.class);

                    startActivity(i);
                }
            });
        }*/

    }
}



