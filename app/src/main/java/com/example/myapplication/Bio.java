package com.example.myapplication;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Bio {

    String BASE_URL = "https://ganjgah.ir/api/ganjoor/";

    @GET("poets")
    Call<List<Poet>> getPoets();
}