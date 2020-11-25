package com.no_18002402.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Details");

    TextView Name, Age, Weight, Height, tWeight, tSteps, gen;
    String namePull, agePull, genderPull, heightPull, weightPull, targetWeightPull, targetStepsPull;
    Button edit, logout;
    BottomNavigationView nav;
    UserDetails details = new UserDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        panels();

        edit = findViewById(R.id.btnEdit);
        logout = findViewById(R.id.btnOut);
        Name = findViewById(R.id.name);
        Age = findViewById(R.id.age);
        gen = findViewById(R.id.gender);
        Weight = findViewById(R.id.weight);
        Height = findViewById(R.id.height);
        tWeight = findViewById(R.id.tarWeight);
        tSteps = findViewById(R.id.tarSteps);


        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                namePull = dataSnapshot.child("fullName").getValue(String.class);
                agePull = dataSnapshot.child("age").getValue(String.class);
                genderPull = dataSnapshot.child("gender").getValue(String.class);
                heightPull = dataSnapshot.child("height").getValue(String.class);
                weightPull = dataSnapshot.child("weight").getValue(String.class);
                targetWeightPull = dataSnapshot.child("targetWeight").getValue(String.class);
                targetStepsPull = dataSnapshot.child("targetSteps").getValue(String.class);


                Name.setText(namePull);
                Age.setText(agePull);
                gen.setText(genderPull);
                Height.setText(heightPull);
                Weight.setText(weightPull);
                tWeight.setText(targetWeightPull);
                tSteps.setText(targetStepsPull);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Settings.this, "An error has occurred", Toast.LENGTH_LONG).show();

            }
        });



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Editt = new Intent(Settings.this, Profile.class);
                startActivity(Editt);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent out = new Intent(Settings.this, MainActivity.class);
                startActivity(out);
            }
        });
    }

    public void panels()
    {
        nav = findViewById(R.id.navigation);
        nav.setSelectedItemId(R.id.settings);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent a = new Intent(Settings.this, Menu.class);
                        startActivity(a);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.wLog:
                        Intent b = new Intent(Settings.this, Weight_Log.class);
                        startActivity(b);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.cam:
                        Intent c = new Intent(Settings.this, Camera.class);
                        startActivity(c);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.fLog:
                        Intent d = new Intent(Settings.this, Food_Log.class);
                        startActivity(d);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.settings:
                        Intent e = new Intent(Settings.this, Settings.class);
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