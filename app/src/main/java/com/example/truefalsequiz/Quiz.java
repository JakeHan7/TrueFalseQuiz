package com.example.truefalsequiz;


import java.util.List;




public class Quiz {
    private List<Question> questionArrayList;
    private int score;
    private int currentQuestion;

    Quiz(List<Question> questions) {
        this.questionArrayList = questions;

    }
    public Quiz(){
        questionArrayList = null;
        score= 0;
        currentQuestion =0;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int number) {
        this.currentQuestion=number;

    }

    public void nextQuestion(){
        if(isThereAnotherQuestion()) {
            currentQuestion += 1;
        }
    }
    public boolean isThereAnotherQuestion(){
        if(currentQuestion < questionArrayList.size()){
            return true;
        }
        else{
            return false;
        }
    }


}

