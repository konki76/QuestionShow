package com.example.slam_12.questionshow.Model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Json {
    @GET("edm_json.php")
    Call<List<Question>> getQuestions(@Query("m") String codeMatiere);

    @GET("edm_json.php")
    Call<ResponseBody> insert(@Query("setMarqueur") String codeQu);

    @GET("edm_json.php")
    Call<ResponseBody> del(@Query("delMarqueur") String codeQu);
}
