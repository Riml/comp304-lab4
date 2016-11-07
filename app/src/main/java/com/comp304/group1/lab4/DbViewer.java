package com.comp304.group1.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DbViewer extends AppCompatActivity {

    private String currentTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_viewer);

        currentTable=getIntent().getStringExtra("table_name");
        switch (currentTable){
            case "tbl_patient":
                displayTable("tbl_patient");
                break;
            case "tbl_test":
                displayTable("tbl_test");
                break;
            case "tbl_doctor":
                displayTable("tbl_doctor");
                break;
            case "tbl_nurse":
                displayTable("tbl_nurse");
                break;
        }
    }

    private void displayTable(String table){

        final DatabaseManager db = new DatabaseManager(this);
        List tableList = db.readTable(table);


        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for( int i = 0; i < tableList.size(); i++ )
        {
            TextView textView = new TextView(this);
            String currentRow="";
            for( int j = 0; j < ((List)tableList.get(i)).size(); j++ )
            {

                currentRow+=((List) tableList.get(i)).get(j);


            }

            textView.setText(currentRow);
            linearLayout.addView(textView);
        }

        getSupportActionBar().setTitle(table);
    }
}
