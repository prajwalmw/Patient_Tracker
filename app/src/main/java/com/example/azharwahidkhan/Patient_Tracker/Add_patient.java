package com.example.azharwahidkhan.Patient_Tracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//import com.example.akshyata.Patient_Tracker.R;
import com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database;

import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.M_BACKPAIN;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.M_CHESTPAIN;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.M_COST;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.M_MEDICINES;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.M_STOMACHPAIN;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.M_SYMPTOMS;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.P_ADDRESS;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.P_CONTACT;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.P_GENDER;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.P_NAME;
import static com.example.azharwahidkhan.Patient_Tracker.Data.Personal_medical_database.P_TABLE;


public class Add_patient extends AppCompatActivity {

    SQLiteDatabase database;
    Personal_medical_database dbhelper;
    long rowInserted;

    Bundle bundle;
    String u_id;
    boolean add;

FloatingActionButton btn_save;
    EditText et,et2,et3,m1,m2,m3,m4,m5,m6;
    RadioGroup rg;
    RadioButton m_gender,f_gender;
String selected_gender;
    long no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        bundle = getIntent().getExtras();
        u_id = bundle.getString("patient_edit",null);
        add = bundle.getBoolean("tp");
        //Log.d("id","intent_start"+u_id);

        //persoanl edit
et = (EditText) findViewById(R.id.editText);
        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

   et2 = (EditText) findViewById(R.id.editText2);
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);


       et3 = (EditText) findViewById(R.id.editText3);

//my changes for medical
        m1 = (EditText) findViewById(R.id.symptom);
        m1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        m2 = (EditText) findViewById(R.id.medicine);
        m2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        m3 = (EditText) findViewById(R.id.cost);

        m4 = (EditText) findViewById(R.id.backpain);
        m4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        m5 = (EditText) findViewById(R.id.chestpain);
        m5.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        m6 = (EditText) findViewById(R.id.stomachpain);
        m6.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

//gender
        m_gender = (RadioButton) findViewById(R.id.male);
        f_gender = (RadioButton) findViewById(R.id.female);





        //end

        btn_save = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_patient_data();
               // finish();
            }
        });
    }

    public void insert_patient_data()
    {
        String name_et = et.getText().toString();

        String address_et2 = et2.getText().toString();
        String contact_et3 = et3.getText().toString();
        PhoneNumberUtils.formatNumber(contact_et3);

        //medical
        String symptom_m1 = m1.getText().toString();
        String medicine_m2 = m2.getText().toString();
        String cost_m3 = m3.getText().toString();

        String backpain_m4 = m4.getText().toString();
        String chestpain_m5 = m5.getText().toString();
        String stomachpain_m6 = m6.getText().toString();



     //   no = Long.parseLong(contact_et3);     // my changes

        if(m_gender.isChecked())
        {
            selected_gender =m_gender.getText().toString();
        }
        else if (f_gender.isChecked())
        {
            selected_gender = f_gender.getText().toString();
        }
     // String gender_rb = m_gender.getText().toString(); //gender

        dbhelper = new Personal_medical_database(this);
        database = dbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(P_NAME,name_et);
        values.put(P_ADDRESS,address_et2);
        values.put(P_CONTACT,contact_et3);   //my changes
        values.put(P_GENDER,selected_gender);  //gender
        //medical
        values.put(M_SYMPTOMS,symptom_m1);
        values.put(M_MEDICINES,medicine_m2);
        values.put(M_COST,cost_m3);
        values.put(M_BACKPAIN,backpain_m4);
        values.put(M_CHESTPAIN,chestpain_m5);
        values.put(M_STOMACHPAIN,stomachpain_m6);


        if(!et.getText().toString().equals("") && !et2.getText().toString().equals("") && !et3.getText().toString().equals("")
                && !m1.getText().toString().equals("") && !m2.getText().toString().equals("")
                && !m3.getText().toString().equals("") && !m4.getText().toString().equals("")
                && !m5.getText().toString().equals("") && !m6.getText().toString().equals(""))   //patient name is mandatory
        {
            //address
            if(bundle.containsKey("tp"))
            {
                rowInserted = database.insert(P_TABLE, null, values);
                finish();
            }

            if(rowInserted != -1)
                Toast.makeText(getApplicationContext(), "Patient Added ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();

            if(bundle.containsKey("patient_edit"))
            {
                Log.d("id","PResent_id"+u_id);
                database.update(P_TABLE, values, "_id="+u_id, null);
                finish();
            }

        }

        else
        {
            if(et.getText().toString().equals("") )
            {
                et.setError("Name is mandatory!");
            }

            if(et2.getText().toString().equals(""))
            {
                et2.setError("Address field is required!");
            }

             if(et3.getText().toString().equals(""))
            {
                et3.setError("Enter your Mobile no.");
            }

            if(m1.getText().toString().equals(""))
            {
                m1.setError("Please Enter Symptom");
            }

            if(m2.getText().toString().equals(""))
            {
                m2.setError("Please Enter Medicine List");
            }

            if(m3.getText().toString().equals(""))
            {
                m3.setError("Please Enter Cost");
            }

            if(m4.getText().toString().equals(""))
            {
                m4.setError("Enter Backpain data");
            }

            if(m5.getText().toString().equals(""))
            {
                m5.setError("Enter Chestpain data");
            }

            if(m6.getText().toString().equals(""))
            {
                m6.setError("Enter Stomachpain data");
            }


            Toast.makeText(getApplicationContext(), "Please Enter Data", Toast.LENGTH_LONG).show();
          //  et.setError( "First name is required!" );
        }






    }
}
