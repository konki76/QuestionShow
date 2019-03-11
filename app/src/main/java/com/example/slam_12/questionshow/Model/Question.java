package com.example.slam_12.questionshow.Model;

public class Question {

    /**
     * codeQu : D1
     * question : * Ensemble de règles qui régissent les rapports des membres d’une même société. Qui suis-je ?
     * answer : Le Droit.
     */

    private String codeQu;
    private String question;
    private String answer;

    public Question(String codeQu, String question, String answer) {
        this.codeQu = codeQu;
        this.question = question;
        this.answer = answer;
    }

    public String getCodeQu() {
        return codeQu;
    }

    public void setCodeQu(String codeQu) {
        this.codeQu = codeQu;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
