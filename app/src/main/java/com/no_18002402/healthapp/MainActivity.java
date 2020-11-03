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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    EditText password, email;
    Button login, register;
    String pEmail, pPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent i = new Intent(this, Register.class);
                //Intent i = new Intent(new MainActivity.this,Register.class);
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