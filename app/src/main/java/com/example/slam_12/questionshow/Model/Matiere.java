package com.example.slam_12.questionshow.Model;

public class Matiere {
    private String mNomMatiere;
    private String mCodeMatiere;

    public String getNomMatiere() {
        return mNomMatiere;
    }
    public String getCodeMatiere() { return mCodeMatiere; }

    public void setNomMatiere(String nomMatiere) {
        mNomMatiere = nomMatiere;
    }
    public void  setCodeMatiere(String codeMatiere){
        mCodeMatiere = codeMatiere;
    }

    @Override
    public String toString() {
        return "User{" +
                "mNomMatiere='" + mNomMatiere + '\'' +
                "mCodeMatiere='" + mCodeMatiere + '\'' +
                '}';
    }
}
