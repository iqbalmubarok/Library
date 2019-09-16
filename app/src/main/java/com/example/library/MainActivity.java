package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnZxing, btnItextG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnZxing = findViewById(R.id.btn1);
        btnItextG = findViewById(R.id.btn2);

        btnZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Zxing = new Intent(MainActivity.this, libraryZxing.class);
                startActivity(Zxing);
            }
        });

        btnItextG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PDF = new Intent(MainActivity.this, libraryPdf.class);
                startActivity(PDF);
            }
        });
    }
}
