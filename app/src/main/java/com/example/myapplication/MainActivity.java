package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);

        tv1.setOnClickListener(v->  {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);

        });

        tv2.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,PoetBio.class);
            startActivity(i);

        });

    }
}