package com.mk.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "StudentRecord";

    private static final String COLUMN_ROLL = "RollNo";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SEM = "Semester";
    private final Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ROLL + " TEXT PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_SEM + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String roll, String name, String sem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ROLL, roll);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SEM, sem);

        db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor getDataByRoll(String roll) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ROLL + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{roll});
        return cursor;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public boolean updateData(String roll, String name, String sem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SEM, sem);

        int result = db.update(TABLE_NAME, contentValues, COLUMN_ROLL + "=?", new String[]{roll});
        return result > 0; // Return true if update was successful
    }

    // DELETE operation (Delete data by Roll Number)
    public boolean deleteData(String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_ROLL + "=?", new String[]{roll});
        return result > 0; // Return true if delete was successful
    }

    // DELETE operation (Delete all records)
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public ArrayAdapter<String> getAdapter(){
        ArrayList<String> rows = new ArrayList<String>();
        Cursor data = getAllData();
//        data.moveToFirst();
        while(data.moveToNext()){

            String roll = data.getString(data.getColumnIndex(COLUMN_ROLL));
            String name = data.getString(data.getColumnIndex(COLUMN_NAME));
            String sem = data.getString(data.getColumnIndex(COLUMN_SEM));
            String row = "Name: "+name+"\nRoll: "+roll+"\nSem: "+sem;
            rows.add(row);
        }
        data.close();
        return new ArrayAdapter<String>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, rows);
    }
}
