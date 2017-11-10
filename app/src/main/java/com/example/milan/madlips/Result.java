package com.example.milan.madlips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String received = intent.getStringExtra("ourtext");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(received);
    }
}
