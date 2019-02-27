package com.example.slam_12.questionshow.Model.sampledata;


public class Question {

    private String mQuestion;
    private String mAnswer;

    public Question(String question, String answer) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mAnswer;
    }

    public void setChoiceList(String answer) {
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
