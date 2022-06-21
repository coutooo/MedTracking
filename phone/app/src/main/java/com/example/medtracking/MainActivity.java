package com.example.medtracking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medtracking.User.AllCategories;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button bMain,bLogin;
    EditText inputEmail,inputPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bMain = findViewById(R.id.btnMain);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        bLogin = findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
            }
        });

        bMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, AllCategories.class);
                        startActivity(i);
                    }
                }
        );
    }

    private void perforLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Connext Email");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}