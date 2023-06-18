package com.example.quiz1;

import static com.example.quiz1.AboutActivity.MyPREFERENCES;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DifficultQuiz extends AppCompatActivity {

    TextView quiztext, aans, bans, cans, dans;
    List<QuesitionsItem> quesitionsItems;
    int currentQuestions = 0;
    int correct = 0, wrong = 0;
    Intent intent;
    public static int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int default1;
        switch (Configuration.UI_MODE_NIGHT_MASK) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                default1 = 0;
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                default1 = 0;
                break;
            default:
                default1 = 2;
        }

        checked = sharedPreferences.getInt("checked", default1);
        switch (checked) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 3:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                break;
        }

        quiztext = findViewById(R.id.quizText);
        aans = findViewById(R.id.aanswer);
        bans = findViewById(R.id.banswer);
        cans = findViewById(R.id.canswer);
        dans = findViewById(R.id.danswer);

        loadAllQuestions();
        Collections.shuffle(quesitionsItems);
        setQuestionScreen(currentQuestions);

        aans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quesitionsItems.get(currentQuestions).getAnswer1().equals(quesitionsItems.get(currentQuestions).getCorrect())) {
                    correct++;
                    aans.setBackgroundResource(R.color.green);
                    aans.setTextColor(getResources().getColor(R.color.white));
                } else {
                    wrong++;
                    aans.setBackgroundResource(R.color.red);
                    aans.setTextColor(getResources().getColor(R.color.white));
                }

                if (currentQuestions < quesitionsItems.size() - 1) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            aans.setBackgroundResource(R.color.card_background);
                            aans.setTextColor(getResources().getColor(R.color.text_secondery_color));
                        }
                    }, 500);
                } else {
                    Intent intent = new Intent(DifficultQuiz.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

        bans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quesitionsItems.get(currentQuestions).getAnswer2().equals(quesitionsItems.get(currentQuestions).getCorrect())) {
                    correct++;
                    bans.setBackgroundResource(R.color.green);
                    bans.setTextColor(getResources().getColor(R.color.white));
                } else {
                    wrong++;
                    bans.setBackgroundResource(R.color.red);
                    bans.setTextColor(getResources().getColor(R.color.white));
                }

                if (currentQuestions < quesitionsItems.size() - 1) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            bans.setBackgroundResource(R.color.card_background);
                            bans.setTextColor(getResources().getColor(R.color.text_secondery_color));
                        }
                    }, 500);
                } else {
                    Intent intent = new Intent(DifficultQuiz.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

        cans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quesitionsItems.get(currentQuestions).getAnswer3().equals(quesitionsItems.get(currentQuestions).getCorrect())) {
                    correct++;
                    cans.setBackgroundResource(R.color.green);
                    cans.setTextColor(getResources().getColor(R.color.white));
                } else {
                    wrong++;
                    cans.setBackgroundResource(R.color.red);
                    cans.setTextColor(getResources().getColor(R.color.white));
                }

                if (currentQuestions < quesitionsItems.size() - 1) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            cans.setBackgroundResource(R.color.card_background);
                            cans.setTextColor(getResources().getColor(R.color.text_secondery_color));
                        }
                    }, 500);
                } else {
                    Intent intent = new Intent(DifficultQuiz.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

        dans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quesitionsItems.get(currentQuestions).getAnswer4().equals(quesitionsItems.get(currentQuestions).getCorrect())) {
                    correct++;
                    dans.setBackgroundResource(R.color.green);
                    dans.setTextColor(getResources().getColor(R.color.white));
                } else {
                    wrong++;
                    dans.setBackgroundResource(R.color.red);
                    dans.setTextColor(getResources().getColor(R.color.white));
                }

                if (currentQuestions < quesitionsItems.size() - 1) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestions++;
                            setQuestionScreen(currentQuestions);
                            dans.setBackgroundResource(R.color.card_background);
                            dans.setTextColor(getResources().getColor(R.color.text_secondery_color));
                        }
                    }, 500);
                } else {
                    Intent intent = new Intent(DifficultQuiz.this, ResultActivity.class);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void setQuestionScreen(int currentQuestions) {
        quiztext.setText(quesitionsItems.get(currentQuestions).getQuestions());
        aans.setText(quesitionsItems.get(currentQuestions).getAnswer1());
        bans.setText(quesitionsItems.get(currentQuestions).getAnswer2());
        cans.setText(quesitionsItems.get(currentQuestions).getAnswer3());
        dans.setText(quesitionsItems.get(currentQuestions).getAnswer4());
    }

    private void loadAllQuestions() {
        quesitionsItems = new ArrayList<>();
        String jsonquiz = loadJsonFromAsset("difficultquestions.json");
        try {
            JSONObject jsonObject = new JSONObject(jsonquiz);
            JSONArray questions = jsonObject.getJSONArray("difficultquestions");
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);

                String questionsString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");

                quesitionsItems.add(new QuesitionsItem(questionsString, answer1String, answer2String, answer3String, answer4String, correctString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJsonFromAsset(String s) {
        String json = "";
        try {
            InputStream inputStream = getAssets().open(s);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(DifficultQuiz.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("Are you sure want to exit the quiz?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(DifficultQuiz.this, MainActivity.class));
                finish();
            }
        });
        materialAlertDialogBuilder.show();
    }
}