package com.no_18002402.healthapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    EditText password, email;
    Button login, register;
    String pEmail, pPassword;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);
        progress = new ProgressDialog(MainActivity.this);


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 Intent i = new Intent(MainActivity.this,Register.class);

                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                pEmail = email.getText().toString();
                pPassword = password.getText().toString();

                progress.setTitle("Loading");
                progress.setMessage("Logging in...");
                progress.show();

                if(pEmail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    return;
                }

                if(pPassword.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }

                auth.signInWithEmailAndPassword(pEmail, pPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent j = new Intent(MainActivity.this, Menu.class);
                            startActivity(j);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Cannot access this account", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}