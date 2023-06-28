package com.example.quiz1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView textViewLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        textViewLeaderboard = findViewById(R.id.text_view_leaderboard);

        // Implement the logic to retrieve and display the leaderboard data
        // You can retrieve the data from Firebase Realtime Database or any other data source
        // Update the textViewLeaderboard with the leaderboard information
    }
}
