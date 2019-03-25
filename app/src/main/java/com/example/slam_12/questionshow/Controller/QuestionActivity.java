package com.example.slam_12.questionshow.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.slam_12.questionshow.Model.Json;
import com.example.slam_12.questionshow.Model.QuestionBank;
import com.example.slam_12.questionshow.Model.Question;
import com.example.slam_12.questionshow.Model.RF;
import com.example.slam_12.questionshow.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
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
    private Button mMarqueur;
    private int mNumberOfQuestions;
    private boolean mRectoVerso = true;
    private boolean mMarqueurBool = false;
    private List<Question> listQuestion;
    private int nb_question;

    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        listQuestion = new ArrayList<Question>();

        final Intent intent = getIntent();
        String matiere = intent.getStringExtra(MainActivity.MAT_NAME);
        String codeMatiere = intent.getStringExtra(MainActivity.MAT_CODE);

        mQuestion = (TextView) findViewById(R.id.question_txt);
        mAnswer = (Button) findViewById(R.id.answer_btn);
        Button b_retour =(Button)findViewById(R.id.retour_btn);
        mMarqueur = (Button) findViewById(R.id.marqueur_btn);
        TextView matiere_txt = (TextView)findViewById( R.id.qu_txt );
        matiere_txt.setText( matiere );

        listQuestion = getQuestions(codeMatiere);
        nb_question = 10;
        listQuestion.add(new Question("codeQu",
                "Appuyer sur le champs du bas pour afficher les réponses et passer aux questions suivantes",
                "Essayer de réfléchir avant d'afficher la réponse. " +nb_question+ " questions vont vous être proposées."
        ));
        mQuestionBank = new QuestionBank(listQuestion);

        if (savedInstanceState != null) {
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mNumberOfQuestions = nb_question + 1;
        }

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

        mMarqueur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mMarqueurBool) {
                    delMarqueur();
                    mMarqueurBool = false;
                    mMarqueur.setBackgroundColor(Color.WHITE);
                    mMarqueur.setTextColor(Color.BLACK);
                }
                else {
                    insertMarqueur();
                    mMarqueurBool = true;
                    mMarqueur.setBackgroundColor(Color.BLUE);
                    mMarqueur.setTextColor(Color.WHITE);
                }

            }
        });
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
                        endGame();
                    } else {
                        mCurrentQuestion = mQuestionBank.getQuestion();
                        mQuestion.setText(mCurrentQuestion.getQuestion());
                        mRectoVerso = true;
                        mAnswer.setText("");
                        mMarqueur.setBackgroundColor(Color.WHITE);
                        mMarqueur.setTextColor(Color.BLACK);
                        mMarqueurBool = false;
                        nb_question--;
                    }
                } else {
                    mRectoVerso = false;
                    mAnswer.setText(mCurrentQuestion.getAnswer());
                }
            }
        }, 0);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
    private  List<Question> getQuestions(String codeMatiere){
        List<Question> listQu = new ArrayList<Question>();
        RF rf = new RF("http://mat.planchot.free.fr/");
        Json json = rf.getJson();
        Call<List<Question>> call;
        call = json.getQuestions(codeMatiere);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()) {
                    mAnswer.setText("Code : " + response.code());
                    return;
                }
                listQu.addAll(response.body());
                Collections.shuffle(listQu);
            }
            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                mAnswer.setText(t.getMessage());
            }
        });
        return listQu;
    }
    private void delMarqueur(){
        RF rf = new RF("http://mat.planchot.free.fr/");
        Json json = rf.getJson();
        json.del(mCurrentQuestion.getCodeQu()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mMarqueur.setText("Marquer");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mMarqueur.setText("Failure");
            }
        });
        Log.d("codeQuDel", mCurrentQuestion.getCodeQu());
    }
    private void  insertMarqueur(){
        RF rf = new RF("http://mat.planchot.free.fr/");
        Json json = rf.getJson();
        json.insert(mCurrentQuestion.getCodeQu()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mMarqueur.setText("Favoris");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mMarqueur.setText("Failure");
            }
        });
        Log.d("codeQuInsert", mCurrentQuestion.getCodeQu());
    }
}
