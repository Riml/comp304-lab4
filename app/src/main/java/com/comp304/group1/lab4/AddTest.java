package com.comp304.group1.lab4;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddTest extends AppCompatActivity {

    TextView testsDataView;
    TextView totalTestsView;
    int patientID;
    int lastTestID;
    final String fields[] = {"test_id","BPL","BPL","temperature","patient_id"};
    final String record[] = new String[5];
    private DatabaseManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);


        testsDataView=(TextView)findViewById(R.id.txtTestData);
        totalTestsView=(TextView)findViewById(R.id.txtTotalTests);
        DatabaseManager db = new DatabaseManager(this);

        //retrive patient id from intent
        patientID=10;
        List table = db.getTable("tbl_test");
        totalTestsView.setText("Current patient survived after "+table.size()+"tests");
        lastTestID=1;
        if(table.size() !=0 ) {
            lastTestID = (int) ((ArrayList) (table.get(table.size() - 1))).get(0);
            for (Object o : table) {
                ArrayList row = (ArrayList)o;
                // Writing table to log
                String output="";
                for (int i=0;i<row.size();i++)
                {
                    output+= row.get(i).toString() + " ";
                    output+="\n";
                }
                testsDataView.setText(output);

            }
        }

    }

    public void onClickAddTest(View v){

        if(((TextView)findViewById(R.id.inputBPL)).getText().toString().length() <1 ){
            Toast.makeText(getApplicationContext(),"Please fill required fields",Toast.LENGTH_SHORT).show();
            return;
        }
        record[1]= ((TextView)findViewById(R.id.inputBPL)).getText().toString();
        record[2]=((TextView)findViewById(R.id.inputBPH)).getText().toString();
        record[3]=((TextView)findViewById(R.id.inputTemp)).getText().toString();
        record[4]=Integer.toString(patientID);

        ContentValues values = new ContentValues();

        db.addRecord(values, "tbl_test", fields,record);

    }
}
