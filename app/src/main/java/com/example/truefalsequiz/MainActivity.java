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


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "MainActivity";
    private Quiz quiz;
    private Question[] questions;
    private TextView displayedQuestion;
    private Button truebutton;
    private Button falsebutton;
    private boolean userAnswer;
    private int score;
    private TextView displayedScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeQuiz();
        wireWidgets();
        setonClickListeners();
        displayNextQuestion();
        //
        //

        //create a gson string
    }

    private void initializeQuiz() {
        InputStream Stream = getResources().openRawResource(R.raw.questions);
        String jsonString = readTextFile(Stream);
        Log.d(TAG , "onCreate" + jsonString);
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(jsonString, Question[].class);
        List<Question> questionList = Arrays.asList(questions);
        Log.d(TAG, "onCreate: " + questionList.toString());
        quiz = new Quiz(questionList);
        if ((userAnswer)){
            if (quiz.hasMoreQuestions()){
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"",Toast.LENGTH_SHORT);
            }
        }
    }

    private void displayNextQuestion() {
        if (quiz.hasMoreQuestions()) {
            Question question = questions[quiz.getCurrentQuestions()];
            displayedQuestion.setText(question.getQuestion());
        }
            else{

            displayedQuestion.setText("WE ARE DONE BOys");
        }
    }

    private void displayScore(){
        if(quiz.hasMoreQuestions()){
            displayedScore.setText("SCORE :" + score);
        }
            else {
            displayedScore.setText("SCORE :" + score);
        }
    }

    private void setonClickListeners() {
        truebutton.setOnClickListener((View.OnClickListener) this);
        falsebutton.setOnClickListener((View.OnClickListener) this);

    }

    private void wireWidgets() {
        displayedQuestion = findViewById(R.id.textView_main_displayedQuestion);
        truebutton = findViewById(R.id.button_main_truebutton);
        falsebutton = findViewById(R.id.button_main_falsebutton);
        displayedScore = findViewById(R.id.textView_main_score);

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
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_main_falsebutton:
                userAnswer= false;
                Question aquestion = questions[quiz.getCurrentQuestions()];
                if((userAnswer == aquestion.isAnswer())) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                }else{
                    Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                    score--;
                }
                quiz.hasMoreQuestions();
                quiz.nextQuestion();
                displayScore();
                displayNextQuestion();
                break;
        }

    }


}

