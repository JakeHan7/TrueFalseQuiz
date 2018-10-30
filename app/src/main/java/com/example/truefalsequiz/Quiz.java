package com.example.truefalsequiz;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int score;
    private int currentQuestions;

    private List<Question> questionArrayList;


    public Quiz(List<Question> questions){
        this.questionArrayList = questions;
    }

    public Quiz(){
        questionArrayList = null;
        score = 0;
        currentQuestions = 0;

    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQuestions() {
        return currentQuestions;
    }

    public void setCurrentQuestions(int currentQuestions) {
        this.currentQuestions = currentQuestions;
    }

    public void nextQuestion(){
        currentQuestions = getCurrentQuestions() + 1;
    }

    public void checkQuestion(){

    }

    public boolean hasMoreQuestions() {
        if (currentQuestions < questionArrayList.size()){
            return true;
        }
        else{
            return false;
        }
    }
}
