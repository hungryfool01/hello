package com.example.hello;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener{

    public Activity c;
    public Dialog d;
    public Button yes,no;
    public Button btnsave;

    public CustomDialogClass(Activity a) {
        super(a);
        this.c=a;
    }
       Integer hour; Integer minutes; Integer seconds; String topicvalue; String subjectvalue;
       String currentDate;
       DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstannceState){
        super.onCreate(savedInstannceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes=(Button)findViewById(R.id.btn_yes);
        no=(Button)findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        btnsave=(Button)findViewById(R.id.button_save);
        no.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
            c.finish();
            break;
            case R.id.btn_no:
            dismiss();
            break;
          /*  case R.id.button_save:
            {
                hour=Main4Activity.gethour();
                minutes=Main4Activity.getminutes();
                seconds=Main4Activity.getseconds();
                topicvalue=Main4Activity.getTopicvalue();
                subjectvalue=Main4Activity.getSubjectvalue();
                currentDate=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                boolean isinserted = myDb.insertData3(topicvalue,subjectvalue,hour,minutes,seconds,currentDate);
                if(isinserted)
                    Toast.makeText(CustomDialogClass.super.getContext(),"Added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CustomDialogClass.super.getContext(),"Not Added",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            }*/
            default:
            break;
        }
        dismiss();
    }
}
