package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.hello.DatabaseHelper.COL_1;
import static com.example.hello.DatabaseHelper.TABLE_NAME;

public class Subjectbuttonclick extends Activity {
    DatabaseHelper myDb;
    String text,text2;
    Button b,b1;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectbuttonclick);
        myDb = new DatabaseHelper(this);
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        editText=(EditText)findViewById(R.id.editText);
       editText.setText(getIntent().getStringExtra("subject"));
       text=editText.getText().toString();
       text2=(getIntent().getStringExtra("subject"));
       b=(Button)findViewById(R.id.button7);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isupdate=myDb.updateData(editText.getText().toString(),text2);
                boolean isupdate2=myDb.updateData2(editText.getText().toString(),text2);
                boolean isupdate3=myDb.updateData3(editText.getText().toString(),text2);
                if(isupdate == true)
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data inserted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data  not inserted",Toast.LENGTH_LONG).show();
                }

                if( isupdate2== true)
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data inserted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data  not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
        b1=(Button)findViewById(R.id.button8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean isdeleted  =  myDb.deletesubject(text);
                if( isdeleted== true)
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data deleted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data  not deleted",Toast.LENGTH_LONG).show();
                }
                boolean isdeleted2  =  myDb.deletesubject2(text);
                if( isdeleted2== true)
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data deleted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(Subjectbuttonclick.this,"Data  not deleted",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
