package com.comp304.group1.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    private static final String tables[]={"tbl_patient","tbl_test","tbl_nurse","tbl_doctor"};
    private static final String tableCreatorString[] ={
            "CREATE TABLE tbl_patient (patient_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    " firstname TEXT, lastname TEXT,department TEXT, room TEXT,doctor_id INTEGER NOT NULL" +
                    " FOREIGN KEY(doctor_id) REFERENCES tbl_doctor(doctor_id));",

            "CREATE TABLE tbl_test (test_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    "BPL REAL, BPH  REAL, temperature REAL, patient_id INTEGER NOT NULL" +
                    "FOREIGN KEY(patient_id) REFERENCES tbl_patient(patient_id));",

            "CREATE TABLE tbl_nurse (nurse_id INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT, lastname TEXT, department TEXT);",

            "CREATE TABLE tbl_doctor (doctor_id INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT, lastname TEXT, department TEXT);"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("Hospital Database (Group1)");

        final DatabaseManager db = new DatabaseManager(this);
        //db.createDatabase(getApplicationContext());
        db.initializeDatabase( tables,tableCreatorString);
    }

    public void onClickTableName(View v){
        Log.i("BUTTON", v.getId()+" ");

        String tableNameToPass="";
        switch (v.getId()){
            case 2131492947:
                tableNameToPass="tbl_patient";
                break;
            case 2131492949:
                tableNameToPass="tbl_doctor";
                break;
            case 2131492950:
                tableNameToPass="tbl_test";
                break;
            case 2131492951:
                tableNameToPass="tbl_nurse";
                break;
        }

        Intent nextIntent = new Intent(MainActivity.this, DbViewer.class);
        nextIntent.putExtra("table_name", tableNameToPass);
        startActivity(nextIntent);


    }
}
