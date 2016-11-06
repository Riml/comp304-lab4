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

    }

    public void addPatientClick(View v){
        Intent i = new Intent(NurseView.this, AddPatient.class);
        startActivity(i);
    }

    public void showPatients(View v) {
        final DatabaseManager db = new DatabaseManager(this);
        final TextView patientList = (TextView) findViewById(R.id.patientList);

        // Reading all records
        List table = db.getTable("tbl_patient");

        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            // Writing table to log
            String output="";
            for (int i=0;i<row.size();i++)
            {
                output+= row.get(i).toString() + " ";
                output+="\n";
            }
            patientList.setText(output);

        }
    }
}
