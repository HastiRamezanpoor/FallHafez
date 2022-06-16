package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PoetBio extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PoetAdapter adapter;
    List<Poet> poets;
    ImageView backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poet_bio);


        backward=findViewById(R.id.imageView3);
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PoetBio.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.BioRV);
        poets = new ArrayList<>();

        adapter = new PoetAdapter(getApplicationContext(), poets);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Bio.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Bio bioApi = retrofit.create(Bio.class);
        Call<List<Poet>> call = bioApi.getPoets();
        call.enqueue(new Callback<List<Poet>>() {
            @Override
            public void onResponse(Call<List<Poet>> call, Response<List<Poet>> response) {
                if (response.isSuccessful()) {
                    poets.clear();
                    poets.addAll(response.body());
                    //Toast.makeText(Biography_of_poets.this, "" + poets.get(0).getName(), Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Poet>> call, Throwable t) {
                Toast.makeText(PoetBio.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}