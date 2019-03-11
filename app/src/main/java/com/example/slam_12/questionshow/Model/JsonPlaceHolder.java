package com.example.slam_12.questionshow.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("edm_json.php")
    Call<List<Question>> getQuestions();
    @GET("edm_json.php?m=E")
    Call<List<Question>> getQuestionsE();
    @GET("edm_json.php?m=D")
    Call<List<Question>> getQuestionsD();
    @GET("edm_json.php?m=M")
    Call<List<Question>> getQuestionsM();
}
