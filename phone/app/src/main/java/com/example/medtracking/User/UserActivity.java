package com.example.medtracking.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import org.w3c.dom.Text;

public class UserActivity  extends AppCompatActivity {

    TextView pacientsN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);


        pacientsN = findViewById(R.id.pacientsNumberText);




        Intent intent = this.getIntent();
        if (intent != null)
        {
            int size = intent.getIntExtra("npacients", 0);

            Log.d("size", String.valueOf(size));

            pacientsN.setText(String.valueOf(size));
        }


    }
}
