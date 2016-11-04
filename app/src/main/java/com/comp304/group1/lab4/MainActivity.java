package com.comp304.group1.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;

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
    }
}
