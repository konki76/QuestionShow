package com.example.slam_12.questionshow.Model.sampledata;

public class Matiere {
    private String mNomMatiere;

    public String getNomMatiere() {
        return mNomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        mNomMatiere = nomMatiere;
    }

    @Override
    public String toString() {
        return "User{" +
                "mNomMatiere='" + mNomMatiere + '\'' +
                '}';
    }
}
