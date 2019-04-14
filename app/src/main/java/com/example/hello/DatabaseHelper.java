package com.example.hello;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME ="subject3.db";
    public static final String TABLE_NAME= "subject_table";
    public static final String COL_1="Subject";

    public static final String TABLENAME2="topic_table";
    public static String COL2_1="Topic2";
    public static final String COL2_2="Subject2";

    public static final String TABLENAME3="timetable";
    public static final String COL3_1="topic";
    public static final String COL3_2="subject";
    public static final String COL3_3="hours";
    public static final String COL3_4="minutes";
    public static final String COL3_5="seconds";
    public static final String COL3_6="Date";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME+ "(Subject TEXT PRIMARY KEY )");
        db.execSQL("create table "+TABLENAME2+ "(Topic2 TEXT,Subject2 TEXT)");
        db.execSQL("create table "+TABLENAME3+ "(topic TEXT,subject TEXT,hours Integer," +
                "minutes Integer,seconds Integer,Date TEXT )");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion,int newVersion){

        if(oldversion==2 && newVersion==3){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+TABLENAME2);
            db.execSQL("DROP TABLE IF EXISTS "+TABLENAME3);

        }
        onCreate(db);
    }
    public boolean insertData(String Subject)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,Subject);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertData2(String Topic2,String Subject2)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2_1,Topic2);
        contentValues.put(COL2_2,Subject2);
        long result=db.insert(TABLENAME2,null,contentValues);
        if(result==-1)
            return false;
        else
            return  true;

    }
    public boolean insertData3(String topic, String subject, String hours,String minutes,String seconds,String Date)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL3_1,topic);
        contentValues.put(COL3_2,subject);
        contentValues.put(COL3_3 , hours);
        contentValues.put(COL3_4,minutes);
        contentValues.put(COL3_5,seconds);
        contentValues.put(COL3_6,Date);
        long result=db.insert(TABLENAME3,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public ArrayList<String> getit(String topic,String subject,String date)
    {
        ArrayList<String>labels=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //String selectQuery = "SELECT * FROM timetable WHERE Date='" + date + "'";
        String select = "SELECT * FROM " + TABLENAME3 + " WHERE " + COL3_1+ " = ? AND " + COL3_6 + " = ? AND " + COL3_2 + " = ?";

        Cursor cursor = db.rawQuery(select, new String[]{topic,date,subject});

       // Cursor cursor = db.rawQuery(select, null);
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {

                    labels.add(cursor.getString(2));
                    labels.add(cursor.getString(3));
                    labels.add(cursor.getString(4));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();


        //returning lables
        return labels;
    }
    public boolean isthere(String topic,String subject,String date)
    {
     Integer count=0;
       /* String selectQuery = "SELECT * FROM timetable WHERE Date='" + date + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                if((cursor.getString(1).equals(topic)==true) &&  (cursor.getString(2).equals(subject)==true));
                return true;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_6+ " = ? AND " + COL3_1 +
                        " = ? AND "+COL3_2+"=?",
                new String[] { date,topic,subject });
        boolean exists = (c.getCount() > 0);
        return exists;

    }
    public boolean isthere2(String date)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_6+ " = ? ",
                new String[] { date });
        boolean exists = (c.getCount() > 0);
        return exists;

    }
    String s;
    public String getlastdate()
    {

        SQLiteDatabase db=this.getWritableDatabase();

        //SELECT * FROM tablename ORDER BY column DESC LIMIT 1;
        /*"SELECT * FROM " + DbHandler.TABLE_ORDER_DETAIL +
                " ORDER BY "+DbHandler.KEY_ORDER_CREATED_AT + " DESC"
                , new String[] {}*/
        //Cursor res=db.rawQuery("SELECT * FROM "+ TABLENAME3  + " ORDER BY "+ COL3_6 + " DESC LIMIT 1  ", null);

        String selectQuery = "SELECT  * FROM " + TABLENAME3;
        Cursor res = db.rawQuery(selectQuery, null);


        if(res!= null)
        {
           res.moveToLast();
            s =res.getString(5);
        }
        return s;
    }
    String arrData;
    public String getfirstdate()
    {

        SQLiteDatabase db=this.getWritableDatabase();

        //SELECT * FROM tablename ORDER BY column DESC LIMIT 1;
        /*"SELECT * FROM " + DbHandler.TABLE_ORDER_DETAIL +
                " ORDER BY "+DbHandler.KEY_ORDER_CREATED_AT + " DESC"
                , new String[] {}*/
        //Cursor res=db.rawQuery("SELECT * FROM "+ TABLENAME3  + " ORDER BY "+ COL3_6 + " DESC LIMIT 1  ", null);

        String selectQuery = "SELECT  * FROM " + TABLENAME3;
        Cursor res = db.rawQuery(selectQuery, null);

        if(res != null)
        {
            if (res.moveToFirst()) {
                arrData= res.getString(5); // DeviceID

             res.close();
            }
        }
        db.close();
        return arrData;
    }
    public boolean  isMasterEmpty() {

        /*boolean flag;
        String quString = "select exists(select 1 from " + TABLENAME3  + ");";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(quString, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        if (count ==1) {
            flag =  false;
        } else {
            flag = true;
        }
        cursor.close();
        db.close();

        return flag;*/
        SQLiteDatabase db = getReadableDatabase();
       Cursor cursor = db.rawQuery("SELECT * FROM timetable", null);
        /*if(cursor.moveToFirst()){
             return false;
        }
        else
            {
             return true;
        }*/
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            //do your action
            //Fetch your data
            return false;

        }
        else {
            return true;

        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME, null );
        return res;
    }

    public Cursor getAllData2()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLENAME2, null );
        return res;
    }
    public Cursor getAllData3()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLENAME3, null );
        return res;
    }
    public ArrayList<String> getAllLabels(){
        ArrayList<String>labels=new ArrayList<>();
        //select all query
        String selectQuery= "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return labels;
    }
    public ArrayList<String> getAllLabels1(){
        ArrayList<String>labels=new ArrayList<>();
        //select all query
        String selectQuery= "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return labels;
    }

    public ArrayList<String>getalltopics(String text) {
        ArrayList<String> labels1 = new ArrayList<>();
        String selectQuery = "SELECT * FROM topic_table WHERE subject2='" + text + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                labels1.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        //returning lables
        return labels1;
    }

    public ArrayList<String>getallsubject(String text) {
        ArrayList<String> labels1 = new ArrayList<>();
        String selectQuery = "SELECT * FROM timetable WHERE Date='" + text + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                labels1.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return labels1;
    }
    public ArrayList<String>getallsubjecttopics(String date,String subject) {
        ArrayList<String> labels1 = new ArrayList<>();
        //String selectQuery = "SELECT * FROM timetable WHERE Date='" + date + "'";
        SQLiteDatabase db = this.getReadableDatabase();
       // Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_2+ " = ? AND " + COL3_6 +
                        " = ? ",
                new String[] { subject,date });
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                labels1.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return labels1;
    }
    String hour,min,sec;
    public String gethour(String date,String subject,String topic) {

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_2+ " = ? AND " + COL3_6 +
                        " = ? AND "+COL3_1 + " = ?",
                new String[] { subject,date,topic });
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                hour=(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return hour;
    }
    public String getmin(String date,String subject,String topic) {

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_2+ " = ? AND " + COL3_6 +
                        " = ? AND "+COL3_1 + " = ?",
                new String[] { subject,date,topic });
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                min=(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return min;
    }
    public String getsec(String date,String subject,String topic) {

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_2+ " = ? AND " + COL3_6 +
                        " = ? AND "+COL3_1 + " = ?",
                new String[] { subject,date,topic });
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                sec=(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        return sec;
    }

    public ArrayList<String>getalldate() {
        ArrayList<String> labels1 = new ArrayList<>();
        String selectQuery = "SELECT * FROM timetable " ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                if(!labels1.contains(cursor.getString(5)))
                labels1.add(cursor.getString(5));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        //returning lables
        return labels1;
    }
    public boolean updateData(String Subject,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_1,Subject);

        db.update(TABLE_NAME,contentValues,"Subject= ?",new String[] {text});

        return true;
    }
    public boolean updateData2(String Subject,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL2_2,Subject);

        db.update(TABLENAME2,contentValues,"Subject2= ?",new String[] {text});

        return true;
    }
    public boolean updateData7(String subject,String topic,String date,String h1,String m1,String s1,String h2,String m2,String s2)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        Integer result = Integer.parseInt(h1);
        Integer result1= Integer.parseInt(h2);
        Integer hour=result+result1;
        Integer result2 = Integer.parseInt(m1);
        Integer result21= Integer.parseInt(m2);
        Integer minutes=result2+result21;
        Integer result23 = Integer.parseInt(s1);
        Integer result213= Integer.parseInt(s2);
        Integer second=result23+result213;
        String hr = Integer.toString(hour);
        String min = Integer.toString(minutes);
        String sec = Integer.toString(second);


        contentValues.put(COL3_3,hr);
        contentValues.put(COL3_4,min);
        contentValues.put(COL3_5,sec);

        db.update(TABLENAME3,contentValues, "topic=? AND subject=? AND Date=?",new String[] {topic,subject,date});

        return true;
    }

    public boolean updateall(String date,String subject,String topic,String hr,String min,String sec)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        contentValues.put(COL3_3,hr);
        contentValues.put(COL3_4,min);
        contentValues.put(COL3_5,sec);

        db.update(TABLENAME3,contentValues, "topic=? AND subject=? AND Date=?",new String[] {topic,subject,date});

        return true;
    }
    public boolean updateData3(String Subject,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL3_2,Subject);

        db.update(TABLENAME3,contentValues,"subject= ?",new String[] {text});

        return true;
    }
    public boolean updateData4(String topic,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL2_1,topic);

        db.update(TABLENAME2,contentValues,"Topic2= ?",new String[] {text});

        return true;
    }
    public boolean updateData5(String topic,String text)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL3_1,topic);

        db.update(TABLENAME3,contentValues,"topic= ?",new String[] {text});

        return true;
    }
    public void updateMethod(String updatename, String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="update TABLENAME2 set COL2_2 = ? where name = ?";
        String[] selections={updatename, name};
        Cursor cursor=db.rawQuery(query, selections);
    }
    public boolean deletesubject(String subject){
        String deleteQuery = "DELETE FROM "+TABLE_NAME+" WHERE Subject='"+subject+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteQuery);
        db.execSQL("vacuum");
        db.close();
        return true;
    }
    public boolean deletesubject2(String subject){
        String deleteQuery = "DELETE FROM "+TABLENAME2+" WHERE Subject2='"+subject+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteQuery);
        db.execSQL("vacuum");
        db.close();
        return true;
    }

    String h1,s1,m1;
    public int getall(String subject)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);

        Cursor cursor = db.rawQuery("select * from " +
                        TABLENAME3 + " where " + COL3_2+ " = ? ",
                new String[] { subject });
        //looping through all rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                h1=(cursor.getString(2));
                m1=(cursor.getString(3));
                s1=(cursor.getString(4));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //returning lables
        Integer h12 = Integer.parseInt(h1);
        Integer m12 = Integer.parseInt(m1);
        Integer s12 = Integer.parseInt(s1);
        return (h12*3600 + m12*60 + s12);

    }

}

