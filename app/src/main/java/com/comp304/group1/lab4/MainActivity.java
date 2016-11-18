package com.comp304.group1.lab4;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {



    private static final String tables[]={"tbl_patient","tbl_test","tbl_nurse","tbl_doctor"};
    private static final String tableCreatorString[] ={
        
            //Query for creating table named tbl_doctor having column doctor_id , firstname, lastname,department
            "CREATE TABLE tbl_doctor (doctor_id INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT, lastname TEXT, department TEXT);",

            //Query for creating table named tbl_patient having column patient_id , firstname, lastname,department, room, doctor_id

            "CREATE TABLE tbl_patient (patient_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    " firstname TEXT, lastname TEXT,department TEXT, room TEXT,doctor_id INTEGER NOT NULL," +
                    " FOREIGN KEY(doctor_id) REFERENCES tbl_doctor(doctor_id));",

             //Query for creating table named tbl_test having column test_id , BPL, BHP,HPM,tempeature, patient_id.

            "CREATE TABLE tbl_test (test_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    "BPL REAL, BPH  REAL, HPM REAL, temperature REAL, patient_id INTEGER NOT NULL," +
                    "FOREIGN KEY(patient_id) REFERENCES tbl_patient(patient_id));",
        
            //Query for creating table named tbl_nurse having column nurse_id , firstname, lastname,department

            "CREATE TABLE tbl_nurse (nurse_id INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT, lastname TEXT, department TEXT);"



    };
    private DatabaseManager db;
    private EditText passField;
    private EditText idField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseManager(this);
        //db.createDatabase(getApplicationContext());
        
        db.dbInitialize( tables,tableCreatorString);        //initialize database (open for read/write)
        passField=(EditText) findViewById(R.id.txtLoginPass) ;
        idField=(EditText) findViewById(R.id.txtLoginID) ;


        getSupportActionBar().setTitle("Hospital Super Database (Group1)");



    }

    public void nurseClick(View v){

        if( idField.getText().toString() =="" ||                        
                !(passField.getText().toString()).equals("123qwe")  ){           //check for empty string
            Toast.makeText(getApplicationContext(),"Please check your credentials",Toast.LENGTH_SHORT).show();
            return;
        }


        Intent i = new Intent(MainActivity.this, NurseView.class);               //go to NurseView activity
        startActivity(i);

    }

    public void doctorClick(View v){

        if( idField.getText().toString() =="" ||
                !(passField.getText().toString()).equals("12qwas")  ){              //check for empty string
            Toast.makeText(getApplicationContext(),"Please check your credentials",Toast.LENGTH_SHORT).show();
            return;
        }


        Intent i = new Intent(MainActivity.this, AddTest.class);           //go to Addtest activity
        startActivity(i);

    }

    public void onClickReset(View v){

        //empty all tables
        db.truncateTable("tbl_doctor");
        db.truncateTable("tbl_nurse");
        db.truncateTable("tbl_test");
        db.truncateTable("tbl_patient");
        //prepopulate Nurse and Doctor table
        db.prePopulateDB();



    }


}
