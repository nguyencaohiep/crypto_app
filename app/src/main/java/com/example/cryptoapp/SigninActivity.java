package com.example.cryptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    private EditText emailInput, passInput;
    private Button btnSignin, btnSignup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance(); // khởi tạo firebaseAuth

        emailInput = findViewById(R.id.email);
        passInput = findViewById(R.id.pass);

        btnSignin = findViewById(R.id.btn_signin);
        btnSignup = findViewById(R.id.btn_signup);

        btnSignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signin();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signin() {
        String email, pass;
        email = emailInput.getText().toString();
        pass = passInput.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Vui lòng nhập password!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(SigninActivity.this, MainActivity.class);

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        String email = user.getEmail();
                        String id = user.getUid();
                        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
                        i.putExtra("name", user.getEmail());
                        i.putExtra("id", id);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Không tồn tại user", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Sai email hoặc password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signup() {
        Intent i = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(i);
    }
}