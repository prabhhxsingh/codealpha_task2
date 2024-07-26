package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitness_tracker.DatabaseHelper;
import com.example.fitness_tracker.R;

public class MainActivity extends AppCompatActivity {

    private Button startWorkoutButton;
    private Button setGoalsButton;
    private Button viewProgressButton;

    private RelativeLayout layoutWorkout;
    private RelativeLayout layoutGoals;
    private RelativeLayout layoutProgress;

    private EditText exerciseNameEditText;
    private EditText durationEditText;
    private EditText repetitionsEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startWorkoutButton = findViewById(R.id.buttonStartWorkout);
        setGoalsButton = findViewById(R.id.buttonSetGoals);
        viewProgressButton = findViewById(R.id.buttonViewProgress);

        layoutWorkout = findViewById(R.id.layoutWorkout);
        layoutGoals = findViewById(R.id.layoutGoals);
        layoutProgress = findViewById(R.id.layoutProgress);

        exerciseNameEditText = findViewById(R.id.editTextExerciseName);
        durationEditText = findViewById(R.id.editTextDuration);
        repetitionsEditText = findViewById(R.id.editTextRepetitions);
        saveButton = findViewById(R.id.buttonSaveWorkout);

        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLayout(layoutWorkout);
            }
        });

        setGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLayout(layoutGoals);
            }
        });

        viewProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLayout(layoutProgress);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkout();
            }
        });
    }

    private void showLayout(RelativeLayout layoutToShow) {
        layoutWorkout.setVisibility(View.GONE);
        layoutGoals.setVisibility(View.GONE);
        layoutProgress.setVisibility(View.GONE);
        layoutToShow.setVisibility(View.VISIBLE);
    }

    private void saveWorkout() {
        String exerciseName = exerciseNameEditText.getText().toString().trim();
        String durationStr = durationEditText.getText().toString().trim();
        int duration = Integer.parseInt(durationStr); // assuming duration is in minutes
        String repetitionsStr = repetitionsEditText.getText().toString().trim();
        int repetitions = Integer.parseInt(repetitionsStr);

        // Save workout details to database or perform other actions
        // Example: Save to SQLite database using DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        long result = databaseHelper.insertWorkout(exerciseName, duration, repetitions);

        if (result > 0) {
            Toast.makeText(this, "Workout saved successfully", Toast.LENGTH_SHORT).show();
            clearFields();
            layoutWorkout.setVisibility(View.GONE); // Hide workout layout after saving
        } else {
            Toast.makeText(this, "Failed to save workout", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        exerciseNameEditText.setText("");
        durationEditText.setText("");
        repetitionsEditText.setText("");
    }
}
