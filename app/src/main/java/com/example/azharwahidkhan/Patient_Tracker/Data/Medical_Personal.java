package com.example.azharwahidkhan.Patient_Tracker.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Medical_Personal extends SQLiteOpenHelper {



    //values
    private static final String LOGTAG = "Explore";

    public static final String DATABASE_NAME = "PatientData_2.db";
    public static final int DATABASE_VERSION = 4;

    //personal values
    public static final String P_TABLE_2 = "Medical_details";
    public static final String _id = "_id";
    public static final String P_NAME_2 = "NAME";
    public static final String P_ADDRESS_2 = "ADDRESS";
    public static final String P_GENDER_2 = "GENDER";
    public static final String P_CONTACT_2 = "MOBILE_NO";
    //personal end

    //medical
    // public static final String M_TABLE = "Medical_details";
    //   public static final String M_ID = "M_ID";
    public static final String M_SYMPTOMS_2 = "SYMPTOMS";
    public static final String M_MEDICINES_2 = "MEDICINES_LIST";
    public static final String M_COST_2 = "COST";
    //end medicines

    private static final String Medical_table =
            "CREATE TABLE " +P_TABLE_2+ "(" +
                    _id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    P_NAME_2 + " TEXT," +
                    P_ADDRESS_2 + " TEXT," +
                    P_GENDER_2 + " TEXT," +
                    P_CONTACT_2 + " INTEGER,"+
                    M_SYMPTOMS_2 + " TEXT," +
                    M_MEDICINES_2 + " TEXT," +
                    M_COST_2 + " INTEGER);";


    //end of values



    public Medical_Personal(Context context)
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

        db.execSQL(Medical_table);
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
        db.delete(P_TABLE_2,_id + "=?",new String[] {String.valueOf(anInt)});
        db.close();
    }

    public Cursor getAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+P_TABLE_2,null);
        return c;
    }


}
