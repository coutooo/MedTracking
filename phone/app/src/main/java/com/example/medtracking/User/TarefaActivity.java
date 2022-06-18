package com.example.medtracking.User;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracking.R;
import com.example.medtracking.databinding.ActivityTarefasBinding;

public class TarefaActivity extends AppCompatActivity {

    ActivityTarefasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarefasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null)
        {
            String bed = intent.getStringExtra("bed");
            String nameAndCause = intent.getStringExtra("nameAndCause");
            int imageId = intent.getIntExtra("imageid", R.drawable.indenavarrete);
            String inAndout = intent.getStringExtra("inAndOut");

            binding.bedN.setText(bed);
            binding.nameAndCause.setText(nameAndCause);
            binding.profileImage.setImageResource(imageId);
            binding.inAndOut.setText(inAndout);



        }
    }
}
