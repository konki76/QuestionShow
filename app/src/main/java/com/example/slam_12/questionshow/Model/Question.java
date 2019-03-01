package com.example.slam_12.questionshow.Model;


public class Question {

    private String mQuestion;
    private String mAnswer;

    public Question(String question, String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }


    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mAnswer=" + mAnswer +
                '}';
    }
}
