package com.comp304.group1.lab4;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPatient extends AppCompatActivity {

    EditText pFname, pLname;
    int doctoroId;
    Spinner departmentSpinner, doctorSpinner, roomSpinner;
    ArrayAdapter deparmentAdapter, doctorAdapter, roomAdapter;
    List<String> doctortArray = new ArrayList<String>();
    List<String> roomArray = new ArrayList<String>();
    List<String> departmentArray = new ArrayList<String>();

    String selectedDoctorID;
    List<String> doctorsIDs = new ArrayList<String>();

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        getSupportActionBar().setTitle("Patient Information View");

        initUI();
    }

    public void initUI(){
        db = new DatabaseManager(this);

        pFname = (EditText) findViewById(R.id.txtFirstName);
        pLname = (EditText) findViewById(R.id.txtLastName);
        departmentSpinner = (Spinner) findViewById(R.id.spnrDprt);
        doctorSpinner = (Spinner) findViewById(R.id.spnrDocId);
        roomSpinner = (Spinner) findViewById(R.id.spnrRoom);
// Add three rooms 1A, 2A , 1B
        roomArray.add("1A");
        roomArray.add("2A");
        roomArray.add("1B");

        // Reading all records from tbl_dotor
        List table = db.getTable("tbl_doctor");
        int i =0;
        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            doctortArray.add(row.get(1+i*4) + " " + row.get(2+i*4));      //get firsname and lastname
            doctorsIDs.add(row.get(0+i*4).toString());                  // get doctor's ID
            departmentArray.add(row.get(3+i*4) + " ");                  //get department
            i++;
        }

        // Create Array adapter for  spinner to bind array data
        deparmentAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, departmentArray);
        doctorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, doctortArray);
        roomAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, roomArray);

        // Attach the array adapter to the spinner
        departmentSpinner.setAdapter(deparmentAdapter);
        doctorSpinner.setAdapter(doctorAdapter);
        roomSpinner.setAdapter(roomAdapter);

    }

    public void addPatient(View v) {

        final String fields[] = {"patient_id","firstname","lastname", "department", "room", "doctor_id"};
        final String record[] = new String[6];

        record[1]= pFname.getText().toString();
        record[2]= pLname.getText().toString();
        record[3]= departmentSpinner.getSelectedItem().toString();
        record[4]=doctorsIDs.get(doctorSpinner.getSelectedItemPosition());
        record[5]= roomSpinner.getSelectedItem().toString();
        //record[5]= roomSpinner.getSelectedItem().toString();

        //populate the row with some values
        ContentValues values = new ContentValues();

        //add the row to the database
        db.addRecord(values, "tbl_patient", fields,record);
        Toast.makeText(getApplicationContext(),"Patient was successfully added",Toast.LENGTH_SHORT).show();

    }
}
