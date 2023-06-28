package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class StudentDashboardActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private EditText editTextCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextCode = findViewById(R.id.edit_text_code);
        Button buttonJoinQuiz = findViewById(R.id.button_join_quiz);

        buttonJoinQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinQuiz();
            }
        });
    }

    private void joinQuiz() {
        String code = editTextCode.getText().toString();

        // Check if the entered code exists in the database
        mDatabase.child("quizzes").orderByValue().equalTo(code).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Code exists, proceed to start the quiz
                    Toast.makeText(StudentDashboardActivity.this, "Quiz joined successfully", Toast.LENGTH_SHORT).show();
                    startQuiz();
                } else {
                    // Code doesn't exist, show an error message
                    Toast.makeText(StudentDashboardActivity.this, "Invalid code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Error occurred while accessing the database
                Toast.makeText(StudentDashboardActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startQuiz() {
        // Implement the logic to start the quiz activity
        // You can navigate to a new activity or fragment to display the quiz questions

        // Generate a random code for the quiz
        String quizCode = generateRandomCode();

        // Store the generated code in the Firebase database under the "quizzes" node
        DatabaseReference quizzesRef = FirebaseDatabase.getInstance().getReference("quizzes");
        String quizId = quizzesRef.push().getKey();
        quizzesRef.child(quizId).setValue(quizCode);

        // Pass the quiz code to the QuizActivity and start the activity
        Intent intent = new Intent(StudentDashboardActivity.this, StudentDashboardActivity.class);
        intent.putExtra("quizId", quizId);
        startActivity(intent);
    }

    private String generateRandomCode() {
        // Generate a random code using any method or library you prefer
        // Here's an example of generating a random code with 6 alphanumeric characters
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }
        return codeBuilder.toString();
    }
}

