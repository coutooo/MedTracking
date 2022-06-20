package com.example.medtracking.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracking.MainActivity;
import com.example.medtracking.R;
import com.example.medtracking.databinding.BedsActivityBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BedsActivity extends AppCompatActivity {

    BedsActivityBinding binding;

    private CollectionReference mDocRef = FirebaseFirestore.getInstance().collection("Pacients/");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BedsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.ic_user,R.drawable.indenavarrete}; // mudar para query

        String[] beds = {"Bed 1", "Bed 2"};  // mudar para query

        fetchData();

        String[] nameAndCause = {"Joao, Caiu de Cavalo","Joana, Acidente Mota"};  // mudar para query

        String[] inAndout = {"in:18-06/2022\n\nout:--/--/--","in:05-04/2022\n\nout:20/09/2022"};  // mudar para query

        ArrayList<Pacient> pacientArrayList = new ArrayList<>();

        for (int i = 0;i< imageId.length; i++){
            Pacient pacient = new Pacient(nameAndCause[i],beds[i],inAndout[i],imageId[i] );
            pacientArrayList.add(pacient);
        }

        ListAdapter listAdapter = new ListAdapter(BedsActivity.this,pacientArrayList);

        binding.bedlistview.setAdapter(listAdapter);
        binding.bedlistview.setClickable(true);
        binding.bedlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(BedsActivity.this, TarefaActivity.class);
                i.putExtra("bed",beds[position]);
                i.putExtra("nameAndCause",nameAndCause[position]);
                i.putExtra("imageid",imageId[position]);
                i.putExtra("inAndOut",inAndout[position]);
                startActivity(i);
            }
        });
    }

    public void fetchData(){
        DocumentReference document = FirebaseFirestore.getInstance().collection("Pacients").document();
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    Log.d("adsdsa",documentSnapshot.getString("name"));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Row not found.",Toast.LENGTH_LONG).show();
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to fetch data.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
