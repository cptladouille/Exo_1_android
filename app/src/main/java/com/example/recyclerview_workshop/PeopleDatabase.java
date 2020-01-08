package com.example.recyclerview_workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PeopleDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "peoples.db";
    public static final int DATABASE_VERSION = 1;

    private static final String PEOPLE_TABLE = "peoples";
    private static final String KEY_PEOPLE_ID = "id";
    private static final String KEY_PEOPLE_NAME= "name";
    private static final String KEY_PEOPLE_AGE = "age";
    private static final String KEY_PEOPLE_SEX = "sex";
    private static final String KEY_PEOPLE_LOVEANDROID = "love_android";

    public PeopleDatabase(@Nullable Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATION_SCRIPT =
                String.format(
                        "CREATE TABLE %s (" +
                        "%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT," +
                        "%s INTEGER," +
                        "%s TEXT," +
                        "%s INTEGER);",
                        PEOPLE_TABLE,KEY_PEOPLE_ID,KEY_PEOPLE_NAME,KEY_PEOPLE_AGE,KEY_PEOPLE_SEX,KEY_PEOPLE_LOVEANDROID);
        db.execSQL(DATABASE_CREATION_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void AddToDatabase(People p)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_PEOPLE_NAME, p.getName());
            values.put(KEY_PEOPLE_AGE, p.getAge());
            values.put(KEY_PEOPLE_SEX, p.getSex().toString());
            values.put(KEY_PEOPLE_LOVEANDROID, p.isLoveAndroid());
            db.insertOrThrow(PEOPLE_TABLE, null, values);

        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        db.endTransaction();
    }



    public void GetFromDatabase(SQLiteDatabase db,int id)
    {
    }

}
