package com.example.medtracking.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracking.MainActivity;
import com.example.medtracking.R;
import com.example.medtracking.databinding.ActivityTarefasBinding;

public class TarefaActivity extends AppCompatActivity {

    ActivityTarefasBinding binding;

    Button btnTasks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTarefasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnTasks = findViewById(R.id.btnTasks);

        btnTasks.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(TarefaActivity.this, CheckBoxActivity.class);
                        startActivity(i);
                    }
                }
        );

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
