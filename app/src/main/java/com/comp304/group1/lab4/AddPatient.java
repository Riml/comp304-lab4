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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPatient extends AppCompatActivity {

    EditText pFname, pLname;
    Spinner departmentSpinner, doctorSpinner, roomSpinner;
    ArrayAdapter deparmentAdapter, doctorAdapter, roomAdapter;
    List<String> doctortArray = new ArrayList<String>();
    List<String> roomArray = new ArrayList<String>();
    List<String> departmentArray = new ArrayList<String>();

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        initUI();
    }

    public void initUI(){
        db = new DatabaseManager(this);

        EditText pFname = (EditText) findViewById(R.id.txtFirstName);
        EditText pLName = (EditText) findViewById(R.id.txtLastName);
        departmentSpinner = (Spinner) findViewById(R.id.spnrDprt);
        doctorSpinner = (Spinner) findViewById(R.id.spnrDocId);
        roomSpinner = (Spinner) findViewById(R.id.spnrRoom);

        departmentArray.add("Human Experiments");
        departmentArray.add("Morgue");

        // Reading all records
        List table = db.getTable("tbl_patient");

        for (Object o : table) {
            ArrayList row = (ArrayList)o;

            String output="";
            for (int i=0;i<row.size();i++)
            {
                output+= row.get(i).toString();
            }
            //departmentArray = row;
        }

        // Create Array adapter for restaurant spinner to bind array data
        deparmentAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, departmentArray);
        doctorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, departmentArray);
        roomAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, departmentArray);

        // Attach the array adapter to the spinner
        departmentSpinner.setAdapter(deparmentAdapter);
    }

    public void addPatient(View v) {

        final String fields[] = {"patient_id","firstname","lastname", "department", "room", "doctor_id"};
        final String record[] = new String[6];

        record[1]= pFname.getText().toString();
        record[2]= pLname.getText().toString();
        record[3]= departmentSpinner.getSelectedItem().toString();
        record[4]=doctorSpinner.getSelectedItem().toString();
        record[5]= roomSpinner.getSelectedItem().toString();

        //populate the row with some values
        ContentValues values = new ContentValues();

        //add the row to the database
        db.addRecord(values, "tbl_student", fields,record);

    }
}
