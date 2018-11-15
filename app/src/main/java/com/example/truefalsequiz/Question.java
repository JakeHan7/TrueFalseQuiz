package com.example.truefalsequiz;

public class Question {

    private String question;
    private boolean answer;
    private int score;

    public Question(){
         question = "";
         answer = false;
    }
    public Question(String question, boolean answer) {

        this.question = question;
        this.answer = answer;

    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    public boolean checkAnswer(boolean useAnswer){
        return useAnswer == isAnswer();
    }


}
