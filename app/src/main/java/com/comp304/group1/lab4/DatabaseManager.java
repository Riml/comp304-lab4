package com.comp304.group1.lab4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatabaseManager extends SQLiteOpenHelper {

    //Declare variables
    private static final String DATABASE_NAME = "GuineaPigs.db";
    private static final int DATABASE_VERSION = 1;
    private String tables[]; //table names
    private String tableCreatorString[]; //SQL statements to create tables


    public DatabaseManager(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}





