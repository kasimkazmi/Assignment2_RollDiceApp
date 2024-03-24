package com.example.assignment2_rolldiceapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ROLL_DICE_APP.DB";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = RollContract.RollEntry.TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define the SQL statement to create the rolls table
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                RollContract.RollEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RollContract.RollEntry.COLUMN_ROLL_NUMBER + " INTEGER, " +
                RollContract.RollEntry.COLUMN_ROLL_TIMESTAMP + " TEXT);";

        // Execute the SQL statement to create the table
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create a new table by calling the onCreate() method
        onCreate(db);
    }

    public static Cursor getAllRolls(Context context) {
        // Create an instance of DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        // Get a readable database
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // Query the database to retrieve all rolls
        Cursor cursor = database.query(TABLE_NAME,
                new String[]{RollContract.RollEntry._ID, RollContract.RollEntry.COLUMN_ROLL_NUMBER, RollContract.RollEntry.COLUMN_ROLL_TIMESTAMP},
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }
}