package com.no_18002402.healthapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Weight_Log extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Information");

    EditText record, date;
    Button sub, graph;
    BottomNavigationView nav;
    static final String NAME = "Weight_Log";
    //String weeklyweight;
    //Date today = new Date();
    DatePickerDialog.OnDateSetListener gui;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight__log);

        record = findViewById(R.id.weeklyweight);
        date = findViewById(R.id.Date);
        sub = findViewById(R.id.btnSubmit);
        graph = findViewById(R.id.btnprog);

        panels();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String title = "Information" + count;

                String weeklyW = record.getText().toString();
                String now = date.getText().toString();
                String pass = now + "," + weeklyW;

                dataref.child(title).setValue(pass);
                Toast.makeText(Weight_Log.this, "Successfully stored the users weight", Toast.LENGTH_LONG).show();

            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentt = new Intent(Weight_Log.this, Display.class);
                startActivity(intentt);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dateDial = new DatePickerDialog(Weight_Log.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, gui, year, month, day);
                dateDial.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDial.show();
            }
        });

        gui = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(NAME, "onDataSet: mm/dd/yyyy: " + year + "/" + month + "/" + dayOfMonth);
                String today = dayOfMonth + "/" + month + "/" + year;
                date.setText(today);
            }
        };

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
                        Intent a = new Intent(Weight_Log.this, Menu.class);
                        startActivity(a);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.wLog:
                        Intent b = new Intent(Weight_Log.this, Weight_Log.class);
                        startActivity(b);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.cam:
                        Intent c = new Intent(Weight_Log.this, Camera.class);
                        startActivity(c);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.fLog:
                        Intent d = new Intent(Weight_Log.this, Food_Log.class);
                        startActivity(d);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.settings:
                        Intent e = new Intent(Weight_Log.this, Settings.class);
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