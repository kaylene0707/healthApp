package com.no_18002402.healthapp;

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
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {


    private FirebaseAuth auth;
    EditText email, password;
    Button reg, back;
    String uEmail, uPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        reg = findViewById(R.id.btnSign);
        back = findViewById(R.id.btnBack);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                uEmail = email.getText().toString().trim();
                uPass = password.getText().toString().trim();

                if(uEmail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                    return;
                }

                if(uPass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }

                if(uPass.length() < 8)
                {
                    Toast.makeText(getApplicationContext(), "Please enter your password of atleast 8 characters in length", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                    return;
                }

                auth.createUserWithEmailAndPassword(uEmail, uPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();

                            Intent h = new Intent(Register.this, Menu.class);
                            startActivity(h);
                        }
                        else {
                            if(task.getException() instanceof FirebaseApiNotAvailableException)
                            {
                                Toast.makeText(getApplicationContext(), "User is already registered", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Unable to register this account", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}