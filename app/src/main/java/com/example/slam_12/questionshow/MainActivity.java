package com.example.slam_12.questionshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.slam_12.questionshow.Model.*;

public class MainActivity extends AppCompatActivity {

    public static final String MAT_NAME = "matiere";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Matiere mMatiere = new Matiere();

        Button economie_btn = findViewById(R.id.economie_btn);
        Button droit_btn = findViewById(R.id.droit_btn);
        Button management_btn = findViewById(R.id.management_btn);

        economie_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matiere = "économie";
                mMatiere.setNomMatiere(matiere);

                Intent QuestionActivityIntent = new Intent(MainActivity.this, QuestionActivity.class);
                QuestionActivityIntent.putExtra(MAT_NAME, mMatiere.getNomMatiere());
                startActivityForResult(QuestionActivityIntent, 0);
            }
        });
        droit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matiere = "droit";
                mMatiere.setNomMatiere(matiere);
                Intent QuestionActivityIntent = new Intent(MainActivity.this, QuestionActivity.class);
                QuestionActivityIntent.putExtra(MAT_NAME, mMatiere.getNomMatiere());
                startActivityForResult(QuestionActivityIntent, 0);
            }
        });
        management_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matiere = "management";
                mMatiere.setNomMatiere(matiere);
                Intent QuestionActivityIntent = new Intent(MainActivity.this, QuestionActivity.class);
                QuestionActivityIntent.putExtra(MAT_NAME, mMatiere.getNomMatiere());
                startActivityForResult(QuestionActivityIntent, 0);
            }
        });
    }

}
