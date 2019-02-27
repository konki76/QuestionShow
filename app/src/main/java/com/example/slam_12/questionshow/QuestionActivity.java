package com.example.slam_12.questionshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MAT_NAME);
        final TextView tv1 = (TextView)findViewById( R.id.question_txt );
        tv1.setText( message );

        Button b_retour =(Button)findViewById(R.id.retour_btn);
        b_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent();
                i2.putExtra(MainActivity.MAT_NAME, "valeur de retour");
                QuestionActivity.this.setResult(1, i2);
                QuestionActivity.this.finish();
            }
        });
    }

}
