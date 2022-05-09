package com.example.myprojectmolegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME ="records.db" ;
    private static final int DATABASEVERSION = 1;
    private static final String TABLE_RECORD = "recordsTbl";
    private static final String COLUMN_ID ="id" ;
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SCORE = "score";
    private static final  String[] allColumns ={COLUMN_ID,COLUMN_NAME,COLUMN_SCORE};
    private static final String CREATE_TABLE_RECORDS = "CREATE TABLE IF NOT EXISTS " +  TABLE_RECORD
            +"("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_SCORE + " INTEGER );";
    private SQLiteDatabase database;



    public DBHelper(@Nullable Context context){

        super(context, DATABASENAME,null, DATABASEVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        database= getWritableDatabase();
        sqLiteDatabase.execSQL(CREATE_TABLE_RECORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        database= getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }

    public void insert(int score,String name){
        database= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_SCORE,score);
        database.insert(TABLE_RECORD,null,values);

        database.close();
    }



}


