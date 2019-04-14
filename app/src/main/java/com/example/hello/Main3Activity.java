package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    DatabaseHelper myDb; String value;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle extras=getIntent().getExtras();
        if(extras!= null){
            value=extras.getString("key");
        }
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        myDb=new DatabaseHelper(this);
        ArrayList<String>name=db.getalltopics(value);
        final ListView lv=(ListView)findViewById(R.id.list1_id);
        final ArrayAdapter<String>adapter=new ArrayAdapter<>(getApplicationContext()
       ,android.R.layout.simple_list_item_1,name );
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text=(String)lv.getItemAtPosition(position);
                Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                i.putExtra("key",text);
                i.putExtra("key2",value);
                startActivity(i);
            }
        });

    }
}
