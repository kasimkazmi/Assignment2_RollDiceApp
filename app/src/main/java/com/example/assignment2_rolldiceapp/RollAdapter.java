package com.example.assignment2_rolldiceapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Random;

public class RollAdapter extends SimpleCursorAdapter {

    // Constructor for RollAdapter class
    public RollAdapter(Context context, Cursor c, int i) {
        // Call the superclass constructor
        super(context, R.layout.list_item_roll, c,
                new String[]{"ROLL_ID", "ROLL_NUMBER", "ROLL_TIMESTAMP"},
                new int[]{R.id.rollIdTextView, R.id.rollNumberTextView, R.id.rollTimestampTextView},
                0);
    }

    // Create a new view to be displayed for each item in the list
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate the layout for the list item
        return LayoutInflater.from(context).inflate(R.layout.list_item_roll, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        @SuppressLint("Range") int rollNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ROLL_NUMBER")));

        TextView rollNumberTextView = view.findViewById(R.id.rollNumberTextView);
        rollNumberTextView.setText(rollNumber);

        // Get the dice image view from the view
        ImageView diceImageView = view.findViewById(R.id.diceImageView);

        // Generate a random dice number between 1 and 6
        int diceNumber = new Random().nextInt(6) + 1;

        // Set the dice image resource based on the dice number
        switch (diceNumber) {
            case 1:
                diceImageView.setImageResource(R.drawable.dice_six_faces_one);
                break;
            case 2:
                diceImageView.setImageResource(R.drawable.dice_six_faces_two);
                break;
            case 3:
                diceImageView.setImageResource(R.drawable.dice_six_faces_three);
                break;
            case 4:
                diceImageView.setImageResource(R.drawable.dice_six_faces_four);
                break;
            case 5:
                diceImageView.setImageResource(R.drawable.dice_six_faces_five);
                break;
            case 6:
                diceImageView.setImageResource(R.drawable.dice_six_faces_six);
                break;
        }
    }}