package com.example.milan.madlips;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;
import java.util.Random;

public class MainLips extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lips);
    }

    public void Start(View view){
        Intent intent = new Intent(this, ActivityOne.class);
        startActivity(intent);
    }
}