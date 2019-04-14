package com.example.hello;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button b; private Button c;private Button b1,b5;
    private Button test;private Button test1;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflator;
    private RelativeLayout relativeLayout;
    String items[]=new String[] { "Subjects","Topics","Help","About"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        b=(Button)findViewById(R.id.button4);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent (MainActivity.this,toto.class);
                startActivity(j);
            }
        });


        b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent (MainActivity.this,Studyclick1.class);
                startActivity(j);
            }
        });
        b5=(Button)findViewById(R.id.button_analyse);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j=new Intent (MainActivity.this,Analyseclick1.class);
                startActivity(j);
            }
        });


        ListView listview=(ListView)findViewById(R.id.listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent i=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(i);
                        break;

                    case 1:
                        Intent j=new Intent(MainActivity.this,topicclick1.class);
                        startActivity(j);
                        break;
                }
            }
        });

        test=(Button)findViewById(R.id.button3);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,subjectbutton.class);
                startActivity(i);
            }
        });
        test1=(Button)findViewById(R.id.button4);

        test1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,topicbutton.class);
                startActivity(i);
            }
        });

    }
}
