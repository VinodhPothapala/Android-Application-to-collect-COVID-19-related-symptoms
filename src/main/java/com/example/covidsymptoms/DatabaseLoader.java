package com.example.covidsymptoms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseLoader extends SQLiteOpenHelper {
    public static final String HEART_RATE = "HEART_RATE";
    public static final String RESPIRATORY_RATE = "RESPIRATORY_RATE";
    public static final String FEELING_TIRED = "FEELING_TIRED";
    public static final String SHORTNESS_OF_BREATH = "SHORTNESS_OF_BREATH";
    public static final String COUGH = "COUGH";
    public static final String LOSS_SMELL_TASTE = "LOSS_SMELL_TASTE";
    public static final String MUSCLE_ACHE = "MUSCLE_ACHE";
    public static final String FEVER = "FEVER";
    public static final String SORE_THROAT = "SORE_THROAT";
    public static final String DIARRHEA = "DIARRHEA";
    public static final String HEADACHE = "HEADACHE";
    public static final String NAUSEA = "NAUSEA";
    public static final String ID = "ID";
    public static final String DATA_TABLE = "DATA_TABLE";
    public static final String USER_NAME ="USER_NAME";

    public DatabaseLoader(@Nullable Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + DATA_TABLE + "(" + ID + " INTEGER, " + HEART_RATE + " INTEGER, " + RESPIRATORY_RATE + " INTEGER, " + HEADACHE + " float, " + DIARRHEA + " float, " + SORE_THROAT + " float, " + FEVER + " float, " + MUSCLE_ACHE + " float, " + LOSS_SMELL_TASTE + " float, " + COUGH + " float, " + SHORTNESS_OF_BREATH + " float, " + FEELING_TIRED + " float, " + NAUSEA +" float, "+ USER_NAME + " TEXT )";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public int numberofrows(){
        String query="select * from DATA_TABLE";
        SQLiteDatabase num= this.getReadableDatabase();
        Cursor cur=num.rawQuery(query,null);
        cur.moveToFirst();
        int res=cur.getCount();
        cur.close();
        return res;
    }
    public boolean onInsert(DataValues readings){
        SQLiteDatabase database =this.getWritableDatabase();
        int id= numberofrows();
        ContentValues Values = new ContentValues();
        Values.put(ID,id+1);
        Values.put(HEART_RATE,readings.HEART_RATE);
        Values.put(RESPIRATORY_RATE,readings.RESPIRATORY_RATE);
        Values.put(HEADACHE,readings.HEADACHE);
        Values.put(DIARRHEA,readings.DIARRHEA);
        Values.put(SORE_THROAT,readings.SORE_THROAT);
        Values.put(FEVER,readings.FEVER);
        Values.put(MUSCLE_ACHE,readings.MUSCLE_ACHE);
        Values.put(LOSS_SMELL_TASTE,readings.LOSS_SMELL_TASTE);
        Values.put(COUGH,readings.COUGH);
        Values.put(SHORTNESS_OF_BREATH,readings.SHORTNESS_BREATH);
        Values.put(FEELING_TIRED,readings.FEELING_TIRED);
        Values.put(NAUSEA,readings.NAUSEA);
        Values.put(USER_NAME,readings.USER_NAME);
        long insert = database.insert(DATA_TABLE, null, Values);
        if(insert== -1){
            return false;
        }
        else{
            return true;
        }
    }

}
