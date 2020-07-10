package com.jojo.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email, pass;
    Button loginButt;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.userEmail);
        pass = findViewById(R.id.userPwd);
        loginButt = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Login.this, Home.class));
                            Toast.makeText(Login.this, "Welcome, "+auth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Login.this, "Try Later!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
