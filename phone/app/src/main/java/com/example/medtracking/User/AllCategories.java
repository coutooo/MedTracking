package com.example.medtracking.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.medtracking.MainActivity;
import com.example.medtracking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


// da jeito para o firestore https://www.youtube.com/watch?v=y2op1D0W8oE

public class AllCategories extends AppCompatActivity {

    ImageView backBtn;
    RelativeLayout rTratamento,rMedicamentos,rInventario,rBatimentos,rBluetooth,rNFC;

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
        rBluetooth = findViewById(R.id.relativeLayoutBluetooth);
        rNFC = findViewById(R.id.relativeLayoutNFC);

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

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> nursess = new HashMap<>();
                nursess.put("Id","3");
                nursess.put("Nome","Jessica");

                db.collection("Nurse").add(nursess).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AllCategories.this,"Valus addded!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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

        //botao do bluetooth
        rBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Bluetooth","Bluetooth");
                Intent i = new Intent(AllCategories.this, BluetoothActivity.class);
                startActivity(i);
            }
        });

        //botao do nfc
        rNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NFC","NFC");
                Intent i = new Intent(AllCategories.this, NFCActivity.class);
                startActivity(i);
            }
        });

    }
}