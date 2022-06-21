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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BedsActivity extends AppCompatActivity {

    BedsActivityBinding binding;

    private CollectionReference mDocRef = FirebaseFirestore.getInstance().collection("Pacients/");


    int[] imageId = {R.drawable.ic_user,R.drawable.indenavarrete}; // mudar para query

    String[] beds;  // mudar para query

    String[] nameAndCause;  // mudar para query  //"Joao, Caiu de Cavalo","Joana, Acidente Mota"

    String[] inAndout;  // mudar para query


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = BedsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get intents
        Bundle extras = getIntent().getExtras();
        nameAndCause = extras.getStringArray("nameAndCause");
        beds = extras.getStringArray("beds");
        inAndout = extras.getStringArray("inAndout");
        //-------------------------------------

        ArrayList<Pacient> pacientArrayList = new ArrayList<>();

        for (int i = 0;i< nameAndCause.length; i++){
            Pacient pacient = new Pacient(nameAndCause[i],beds[i],inAndout[i] );
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
                //i.putExtra("imageid",imageId[position]);
                i.putExtra("inAndOut",inAndout[position]);
                startActivity(i);
            }
        });
    }

}
