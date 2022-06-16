package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FallActivity extends AppCompatActivity {

    TextView poetry, number;
    ImageView back;
    String random_string = "";
    TextView textView;

    ImageView again;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);

        poetry = findViewById(R.id.Poetry);
        number = findViewById(R.id.number_ghazal);


        back=findViewById(R.id.imageView2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FallActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        textView=findViewById(R.id.textView7);
        again = findViewById(R.id.imageView6);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFaal();

            }
        });




        getFaal();

        int random_string_length = 5;
        String[] all_characters = {
                "دل به کمک و وعده های توخالی دیگران نباشید و به آن ها تکیه نکنید، برای رسیدن به آرزوها باید به توانایی های خود تکیه کرده و با اراده قوی و همت عالی اقدام کنید.",
                "از بازگویی اسرار خود به دیگران دقت کنید و راز دل را پیش هرکسی فاش نکنید. به زودی خبرهای خوبی از مسافرتان دریافت می کنید. رنج و انتظار لازمه رسیدن به مراد دل است. از عجله برحذر باشید. ",
                "ناامیدی را کنار بگذارید و سایه شک و بدگمانی به دیگران را از دل خود پاک کنید.",
                "آنقدر منتظر خبر هستید که ممکن است روی درست انجام دادن کارهای تان تاثیر منفی بگذارد، مراقب باشید و همه چیز را به خداوند سپرده و به امور مهم زندگی بپردازید.",
                "آن چه در زندگی دارید همه به لطف پروردگار و تلاش خودتان حاصل شده، مراقب باشید تا آن چه به سختی به دست آورده اید به راحتی به باد ندهید.",
                "درست است که تحمل سختی ها برای شما دشوار است و تاکنون صبوری بسیار کرده اید، اما مطمئن باشید به زودی پاداش صبر و شکیبایی خود را خواهید دید و به آرزوهای خود می رسید. به خداوند توکل کنید. \n"
        };
        int all_characters_length = all_characters.length;

        int min = 0;
        int max = all_characters_length-1;



        Random r = new Random();
        int random_number = r.nextInt(max - min + 1) + min;
        String random_character = all_characters[random_number];
        random_string = random_string + random_character;












    }


    private void getFaal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FalApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FalApi falApi = retrofit.create(FalApi.class);
        Call<Faal> call = falApi.getFal();
        call.enqueue(new Callback<Faal>() {
            @Override
            public void onResponse(Call<Faal> call, Response<Faal> response) {
                if (response.isSuccessful()) {
                    Faal faal = response.body();
                    number.setText(faal.getTitle());
                    poetry.setText(faal.getPlainText());
                    textView.setText(random_string);








                }
            }

            @Override
            public void onFailure(Call<Faal> call, Throwable t) {
                Toast.makeText(FallActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}