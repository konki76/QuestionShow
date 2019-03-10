package com.example.slam_12.questionshow.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("edm_json.php")
    Call<List<Question>> getQuestions();
}
