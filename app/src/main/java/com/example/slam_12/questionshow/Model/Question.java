package com.example.slam_12.questionshow.Model;


import org.json.JSONObject;

public class Question {

    private  String mCodeQu;
    private String mQuestion;
    private String mAnswer;

    public Question(String question, String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);
    }
    public Question(JSONObject jObject){
        this.mCodeQu = jObject.optString("codeQu");
        this.mQuestion = jObject.optString("question");
        this.mAnswer = jObject.optString("reponse");
    }

    public void setCodeQu(String CodeQu) {
        this.mCodeQu = CodeQu;
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
