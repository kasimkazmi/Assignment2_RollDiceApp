package com.example.assignment2_rolldiceapp;

import android.provider.BaseColumns;

// RollContract class for defining the database schema
public final class RollContract {

    // Private constructor to prevent instantiation of the class
    private RollContract() {}

    // Inner class that defines the table and column names
    public static class RollEntry implements BaseColumns {

        // Table name constant
        public static final String TABLE_NAME = "rolls";

        // Column names constants
        public static final String COLUMN_ROLL_NUMBER = "roll_number";
        public static final String COLUMN_ROLL_TIMESTAMP = "roll_timestamp";
    }
}