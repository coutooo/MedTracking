package com.example.medtracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rTratamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        rTratamento = findViewById(R.id.relativeLayoutTratamentos);

        rTratamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("string","string");
            }
        });
    }
}