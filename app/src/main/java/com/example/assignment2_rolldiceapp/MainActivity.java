package com.example.assignment2_rolldiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declare variables for the UI elements
    private Button rollButton;
    private Button viewRollsButton;
    private ImageView diceImageView;

    // Declare a DatabaseHelper object for database operations
    private DatabaseHelper dbHelper;

    // Declare a SQLiteDatabase object for database operations
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        rollButton = findViewById(R.id.rollButton);
        viewRollsButton = findViewById(R.id.viewRollsButton);
        diceImageView = findViewById(R.id.diceImageView);

        // Initialize the DatabaseHelper object
        dbHelper = new DatabaseHelper(this);

        // Get a writable database reference
        database = dbHelper.getWritableDatabase();

        // Set click listeners for the buttons
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        viewRollsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRolls();
            }
        });
    }

    private void rollDice() {
        // Generate a random number between 1 and 6
        Random random = new Random();
        int rollNumber = random.nextInt(6) + 1;

        // Create a ContentValues object to store the roll data
        ContentValues contentValues = new ContentValues();
        contentValues.put("ROLL_TIMESTAMP", getCurrentTimestamp());
        contentValues.put("ROLL_NUMBER", rollNumber);

        // Insert the roll data into the database
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

        // Show a toast message with the rolled number
        Toast.makeText(
                this,
                "Rolled: " + rollNumber,
                Toast.LENGTH_LONG
        ).show();

        // Set the dice image based on the rolled number
        diceImageView.setImageResource(getDiceImageResource(rollNumber));
    }

    private String getCurrentTimestamp() {
        // Get the current timestamp and format it as a string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private int getDiceImageResource(int rollNumber) {
        // Return the resource ID of the dice image based on the rolled number
        if (rollNumber == 1) {
            return R.drawable.dice_six_faces_one;
        } else if (rollNumber == 2) {
            return R.drawable.dice_six_faces_two;
        } else if (rollNumber == 3) {
            return R.drawable.dice_six_faces_three;
        } else if (rollNumber == 4) {
            return R.drawable.dice_six_faces_four;
        } else if (rollNumber == 5) {
            return R.drawable.dice_six_faces_five;
        } else if (rollNumber == 6) {
            return R.drawable.dice_six_faces_six;
        } else {
            return R.drawable.dice_six_faces_one;
        }
    }

    private void viewRolls() {
        // Query the rolls from the database
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]{"_id", "ROLL_NUMBER", "ROLL_TIMESTAMP"}, null, null, null, null, null);

        // Check if there are any rolls in the database
        if (cursor.moveToFirst()) {
            // Create a string to store the roll records
            String records = "";

            // Iterate through the rolls and add them to the records string
            do {
                int rollNumber = cursor.getInt(0);
                String timestamp = cursor.getString(1);

                records +=  "Roll Number: " + rollNumber + "\nRoll Value: " + timestamp + "\n\n";
            } while (cursor.moveToNext());

            // Create an AlertDialog to display the roll records
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Rolls");
            builder.setMessage(records);
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            // Display a toast message indicating no rolls found
            Toast.makeText(this, "No Rolls", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }
    public static class RollsFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the fragment layout
            View view = inflater.inflate(R.layout.fragment_roll, container, false);

            return view;
        }
    }

}