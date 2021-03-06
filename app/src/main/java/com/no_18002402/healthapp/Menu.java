package com.no_18002402.healthapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Details");
    SensorManager sManager;
    Sensor sens;
    TextView targetW, targetS, stepC;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        stepC = findViewById(R.id.ped);
        targetS = findViewById(R.id.sGoal);
        targetW = findViewById(R.id.wGoal);

        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



        panels();
        Counter();

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String g1 = dataSnapshot.child("targetWeight").getValue(String.class);
                String g2 = dataSnapshot.child("targetSteps").getValue(String.class);

                targetW.setText(g1);
                targetS.setText(g2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void onSensorChanged(SensorEvent event)
    {
        String a = " " + event.values[0];
        stepC.setText(a);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
    public void Counter()
    {
        sens = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        sManager.registerListener((SensorEventListener) this, sens, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void panels()
    {
        nav = findViewById(R.id.navigation);
        nav.setSelectedItemId(R.id.home);


        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent a = new Intent(Menu.this, Menu.class);
                        startActivity(a);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.wLog:
                        Intent b = new Intent(Menu.this, Weight_Log.class);
                        startActivity(b);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.cam:
                        Intent c = new Intent(Menu.this, Camera.class);
                        startActivity(c);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.fLog:
                        Intent d = new Intent(Menu.this, Food_Log.class);
                        startActivity(d);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.settings:
                        Intent e = new Intent(Menu.this, Settings.class);
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