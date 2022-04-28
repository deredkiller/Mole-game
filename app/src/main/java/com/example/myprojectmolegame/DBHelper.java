package com.example.myprojectmolegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME ="result.db" ;
    private static final int DATABASEVERSION = 1;
    private static final String TABLE_RECORD = "tblresult";
    private static final String COLUMN_ID ="_id" ;
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SCORE = "score";
    private static final  String[] allColumns ={COLUMN_ID,COLUMN_NAME,COLUMN_SCORE};
    private static final String CREATE_TABLE_USER = "CREATE TABLE  IF NOT  EXISTS " +  TABLE_RECORD
            +"("+ COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+"TEXT,"+COLUMN_SCORE+" INTEGER );";
    private SQLiteDatabase database;



    public DBHelper(@Nullable Context context){
        super(context, DATABASENAME,null, DATABASEVERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }



}


