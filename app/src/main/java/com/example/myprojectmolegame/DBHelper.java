package com.example.myprojectmolegame;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "scoreDb.db";
    private static final String TABLE_RECORD = "tblresult";
    private static final int DATABASEVERSION = 1;
    // ?
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_RATING = "rating";
    private static final String[] allColumns = {COLUMN_ID, COLUMN_NAME, COLUMN_SCORE};

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RECORD + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_SCORE + " INTEGER );";

    private SQLiteDatabase database; // access to table

    public DBHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    // creating the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    // in case of version upgrade -> new schema
// database version
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }
    public ModelUser insert(ModelUser user)
    {
        database = getWritableDatabase(); // get access to write the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_SCORE, user.getScore());
        long id = (int) database.insert(TABLE_RECORD, null, values);
        user.setId(id);
        database.close();
        return user;
    }

    public void deleteById(long id )
    {
        database = getWritableDatabase(); // get access to write data
        database.delete(TABLE_RECORD, COLUMN_ID + " = " + id, null);
        database.close(); // close the database

    }
    public ArrayList<ModelUser> selectAll()
    {
        database = getReadableDatabase(); // get access to read the database
        ArrayList<ModelUser> users = new ArrayList<>();
        String sortOrder = COLUMN_SCORE + " DESC"; // sorting by score
        Cursor cursor = database.query(TABLE_RECORD, allColumns, null, null, null, null, sortOrder); // cursor points at a certain row
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE));
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                ModelUser user= new ModelUser(name, score, id);
                users.add(user);
            }
        }
        database.close();
        return users;
    }



}



