package com.example.numverifyapi.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseMobile extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people_mobile";
    private static final String COL1 = "ID";
    private static final String COL2 = "Phone";

    /**
     * Constructor for databaseMobile class.
     * @param context
     */
    public DatabaseMobile(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    /**
     * onCreate method to create new database by name of table and names of columns.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" Varchar(30))";
        db.execSQL(createTable);
    }

    /**
     * onUpgrade method to up grade database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * AddData method to insert any new valid number in data base
     * @param item
     */
    public void addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        db.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * getAllContacts method to get all phone numbers in data base
     * @return arrayList of numbers
     */
    public ArrayList<String> getAllContacts(){
        ArrayList<String> arrayList = new ArrayList<>();
        String select_query = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query,null );
        if (cursor.moveToFirst()){
            do {
                String phone = cursor.getString(cursor.getColumnIndex(COL2));
                arrayList.add(phone);
            }while (cursor.moveToNext());

        }
        return arrayList;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_NAME);
    }


}
