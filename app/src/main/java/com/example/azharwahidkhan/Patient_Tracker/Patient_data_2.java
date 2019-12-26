package com.example.azharwahidkhan.Patient_Tracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.azharwahidkhan.Patient_Tracker.Data.Medical_Personal;

public class Patient_data_2 extends AppCompatActivity {

    SQLiteDatabase database_list;
    Medical_Personal db_class;
    Cursor c1;
    Details_2 d = new Details_2();
    FloatingActionButton f_edit;
    TextView txt,et1,et2,et3,et4,et5,et6,et7;
    Intent i_edit;
    String id_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data_2);

        onStart();


    }

    @Override
    protected void onStart() {
        super.onStart();


        Bundle bundle = getIntent().getExtras();
        String d_name = bundle.getString("name");

        db_class = new Medical_Personal(this);
        database_list = db_class.getReadableDatabase();

        String selection = Medical_Personal._id + "=?";
        String[] selectionArgs = new String[]{d_name};

        String[] projection_data = new String[]
                {
                        Medical_Personal.P_NAME_2,
                        Medical_Personal._id,
                        Medical_Personal.P_ADDRESS_2,
                        Medical_Personal.P_GENDER_2,
                        Medical_Personal.P_CONTACT_2,
                        Medical_Personal.M_SYMPTOMS_2,
                        Medical_Personal.M_MEDICINES_2,
                        Medical_Personal.M_COST_2
                };


        c1 = database_list.query
                (Medical_Personal.P_TABLE_2,
                        projection_data,
                        selection,
                        selectionArgs, null, null, null
                );

        txt = (TextView) findViewById(R.id.id_data_2);

        et1 = (TextView) findViewById(R.id.name_data_2);
        et2 = (TextView) findViewById(R.id.address_data_2);
        et3 = (TextView) findViewById(R.id.gender_data_2);
        et4 = (TextView) findViewById(R.id.contact_data_2);
        et5 = (TextView) findViewById(R.id.symptom_data_2);
        et6 = (TextView) findViewById(R.id.medicine_data_2);
        et7 = (TextView) findViewById(R.id.cost_data_2);

        f_edit = (FloatingActionButton) findViewById(R.id.fab_edit_2);    //update option
        f_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_edit = new Intent(getApplicationContext(),Add_Patient_2.class);
                i_edit.putExtra("patient_edit_2",id_s);
                Log.d("data_id","intent_id "+id_s);
                startActivity(i_edit);
            }
        });

        try {
            //   txt.setText("W" + c1.getCount());
            // txt.append("\n\n" + Personal_medical_database._id + " --- "  + Personal_medical_database.P_NAME + " --- "
            //      +Personal_medical_database.P_ADDRESS + " --- "+ Personal_medical_database.P_ADDRESS + " --- " + Personal_medical_database.P_CONTACT + " --- " );


            int column_index = c1.getColumnIndex(Medical_Personal._id);
            int column_name = c1.getColumnIndex(Medical_Personal.P_NAME_2);
            int column_address = c1.getColumnIndex(Medical_Personal.P_ADDRESS_2);
            int column_contact = c1.getColumnIndex(Medical_Personal.P_CONTACT_2);
            //  Long column_contact_long = Long.valueOf(column_contact);   //my changes
            int column_gender = c1.getColumnIndex(Medical_Personal.P_GENDER_2);

            int column_symptom = c1.getColumnIndex(Medical_Personal.M_SYMPTOMS_2);
            int column_medicine = c1.getColumnIndex(Medical_Personal.M_MEDICINES_2);
            int column_cost = c1.getColumnIndex(Medical_Personal.M_COST_2);



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


                //my changes

                txt.setText("ID : "+ id_s);

                et1.setText(current_name);
                et2.setText(current_address);
                et3.setText(current_gender);
                et4.setText(cont);
                et5.setText(current_symptom);
                et6.setText(current_medicine);
                et7.setText("\u20B9 " +int_cost);

            }







        } catch (Exception e) {
            //txt.setText("HELLO");
            c1.close();
        }
    }
}
