package com.jojo.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    TextView wel;
    Button logout;
    FirebaseAuth auth;
    LinearLayout layout;
    EditText addmail, addPwd;
    Button addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        wel = findViewById(R.id.welcome);
        logout = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();

        addmail = findViewById(R.id.addEmail);
        addPwd = findViewById(R.id.addPwd);
        addUser = findViewById(R.id.addUserButt);
        layout = findViewById(R.id.layout);

        String mail = auth.getCurrentUser().getEmail();
        wel.setText("Welcome, "+mail);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Signout Successful", Toast.LENGTH_LONG).show();
                auth.signOut();
                finish();
            }
        });

        if(auth.getCurrentUser().getEmail().equals("yesh.jojo444@gmail.com"))
            layout.setVisibility(View.VISIBLE);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(addmail.getText().toString().trim(), addPwd.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Home.this, "User created successfully", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
