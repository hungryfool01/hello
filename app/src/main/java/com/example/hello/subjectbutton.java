package com.example.hello;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class subjectbutton extends Activity implements SeekBar.OnSeekBarChangeListener{
    private SeekBar PRICEbar,DISTANCEbar,RATINGbar;

    private TextView PRICEtextProgress,DISTANCEtextProgress,RATINGtextProgress;

    DatabaseHelper myDb;
    EditText editSubject; Button btnadd; Button btnview;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectbutton);
        PRICEbar=(SeekBar)findViewById(R.id.PRICEseekBarID);
        PRICEbar.setOnSeekBarChangeListener(this);
        PRICEbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {

                PRICEtextProgress=(TextView)findViewById(R.id.PRICEtextViewProgressID);
                PRICEtextProgress.setText("Priority::"+progress);
            }
        });
        myDb=new DatabaseHelper(this);
        editSubject=(EditText)findViewById(R.id.editText_subject);
        btnadd=(Button)findViewById(R.id.button_add);
        btnview=(Button)findViewById(R.id.button_view);
        AddData(); viewAll();
    }
    @Override
    public  void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
        if(seekBar== PRICEbar)
        PRICEtextProgress.setText("Priority::"+progress);
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekbar){

    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
    public void AddData()
    {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted=myDb.insertData(editSubject.getText().toString());
                if(isInserted==true)
                    Toast.makeText(subjectbutton.this,"Data inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(subjectbutton.this,"Data not inserted",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void viewAll(){
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Cursor res=myDb.getAllData();
               if(res.getCount()==0){
                  showMessage("Error","Nothing Found");
                  return;
               }
               StringBuffer buffer= new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("Subject:"+res.getString(0)+"\n\n");
               }
               showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
