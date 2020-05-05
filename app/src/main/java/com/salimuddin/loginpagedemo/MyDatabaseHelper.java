package com.salimuddin.loginpagedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Userdetails.db";
    private static final String TABLE_NAME = "User_details";
    private static final String ID = "id";
    private static final String NAME = "Name";
    private static final String Email = "Email";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final int VERSION_NUMBER = 1 ;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) NOT NULL,"+Email+" TEXT NOT NULL, "+USERNAME+" TEXT NOT NULL, "+PASSWORD+" TEXT NOT NULL);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context, "DATABASE CREATED", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Toast.makeText(context, "DATABASE Not CREATED!!! "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context, "DATABASE UPDATED", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e){
            Toast.makeText(context, "DATABASE Not UPDATED!!! "+e, Toast.LENGTH_SHORT).show();
        }

    }

    public long insetData(UserDetails userDetails){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, userDetails.getName());
        contentValues.put(Email, userDetails.getEmail());
        contentValues.put(USERNAME, userDetails.getUsername());
        contentValues.put(PASSWORD, userDetails.getPassword());

        long rowId = db.insert(TABLE_NAME, null, contentValues);

        return rowId;

    }

    public boolean findPassword(String unam, String pass){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        Boolean result = false;

        if (cursor.getCount() == 0)
        {
            Toast.makeText(context, "No Data Founded", Toast.LENGTH_SHORT).show();

        }
        else {
            while (cursor.moveToNext()){
                String username = cursor.getString(3);
                String password = cursor.getString(4);

                if (username.equals(unam) && password.equals(pass)){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
