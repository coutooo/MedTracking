package com.example.medtracking.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracking.MainActivity;
import com.example.medtracking.R;
import com.example.medtracking.databinding.ActivityTarefasBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TarefaActivity extends AppCompatActivity {

    ActivityTarefasBinding binding;

    String[] tasks;  //
    boolean[] dones;
    int size;

    Button btnTasks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarefasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fetchData();
        //btnTasks = findViewById(R.id.btnTasks);



        Intent intent = this.getIntent();
        if (intent != null)
        {
            String bed = intent.getStringExtra("bed");
            String nameAndCause = intent.getStringExtra("nameAndCause");
            int imageId = intent.getIntExtra("imageid", R.drawable.hospital_bed);
            String inAndout = intent.getStringExtra("inAndOut");

            binding.bedN.setText(bed);
            binding.nameAndCause.setText(nameAndCause);
            //binding.profileImage.setImageResource(imageId);
            binding.inAndOut.setText(inAndout);
        }
        /*
        btnTasks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bedstr = intent.getStringExtra("bed");
                        Intent i = new Intent(TarefaActivity.this, CheckBoxActivity.class);

                        String bed = bedstr.replaceAll("[^0-9]", "");

                        int bedN = Integer.parseInt(bed);

                        i.putExtra("tasks",tasks);
                        Log.d("dones", String.valueOf(dones[0]));
                        i.putExtra("dones",dones);
                        i.putExtra("id",bedN);
                        startActivity(i);
                    }
                }
        );*/

    }

    private void fetchData() {
        FirebaseFirestore.getInstance().collection("Tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            size = task.getResult().size();// deu certo
                            tasks = new String[size];
                            dones = new boolean[size];
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // ta certo
                                int position = document.getLong("idTask").intValue()-1;  // ta certo
                                Boolean done = document.getBoolean("done");
                                String taski = document.getId();

                                tasks[position] = taski;
                                dones[position] = done;
                            }
                        } else {
                            Log.d("asdsadsadsa", "Error getting documents: ", task.getException());
                        }
                    }

                });
    }
}
