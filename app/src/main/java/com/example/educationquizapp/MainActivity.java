package com.example.educationquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtQuestion;
    RadioButton rb1, rb2, rb3, rb4;
    RadioGroup radioGroup;
    Button btnNext;

    ArrayList<QuestionModel> questionList;

    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtQuestion = findViewById(R.id.txtQuestion);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);

        questionList = new ArrayList<>();

        questionList.add(new QuestionModel(
                "What is the capital of India?",
                "Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "Delhi"
        ));

        questionList.add(new QuestionModel(
                "Which language is used for Android Development?",
                "Python",
                "Java",
                "C++",
                "PHP",
                "Java"
        ));

        questionList.add(new QuestionModel(
                "2 + 2 = ?",
                "3",
                "4",
                "5",
                "6",
                "4"
        ));

        loadQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                if(selectedId == -1){
                    Toast.makeText(MainActivity.this,
                            "Please select an answer",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedButton = findViewById(selectedId);
                String selectedAnswer = selectedButton.getText().toString();

                if(selectedAnswer.equals(questionList.get(currentQuestion).getAnswer())){
                    score++;
                }

                currentQuestion++;

                if(currentQuestion < questionList.size()){
                    loadQuestion();
                } else {

                    Intent intent = new Intent(MainActivity.this,
                            ResultActivity.class);

                    intent.putExtra("score", score);
                    intent.putExtra("total", questionList.size());

                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadQuestion(){

        radioGroup.clearCheck();

        QuestionModel question = questionList.get(currentQuestion);

        txtQuestion.setText(question.getQuestion());
        rb1.setText(question.getOption1());
        rb2.setText(question.getOption2());
        rb3.setText(question.getOption3());
        rb4.setText(question.getOption4());
    }
}