package com.example.assignment2_rolldiceapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RollsFragment extends Fragment {

    private ListView listView;
    private RollAdapter rollAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_roll, container, false);

        // Find the ListView in the fragment layout
        listView = view.findViewById(R.id.list_rolls);

        // Retrieve all rolls from the database
        Cursor cursor = DatabaseHelper.getAllRolls(getActivity());

        // Define the columns to be used for mapping data from the cursor to the views
        String[] fromColumns = {"ROLL_NUMBER", "ROLL_TIMESTAMP"};
        int[] toViews = {R.id.rollNumberTextView, R.id.rollTimestampTextView};

        // Create an instance of RollAdapter with the retrieved data and column mapping
        rollAdapter = new RollAdapter(getActivity(), cursor, 0);

        // Set the RollAdapter as the adapter for the ListView
        listView.setAdapter(rollAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Retrieve updated rolls from the database
        Cursor cursor = DatabaseHelper.getAllRolls(getActivity());

        // Update the cursor in the RollAdapter to reflect the changes
        rollAdapter.swapCursor(cursor);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Clear the cursor in the RollAdapter when the fragment is paused
        rollAdapter.swapCursor(null);
    }
}