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


public class maindata2 extends AppCompatActivity {

    Button b1, b2, b3;
    String subject, topic;
    String text;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindata2);

        topic = (getIntent().getStringExtra("date"));

        t = (TextView) findViewById(R.id.textView20);
        t.setText(getIntent().getStringExtra("date"));

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        //ArrayList<String> name = new ArrayList<>();
        //name.add("2");
        ArrayList<String> name = db.getallsubject(getIntent().getStringExtra("date"));
        Spinner spinner = (Spinner) findViewById(R.id.spinner7);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, name);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subject = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (db.isthere2(getIntent().getStringExtra("date")) == true) {
            b2 = (Button) findViewById(R.id.button18);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(maindata2.this, maindata3.class);
                    i.putExtra("subject", subject);
                    i.putExtra("date", topic);
                    startActivity(i);
                }
            });
        }

        else
            {
                b2 = (Button) findViewById(R.id.button18);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(maindata2.this, maindata32.class);
                        i.putExtra("date", topic);
                        startActivity(i);
                    }
                });
            }


        }
    }


