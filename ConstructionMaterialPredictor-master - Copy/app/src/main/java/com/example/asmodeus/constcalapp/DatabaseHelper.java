package com.example.asmodeus.constcalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table user (id text primary key,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //inserting
    public Boolean insert(String uid ,String pass)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("id",uid);
        values.put("password",pass);
        long ins =db.insert("user",null,values);

        if(ins==-1)return false;
        else return true;


    }

    //checking if user exists
    public Boolean chkUser(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor data=db.rawQuery("select * from user where id =?",new String[]{id});
        if(data.getCount()>0) return false;
        else return  true;
    }

    //checking userid and password
    public Boolean idPass(String uid,String pass){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from user where id=? and password=?",new String[]{uid,pass});
        if(cursor.getCount()>0)return true;
        else return false;
    }
}
