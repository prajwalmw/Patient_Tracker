package com.example.azharwahidkhan.Patient_Tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.akshyata.Patient_Tracker.R;
import com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database;

public class Details extends AppCompatActivity {



    ListView lv ;
    SimpleCursorAdapter c_adpt;
    Add_patient ap;
ArrayAdapter<String> adpt;
    Cursor c;
    Personal_medical_database pmdata;
    SQLiteDatabase db;
    TextView tv_data;
    String string_id;
    long clicked_id;
    SearchView e_search;
    FloatingActionButton fab;
    Intent i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        fab = (FloatingActionButton) findViewById(R.id.fab_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i2 = new Intent(getApplicationContext(),Add_patient.class);
                i2.putExtra("tp",true);
                startActivity(i2);
                Log.d("add","Insert_Patient"+i2);

            }
    });

       // tv_data = (TextView) findViewById(R.id.prajwal_list);


        onStart();


    }

/*
    @Override
    public boolean onQueryTextSubmit(String query) {
        if (lv==null){
            Toast.makeText(getApplicationContext(),"No records found!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), c_adpt.getCount() + " records found!",Toast.LENGTH_LONG).show();
        }
        c_adpt.swapCursor(c);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (c_adpt!=null){
            c_adpt.swapCursor(c);
        }
        return false;
    }
    */

    @Override
    protected void onStart() {
        super.onStart();


        //mycode
        pmdata = new Personal_medical_database(this);
        db = pmdata.getReadableDatabase();
        String[] projection = new String[] {Personal_medical_database._id,Personal_medical_database.P_NAME};
        lv = (ListView )  findViewById(R.id.listview);


        c = db.query(Personal_medical_database.P_TABLE, projection, null, null, null, null, null);

        c_adpt = new SimpleCursorAdapter(this,R.layout.prajwal_layout,c,projection,new int[]{R.id.prajwal_list_id,R.id.prajwal_list_name});
        lv.setAdapter(c_adpt);
        lv.setTextFilterEnabled(true);


        //end
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // String UserInfo = lv.getItemAtPosition(position).toString();

                  clicked_id = c_adpt.getItemId(position);
                 string_id = Long.toString(clicked_id);

                Toast.makeText(getApplicationContext(),"position id : "+ clicked_id,Toast.LENGTH_LONG).show();
                Intent int_data = new Intent(getApplicationContext(),Patient_data.class);

                int_data.putExtra("name",string_id);
                startActivity(int_data);

            }
        });

        //delete
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder ad = new android.support.v7.app.AlertDialog.Builder(Details.this);
                ad.setTitle("Delete");
                ad.setMessage("Are you sure you want to delete ? ");

                final int pos = position;
                ad.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c.moveToPosition(pos);
                       // db.delete(c.getInt(c.getColumnIndex(Personal_medical_database._id)));
                        //c = db.getAll();
                        pmdata.delete(c.getInt(c.getColumnIndex(Personal_medical_database._id)));       //hint for update.
                       c=pmdata.getAll();
                      c_adpt.swapCursor(c);

                      lv.setAdapter(c_adpt);
                        //cAdapter.notifyDataSetChanged();

                    }
                });

                ad.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

     ad.show();
    return  true;

            }
        });


        //end






    }
}
