package com.comp304.group1.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    //Declare variables
    private static final String DATABASE_NAME = "GuineaPigs.db";
    private static final int DATABASE_VERSION = 1;
    private String tables[]; //table names
    private String tableCreatorString[]; //SQL statements to create tables


    public DatabaseManager(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //initialize database table names and DDL statements
    public void dbInitialize(String[] tables, String tableCreatorString[])
    {
        this.tables=tables;
        this.tableCreatorString=tableCreatorString;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Drop existing tables
        for (int i=0;i<tables.length;i++)
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);
        for (int i=0;i<tables.length;i++)
            db.execSQL(tableCreatorString[i]);
    }

    //onUpgrade will be called if a table already exist for an older version 
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables
        for (int i=0;i<tables.length;i++)
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);

        // Create tables again
        onCreate(db);
    }

    //***********************************************
    //Functions for modifying table
    //***********************************************

    void prePopulateDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        //prepopulate doctor table
        db.execSQL("INSERT INTO tbl_doctor VALUES (1 , \"Ilmir\", \"Taychinov\", \"Human Testing\")");
        db.execSQL("INSERT INTO tbl_doctor VALUES (2 , \"Elmir\", \"Baychinov\", \"Morgue\")");
        db.execSQL("INSERT INTO tbl_doctor VALUES (3 , \"Ylmir\", \"Caychinov\", \"Chaplancy\")");

        //prepopulate nurse table
        db.execSQL("INSERT INTO tbl_nurse VALUES (1 , \"Josh\", \"Bender\", \"Morgue\")");
        db.execSQL("INSERT INTO tbl_nurse VALUES (2 , \"Mosh\", \"Lender\", \"Chaplancy\")");

    }


    // Add a new record
    void addRecord(ContentValues values, String tableName, String fields[], String record[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i=1;i<record.length;i++)
            values.put(fields[i], record[i]);
        // Insert the row
        db.insert(tableName, null, values);
        db.close(); //close database connection
    }


    // Read all records
    public List getTable(String tableName) {
        List table = new ArrayList(); //to store all rows
        // Select all records
        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList row=new ArrayList(); //to store one row

        //scroll over rows and store each row in an array list object
        if (cursor.moveToFirst())
        {
            do
            { // for each row
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }

                table.add(row); //add row to the list

            } while (cursor.moveToNext());
        }

        // return table as a list
        return table;
    }

    // Update a record
    public int updateRecord(ContentValues values, String tableName, String fields[],String record[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i=1;i<record.length;i++)
            values.put(fields[i], record[i]);

        // updating row with given id = record[0]
        return db.update(tableName, values, fields[0] + " = ?",
                new String[] { record[0] });
    }

    // Delete a record with a given id
    public void deleteRecord(String tableName, String idName, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, idName + " = ?",
                new String[] { id });
        db.close();
    }
    // trucate a table with given name
    public void truncateTable(String tablename){
        SQLiteDatabase innerDB = this.getWritableDatabase();
        innerDB.execSQL("DELETE FROM " +tablename);

    }



}





