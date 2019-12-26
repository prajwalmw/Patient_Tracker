package com.example.azharwahidkhan.Patient_Tracker.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;
import android.util.Log;

/**
 * Created by akshyata on 28-Jan-18.
 */

public class Personal_medical_database  extends SQLiteOpenHelper{



    //values
    private static final String LOGTAG = "Explore";

    public static final String DATABASE_NAME = "PatientData.db";
    public static final int DATABASE_VERSION = 3;

    //personal values
    public static final String P_TABLE = "Personal_details";
    public static final String _id = "_id";
    public static final String P_NAME = "NAME";
    public static final String P_ADDRESS = "ADDRESS";
    public static final String P_GENDER = "GENDER";
    public static final String P_CONTACT = "MOBILE_NO";
    //personal end

    //medical
   // public static final String M_TABLE = "Medical_details";
 //   public static final String M_ID = "M_ID";
    public static final String M_SYMPTOMS = "SYMPTOMS";
    public static final String M_MEDICINES = "MEDICINES_LIST";
    public static final String M_COST = "COST";
    public static final String M_BACKPAIN = "BACK_PAIN";
    public static final String M_CHESTPAIN = "CHESTPAIN";
    public static final String M_STOMACHPAIN = "STOMACHPAIN";
    //end medicines

    private static final String Personal_table =
            "CREATE TABLE " +P_TABLE+ "(" +
                    _id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    P_NAME + " TEXT," +
                    P_ADDRESS + " TEXT," +
                    P_GENDER + " TEXT," +
                    P_CONTACT + " INTEGER,"+
                    M_SYMPTOMS + " TEXT," +
                    M_MEDICINES + " TEXT," +
                    M_COST + " INTEGER," +
                    M_BACKPAIN + " TEXT," +
                    M_CHESTPAIN + " TEXT," +
                    M_STOMACHPAIN + " TEXT);";


    //end of values



    public Personal_medical_database(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }




    //personal table



    //end of table

    //personal table
/*    public static final String Medical_table =
            "CREATE TABLE " +M_TABLE+ "(" +
                    M_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    M_SYMPTOMS + " TEXT," +
                    M_MEDICINES + " TEXT," +
                    M_COST + " TEXT );";
*/

    //end of table



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Personal_table);
        Log.i(LOGTAG,"Table has been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
       //my changes




    }

    public void delete(int anInt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(P_TABLE,_id + "=?",new String[] {String.valueOf(anInt)});
        db.close();
    }

    public Cursor getAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+P_TABLE,null);
        return c;
    }


}
