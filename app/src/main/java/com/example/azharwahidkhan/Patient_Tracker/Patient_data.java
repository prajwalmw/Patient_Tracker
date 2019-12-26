package com.example.azharwahidkhan.Patient_Tracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database;

public class Patient_data extends AppCompatActivity {

    SQLiteDatabase database_list;
    Personal_medical_database db_class;
    Cursor c1;
    Details d = new Details();
    Intent i_edit;

    TextView txt,et1,et2,et3,et4,et5,et6,et7,et8,et9,et10;
    FloatingActionButton f_edit;
    String id_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);

        onStart();


    }

    @Override
    protected void onStart() {
        super.onStart();


        Bundle bundle = getIntent().getExtras();
        String d_name = bundle.getString("name");

        db_class = new Personal_medical_database(this);
        database_list = db_class.getReadableDatabase();

        final String selection = Personal_medical_database._id + "=?";
        String[] selectionArgs = new String[]{d_name};

        String[] projection_data = new String[]
                {
                        Personal_medical_database.P_NAME,
                        Personal_medical_database._id,
                        Personal_medical_database.P_ADDRESS,
                        Personal_medical_database.P_GENDER,
                        Personal_medical_database.P_CONTACT,
                        Personal_medical_database.M_SYMPTOMS,
                        Personal_medical_database.M_MEDICINES,
                        Personal_medical_database.M_COST,
                        Personal_medical_database.M_BACKPAIN,
                        Personal_medical_database.M_CHESTPAIN,
                        Personal_medical_database.M_STOMACHPAIN
                };


        c1 = database_list.query
                (Personal_medical_database.P_TABLE,
                        projection_data,
                        selection,
                        selectionArgs, null, null, null
                );

        txt = (TextView) findViewById(R.id.id_data);

        et1 = (TextView) findViewById(R.id.name_data);
        et2 = (TextView) findViewById(R.id.address_data);
        et3 = (TextView) findViewById(R.id.gender_data);
        et4 = (TextView) findViewById(R.id.contact_data);
        et5 = (TextView) findViewById(R.id.symptom_data);
        et6 = (TextView) findViewById(R.id.medicine_data);
        et7 = (TextView) findViewById(R.id.cost_data);
        et8 = (TextView) findViewById(R.id.back_data);
        et9 = (TextView) findViewById(R.id.chest_data);
        et10 = (TextView) findViewById(R.id.stomach_data);

        f_edit = (FloatingActionButton) findViewById(R.id.fab_edit);    //update option
        f_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_edit = new Intent(getApplicationContext(),Add_patient.class);
                i_edit.putExtra("patient_edit",id_s);
                Log.d("data_id","intent_id "+id_s);
                startActivity(i_edit);
            }
        });

        try {
         //   txt.setText("W" + c1.getCount());
           // txt.append("\n\n" + Personal_medical_database._id + " --- "  + Personal_medical_database.P_NAME + " --- "
               //      +Personal_medical_database.P_ADDRESS + " --- "+ Personal_medical_database.P_ADDRESS + " --- " + Personal_medical_database.P_CONTACT + " --- " );


            int column_index = c1.getColumnIndex(Personal_medical_database._id);
            int column_name = c1.getColumnIndex(Personal_medical_database.P_NAME);
            int column_address = c1.getColumnIndex(Personal_medical_database.P_ADDRESS);
            int column_contact = c1.getColumnIndex(Personal_medical_database.P_CONTACT);
          //  Long column_contact_long = Long.valueOf(column_contact);   //my changes
            int column_gender = c1.getColumnIndex(Personal_medical_database.P_GENDER);

            int column_symptom = c1.getColumnIndex(Personal_medical_database.M_SYMPTOMS);
            int column_medicine = c1.getColumnIndex(Personal_medical_database.M_MEDICINES);
            int column_cost = c1.getColumnIndex(Personal_medical_database.M_COST);
            int column_backpain = c1.getColumnIndex(Personal_medical_database.M_BACKPAIN);
            int column_chestpain = c1.getColumnIndex(Personal_medical_database.M_CHESTPAIN);
            int column_stomachpain = c1.getColumnIndex(Personal_medical_database.M_STOMACHPAIN);



            while(c1.moveToNext())
            {
                int current_id = c1.getInt(column_index);
                id_s = String.valueOf(current_id);

                String current_name = c1.getString(column_name);
                String current_gender = c1.getString(column_gender);
                String current_address = c1.getString(column_address);

                Long current_contact = c1.getLong(column_contact);
                String cont = String.valueOf(current_contact);

                String current_symptom = c1.getString(column_symptom);
                String current_medicine = c1.getString(column_medicine);

                int current_cost = c1.getInt(column_cost);
                String int_cost = String.valueOf(current_cost);

                String current_backpain = c1.getString(column_backpain);
                String current_chestpain = c1.getString(column_chestpain);
                String current_stomachpain = c1.getString(column_stomachpain);

                //my changes

               txt.setText("ID : "+ id_s);

               et1.setText(current_name);
               et2.setText(current_address);
               et3.setText(current_gender);
               et4.setText(cont);
               et5.setText(current_symptom);
               et6.setText(current_medicine);
               et7.setText("\u20B9 " +int_cost);
                et8.setText(current_backpain);
                et9.setText(current_chestpain);
                et10.setText(current_stomachpain);

            }







        } catch (Exception e) {
            //txt.setText("HELLO");
            c1.close();
        }
    }
}
