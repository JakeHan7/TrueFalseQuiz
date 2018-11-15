package com.example.truefalsequiz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "mainActivity";
    public int score;
    private Quiz quiz;
    private boolean userAnswer;
    private TextView questionDisplayed;
    private TextView scoreDisplayed;
    private Button falsee;
    private Button truee;
    private Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setOnClickListeners();
        score = 0;
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions);

        String jsonstring = readTextFile(XmlFileInputStream);
        Log.d(TAG, "onCreate: " + jsonstring);

        Gson gson = new Gson();
        questions = gson.fromJson(jsonstring, Question[].class);

        List<Question> questionsList = Arrays.asList(questions);
        quiz = new com.example.truefalsequiz.Quiz(questionsList);
        displayScore();
        displayQuestion();

    }

    private void displayScore() {
        if(quiz.isThereAnotherQuestion()) {
            scoreDisplayed.setText("SCORE :" + score);
        }else{
            scoreDisplayed.setText("SCORE :" + score);
        }
    }

    private void sendMessage() {
        if ((userAnswer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayQuestion() {
        if(quiz.isThereAnotherQuestion()){
            Question aquestion = questions[quiz.getCurrentQuestion()];
            questionDisplayed.setText(aquestion.getQuestion());
        }else{
            questionDisplayed.setText("No more Questions");
        }
    }


    private void setOnClickListeners() {
        falsee.setOnClickListener(this);
        truee.setOnClickListener(this);
    }


    private void wireWidgets() {
        questionDisplayed = findViewById(R.id.textView_main_displayedQuestion);
        falsee = findViewById(R.id.button_main_falsebutton);
        truee = findViewById(R.id.button_main_truebutton);
        scoreDisplayed = findViewById(R.id.textView_main_score);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public void onClick(View view) {
        //one method to handel the clicks of all the buttons
        //but don't forget to tell the buttons who is doing the listening
        switch (view.getId()) {
            case R.id.button_main_falsebutton:
                userAnswer = false;
                Question aquestion = questions[quiz.getCurrentQuestion()];
                if ((userAnswer) == aquestion.isAnswer() ) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                } else {
                    Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
                }
                quiz.isThereAnotherQuestion();
                quiz.nextQuestion();
                displayQuestion();
                displayScore();
                break;
            case R.id.button_main_truebutton:
                userAnswer = true;
                Question bquestion = questions[quiz.getCurrentQuestion()];

                if ((userAnswer) == bquestion.isAnswer() ) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                } else {
                    Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
                }
                quiz.isThereAnotherQuestion();
                quiz.nextQuestion();
                displayQuestion();
                displayScore();
                break;
        }
    }

}
