package com.example.slam_12.questionshow.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.slam_12.questionshow.Model.JsonPlaceHolder;
import com.example.slam_12.questionshow.Model.QuestionBank;
import com.example.slam_12.questionshow.Model.OkHttp;
import com.example.slam_12.questionshow.Model.Question;
import com.example.slam_12.questionshow.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private TextView mQuestion;
    private Button mAnswer;
    private int mNumberOfQuestions;
    private boolean mRectoVerso = true;
    private List<Question> listQuestion;

    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        listQuestion = new ArrayList<Question>();

        mQuestion = (TextView) findViewById(R.id.question_txt);
        mAnswer = (Button) findViewById(R.id.answer_btn);
        Button b_retour =(Button)findViewById(R.id.retour_btn);

        /*
        OkHttp ok = new OkHttp();
        String jsonQuestion = null;
        try {
            jsonQuestion = ok.getJson("http://mat.planchot.free.fr/edm_json.php");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonQuestion);
        List<Question> listQuestion = new Gson().fromJson(jsonQuestion, new TypeToken<List<Question>>() {}.getType());
        */
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.10.14.201/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        Call<List<Question>> call = jsonPlaceHolder.getQuestions();

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()) {
                    mAnswer.setText("Code : " + response.code());
                    return;
                }
                listQuestion.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                mAnswer.setText(t.getMessage());
            }
        });
        listQuestion.add(new Question("codeQu", "question", "answer"));
        mQuestionBank = new QuestionBank(listQuestion);

        if (savedInstanceState != null) {
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mNumberOfQuestions = 9;
        }

        final Intent intent = getIntent();
        String matiere = intent.getStringExtra(MainActivity.MAT_NAME);
        final TextView tv1 = (TextView)findViewById( R.id.qu_txt );
        tv1.setText( matiere );

        b_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent();
                i2.putExtra(MainActivity.MAT_NAME, "valeur de retour");
                QuestionActivity.this.setResult(1, i2);
                QuestionActivity.this.finish();
            }
        });

        mAnswer.setOnClickListener(this);
        mCurrentQuestion = mQuestionBank.getQuestion();
        mQuestion.setText(mCurrentQuestion.getQuestion());
    }
    @Override
    public void onClick(View v) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // If this is the last question, ends the game.
                // Else, display the next question.
                if (!mRectoVerso) {
                    if (--mNumberOfQuestions == 0) {
                        // End the game
                        endGame();
                    } else {
                        mCurrentQuestion = mQuestionBank.getQuestion();
                        mQuestion.setText(mCurrentQuestion.getQuestion());
                        mRectoVerso = true;
                        mAnswer.setText("");
                    }
                } else {
                    mRectoVerso = false;
                    mAnswer.setText(mCurrentQuestion.getAnswer());
                }
            }
        }, 0); // LENGTH_SHORT is usually 2 second long
    }
    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
