package com.example.hello;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class topicclick3 extends Activity {

    DatabaseHelper myDb;
   TextView textView18;
     Button b1;
    String subject,topic;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicclick3);
        myDb = new DatabaseHelper(this);
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());

        Bundle extras=getIntent().getExtras();
        if(extras!= null){
            subject=extras.getString("subject");
            topic=extras.getString("topic");
        }
        textView18=(TextView)findViewById(R.id.textView18);
        textView18.setText(subject);

        editText=(EditText)findViewById(R.id.editText2);
        editText.setText(topic);
        b1=(Button)findViewById(R.id.button12);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isupdate=myDb.updateData4(editText.getText().toString(),topic);
                boolean isupdate1=myDb.updateData5(editText.getText().toString(),topic);
                if(isupdate == true)
                {
                    Toast.makeText(topicclick3.this,"Data updated",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(topicclick3.this,"Data  not updated",Toast.LENGTH_LONG).show();
                }
                if(isupdate1 == true)
                {
                    Toast.makeText(topicclick3.this,"Data updated",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(topicclick3.this,"Data  not updated",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
