package com.example.hello;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.hello.DatabaseHelper.TABLENAME3;


public class toto extends Activity {
  TextView textView,textView2,textView3,textView4,textView5,textView6;
  private Button b1,b2;DatabaseHelper myDb;String s1,s2,s3,s4,s5,s6;String s;String dateInString;
String newdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toto);

        textView=(TextView)findViewById(R.id.textView4);
        textView2=(TextView)findViewById(R.id.textView8);
        textView3=(TextView)findViewById(R.id.textView9);
        textView4=(TextView)findViewById(R.id.textView10);
        textView5=(TextView)findViewById(R.id.textView11);
        textView6=(TextView)findViewById(R.id.textView12);
        b1=(Button)findViewById(R.id.button9);
        b2=(Button)findViewById(R.id.button10);

        /*textView.setText("Today is " +
                android.text.format.DateFormat.getDateFormat(this).format(new Date()));*/
       textView.setText(getIntent().getStringExtra("h"));
        textView2.setText(getIntent().getStringExtra("m"));
        textView3.setText(getIntent().getStringExtra("s"));
        textView4.setText(getIntent().getStringExtra("subject"));
        textView5.setText(getIntent().getStringExtra("topic"));
        textView6.setText(getIntent().getStringExtra("date"));

         s1=(getIntent().getStringExtra("h"));
         s2=(getIntent().getStringExtra("m"));
         s3=(getIntent().getStringExtra("s"));
         s4=(getIntent().getStringExtra("subject"));
         s5=(getIntent().getStringExtra("topic"));
        s6=(getIntent().getStringExtra("date"));

        DatabaseHelper db=new DatabaseHelper(getApplicationContext());

        myDb=new DatabaseHelper(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //;
                //textView.setText(s);
                /* if(myDb.isMasterEmpty()==false) {
                dateInString = myDb.getlastdate();  // Start date
                if (s6 != dateInString) {

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(dateInString));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DATE, 1);
                    sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date resultdate = new Date(c.getTimeInMillis());
                    dateInString = sdf.format(resultdate);

                    if (dateInString.equals(s6) == false) {
                        while (dateInString.equals(s6) == false) {
                            String s = "null";
                            String s1 = "0";
                            myDb.insertData3(s, s, s1, s1, s1, dateInString);

                            //dateInString = s;  // Start date
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                            Calendar c1 = Calendar.getInstance();
                            try {
                                c1.setTime(sdf1.parse(dateInString));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            c1.add(Calendar.DATE, 1);
                            sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                            Date resultdate1 = new Date(c1.getTimeInMillis());
                            dateInString = sdf1.format(resultdate1);

                        }
                    }

                    // }
                    //boolean isinserted;
               /* if(myDb.isthere(s5,s4,s6))
                {
                    ArrayList<String> labels9 ;
                    labels9=myDb.getit(s5,s4,s6);
                    String h=labels9.get(0);
                    String m=labels9.get(1);
                    String s=labels9.get(2);
                       myDb.updateData7(s4,s5,s6,s1,s2,s3,h,m,s);
                    Toast.makeText(toto.this, "Data updated", Toast.LENGTH_LONG).show();
                }*/
                //else {
                 /*   boolean isinserted = myDb.insertData3(s5, s4, s1, s2, s3, s6);
                    if (isinserted)
                        Toast.makeText(toto.this, "Data inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(toto.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean isinserted = myDb.insertData3(s5, s4, s1, s2, s3, s6);
                    if (isinserted)
                        Toast.makeText(toto.this, "Data inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(toto.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
            else
                 {
                     boolean isinserted = myDb.insertData3(s5, s4, s1, s2, s3, s6);
                     if (isinserted)
                         Toast.makeText(toto.this, "Data inserted", Toast.LENGTH_LONG).show();
                     else
                         Toast.makeText(toto.this, "Data not inserted", Toast.LENGTH_LONG).show();
                 }*/
                if (myDb.isthere(s5, s4, s6)==true)  {

                    ArrayList<String> labels9= myDb.getit(s5, s4, s6);
                    String h = labels9.get(0);
                    String m = labels9.get(1);
                   String s = labels9.get(2);


                    myDb.updateData7(s4, s5, s6, s1, s2, s3, h, m, s);
                    Toast.makeText(toto.this, "Data updated", Toast.LENGTH_LONG).show();
                }
                if(myDb.isthere(s5,s4,s6)==false) {
                    boolean isinserted = myDb.insertData3(s5, s4, s1, s2, s3, s6);
                    if (isinserted)
                        Toast.makeText(toto.this, "Data inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(toto.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb.getAllData3();
                if(res.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Topic:"+res.getString(0)+"\n");
                    buffer.append("Subject:"+res.getString(1)+"\n");
                    buffer.append("Hours:"+res.getString(2)+"\n");
                    buffer.append("Minutes:"+res.getString(3)+"\n");
                    buffer.append("Seconds:"+res.getString(4)+"\n");
                    buffer.append("Date:"+res.getString(5)+"\n\n");

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
