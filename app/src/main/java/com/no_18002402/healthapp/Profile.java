package com.no_18002402.healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Details");

    EditText Name, Age, Weight, Height, targetW, targetH;
    Button save, back;
    BottomNavigationView nav;
    UserDetails details = new UserDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nav = findViewById(R.id.navigation);
        save = findViewById(R.id.btnSave);
        back = findViewById(R.id.btnBack);

        Name = findViewById(R.id.name);
        Age = findViewById(R.id.age);
        Weight = findViewById(R.id.weight);
        Height = findViewById(R.id.height);
        targetW = findViewById(R.id.tarWeight);
        targetH = findViewById(R.id.tarHeight);

        panels();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                details.setFullName(Name.getText().toString());
                details.setAge(Age.getText().toString());
                details.setHeight(Height.getText().toString());
                details.setWeight(Weight.getText().toString());
                details.setTargetWeight(targetW.getText().toString());
                details.setTargetHeight(targetH.getText().toString());

                dataref.push().child("Information").setValue(details);
                Toast.makeText(Profile.this, "Successfully saved user information", Toast.LENGTH_LONG).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Profile.this, Settings.class);
                startActivity(in);
            }
        });
    }
    public void panels()
    {
        nav = findViewById(R.id.navigation);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent a = new Intent(Profile.this, Menu.class);
                        startActivity(a);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.wLog:
                        Intent b = new Intent(Profile.this, Weight_Log.class);
                        startActivity(b);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.cam:
                        Intent c = new Intent(Profile.this, Camera.class);
                        startActivity(c);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.fLog:
                        Intent d = new Intent(Profile.this, Food_Log.class);
                        startActivity(d);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.settings:
                        Intent e = new Intent(Profile.this, Settings.class);
                        startActivity(e);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}