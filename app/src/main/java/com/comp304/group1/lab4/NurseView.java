package com.comp304.group1.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NurseView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_view);

        getSupportActionBar().setTitle("Hello Nurse");
    }

    public void addPatientClick(View v){
        Intent i = new Intent(NurseView.this, AddPatient.class);         //go to AddPatient activity
        startActivity(i);
    }

    public void addTestClick(View v){
        Intent i = new Intent(NurseView.this, AddTest.class);        //go to Addtest activity
        startActivity(i);
    }

    public void showPatients(View v) {
        final DatabaseManager db = new DatabaseManager(this);
        final TextView patientList = (TextView) findViewById(R.id.patientList);

        // Reading all records
        List table = db.getTable("tbl_patient");
        int n=0;
        for (Object o : table) {            // Iterate through all the rows of table
            ArrayList row = (ArrayList)o;
            // Writing table to log
            String output="";
            for (int i=0;i<row.size();i++)      // Iterate through all the column of a row.
            {
                n++;
                output+= row.get(i).toString() + " ";       //  adding the details of patient one by one. 
                if(n==6){
                    n=0;
                    output+="\n";               //add a nextline char after getting patient details.
                }

            }

            patientList.setText(output);

        }
    }
}
