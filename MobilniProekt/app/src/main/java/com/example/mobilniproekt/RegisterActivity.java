package com.example.mobilniproekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText mEmail;
    EditText mPassword;
    Button mRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        mEmail=findViewById(R.id.ETEmailR);
        mPassword=findViewById(R.id.ETPasswordR);
        mRegister=findViewById(R.id.BTNRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString();
                String pass=mPassword.getText().toString();
                if(email.isEmpty()){
                    mEmail.setError("Please eneter an email");
                    mEmail.requestFocus();
                }
                else if(pass.isEmpty()){
                    mPassword.setError("Please eneter a password");
                    mPassword.requestFocus();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "invalid email or password", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }
}
