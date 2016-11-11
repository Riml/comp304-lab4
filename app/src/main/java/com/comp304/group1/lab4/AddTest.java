package com.comp304.group1.lab4;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddTest extends AppCompatActivity {

    TextView testsDataView;
    TextView totalTestsView;

    int lastTestID;
    final String fields[] = {"test_id","BPL","BPL","temperature","patient_id"};
    final String record[] = new String[5];
    private final DatabaseManager db= new DatabaseManager(this);

    Spinner patientSpinner;
    ArrayAdapter patientAdapter;
    List<String> patientArray = new ArrayList<String>();
    String selectedPatientID;
    List<String> patientIDs = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        getSupportActionBar().setTitle("Test Information View");


        testsDataView=(TextView)findViewById(R.id.txtTestData);
        totalTestsView=(TextView)findViewById(R.id.txtTotalTests);


        List tableP = db.getTable("tbl_patient");
        int i1 =0;
        for (Object o : tableP) {

            ArrayList row = (ArrayList)o;


            patientArray.add(row.get(1+i1*6) + " " + row.get(2+i1*6));
            patientIDs.add(row.get(0+i1*6).toString());


            i1++;
        }
        patientSpinner = (Spinner) findViewById(R.id.spnPatient);
        patientAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, patientArray);

        // Attach the array adapter to the spinner
        patientSpinner.setAdapter(patientAdapter);



        patientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedPatientID = patientIDs.get(patientSpinner.getSelectedItemPosition());

                List table = db.getTable("tbl_test");

                int i2=0;
                int i3=0;
                String output="";
                for (Object o : table) {
                    ArrayList row = (ArrayList)o;
                    // Writing table to log


                    Log.i("COMPARE",row.get(4+i2*5).toString() +" : "+selectedPatientID);
                    if(Integer.parseInt(row.get(4+i2*5).toString()) == Integer.parseInt(selectedPatientID)){
                        i3++;
                        output+= "BPL:" +row.get(1+i2*5)+" BPH:"+row.get(2+i2*5)+" Temperature:"+row.get(3+i2*5)+"\n";

                    }
                    i2++;

                }
                testsDataView.setText(output);
                totalTestsView.setText("Current patient survived after "+i3+" tests");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



    }

    public void onClickAddTest(View v){

        if(((TextView)findViewById(R.id.inputBPL)).getText().toString().length() <1 ){
            Toast.makeText(getApplicationContext(),"Please fill required fields",Toast.LENGTH_SHORT).show();
            return;
        }
        record[1]= ((TextView)findViewById(R.id.inputBPL)).getText().toString();
        record[2]=((TextView)findViewById(R.id.inputBPH)).getText().toString();
        record[3]=((TextView)findViewById(R.id.inputTemp)).getText().toString();
        record[4]=selectedPatientID;

        ContentValues values = new ContentValues();

        db.addRecord(values, "tbl_test", fields,record);

    }
}
