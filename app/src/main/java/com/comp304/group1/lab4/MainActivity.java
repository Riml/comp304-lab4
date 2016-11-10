package com.comp304.group1.lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
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
        final DatabaseManager db = new DatabaseManager(this);
        //db.createDatabase(getApplicationContext());
        db.dbInitialize( tables,tableCreatorString);

        getSupportActionBar().setTitle("Hospital Database (Group1)");
        prepopulateTable(db);


    }

    public void nurseClick(View v){
        Intent i = new Intent(MainActivity.this, NurseView.class);
        startActivity(i);

    }

    private void prepopulateTable(DatabaseManager db){
        //pre-populate table
        SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        int i = myPrefs.getInt("dbReady", 0); // return 0 if dbReady doesn't exist
        if(i==0){

            ContentValues values = new ContentValues();
            String doctorFields[] = {"nurse_id","firstname","lastname","department"};
            String nurseFields[] = {"doctor_id","firstname","lastname","department"};
            String doctorRecord1[]={null,"Jacky","Zhang","Human Experiments"};
            String nurseRecord1[]={null,"Ilmir","Taychinov","Morgue"};
            String doctorRecord2[]={null,"Josh","Bender","Human Experiments"};
            String nurseRecord2[]={null,"Konika","Gupta","Human Experiments"};

            db.addRecord(values, "tbl_nurse", nurseFields,nurseRecord1);
            db.addRecord(values, "tbl_nurse", nurseFields,nurseRecord2);
            db.addRecord(values, "tbl_doctor", doctorFields,doctorRecord1);
            db.addRecord(values, "tbl_doctor", doctorFields,doctorRecord2);

            SharedPreferences.Editor e = myPrefs.edit();
            e.putInt("dbReady", 1); // add or overwrite dbReady
            e.commit(); // this saves to disk and notifies observers
        }

    }
}
