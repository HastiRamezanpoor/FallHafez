package com.example.myapplication;



import retrofit2.Call;
import retrofit2.http.GET;

public interface FalApi {

    String BASE_URL = "https://ganjgah.ir/api/ganjoor/hafez/";

    @GET("faal")
    Call<Faal> getFal();
}
