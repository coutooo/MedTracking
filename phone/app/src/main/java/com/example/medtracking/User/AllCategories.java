package com.example.medtracking.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.medtracking.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AllCategories extends AppCompatActivity {

    ImageView backBtn;
    RelativeLayout rTratamento,rMedicamentos,rInventario,rBatimentos;

    // teste
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Nurse/1");

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

                mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String nurseName = documentSnapshot.getString("Nome");

                            Log.d("Nome",nurseName);
                        }
                    }
                });

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