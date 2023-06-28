package com.example.quiz1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherDashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private EditText editTextQuestion;
    private Button buttonCreateQuiz;
    private Button buttonGenerateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        editTextQuestion = findViewById(R.id.edit_text_question);
        buttonCreateQuiz = findViewById(R.id.button_create_quiz);
        buttonGenerateCode = findViewById(R.id.button_generate_code);

        buttonCreateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuiz();
            }
        });

        buttonGenerateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCode();
            }
        });
    }

    private void createQuiz() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String question = editTextQuestion.getText().toString();

            // Save the quiz question to the database
            String quizId = mDatabase.child("quizzes").push().getKey();
            mDatabase.child("quizzes").child(quizId).setValue(question);

            Toast.makeText(this, "Quiz created successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void generateCode() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String code = generateRandomCode();

            // Save the generated code to the database
            String quizId = mDatabase.child("quizzes").push().getKey();
            mDatabase.child("quizzes").child(quizId).setValue(code);

            Toast.makeText(this, "Code generated: " + code, Toast.LENGTH_SHORT).show();
        }
    }

    private String generateRandomCode() {
        // Generate and return a random code here
        return "ABC123"; // Replace with your code generation logic
    }
}

