package com.example.hello;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class topicbutton extends Activity {
    Button btnadd; Button btnview; EditText edittopic;
    DatabaseHelper myDb;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicbutton);
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        ArrayList<String>name=db.getAllLabels();
        Spinner spinner=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,name);
        spinner.setAdapter(adapter);

        myDb=new DatabaseHelper(this);
        btnadd=(Button)findViewById(R.id.button_add);
        btnview=(Button)findViewById(R.id.button_view);
        edittopic=(EditText)findViewById(R.id.editText_topic);
        AddData(); viewAll();
    }
    public void AddData()
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Spinner spinner1=(Spinner)findViewById(R.id.spinner2);
               spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                       String selectedItem= parent.getItemAtPosition(position).toString();
                       boolean isInserted2= myDb.insertData2(edittopic.getText().toString(),selectedItem);
                       if(isInserted2)
                           Toast.makeText(topicbutton.this,"Data inserted",Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(topicbutton.this,"Data not inserted",Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }


               });
            }
        });
    }
    public void viewAll() {
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb.getAllData2();
                if(res.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Topic:"+res.getString(0)+"\n");
                    buffer.append("Subject:"+res.getString(1)+"\n\n");

                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
