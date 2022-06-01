package com.example.medtracking.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.medtracking.R;

public class AllCategories extends AppCompatActivity {

    ImageView backBtn;
    RelativeLayout rTratamento,rMedicamentos,rInventario,rBatimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        //hooks
        backBtn = findViewById(R.id.back_pressed);
        rTratamento = findViewById(R.id.relativeLayoutTratamentos);
        rMedicamentos = findViewById(R.id.relativeLayoutMedicamentos);
        rInventario = findViewById(R.id.relativeLayoutInventario);
        rBatimentos = findViewById(R.id.relativeLayoutBatimentos);

        // botao para andar para tras
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllCategories.super.onBackPressed();
            }
        });

        // botao do cartao de tratamento
        rTratamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tratamentos","Tratamentos");
            }
        });

        // botao do cartao de medicamentos
        rMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Medicamentos","Medicamentos");
            }
        });

        //botao do cartao de inventario
        rInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Inventario","Inventario");
            }
        });

        //botao do cartao de batimentos
        rBatimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Batimentos","Batimentos");
            }
        });

    }
}