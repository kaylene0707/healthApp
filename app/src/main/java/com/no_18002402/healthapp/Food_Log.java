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

public class Food_Log extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Diary");
    EditText entry, Date;
    Button submit, diary;
    BottomNavigationView nav;
    static final String FOOD ="Food_Log";
    DatePickerDialog.OnDateSetListener gui;
    //Date today = new Date();
    //String meals;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__log);

        entry = findViewById(R.id.weeklyweight);
        submit = findViewById(R.id.btnSubmit);
        diary = findViewById(R.id.btnprog);
        Date = findViewById(R.id.Date);

        panels();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String title = "Diary" + count;

                String meals = entry.getText().toString();
                String date = Date.getText().toString();
                String pass = date + "," + meals;

                dataref.child(title).setValue(pass);
                Toast.makeText(Food_Log.this, "Successfully stored the users Meals", Toast.LENGTH_LONG).show();

            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dia = new Intent(Food_Log.this, Diary.class);
                startActivity(dia);
            }
        });

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dateDial = new DatePickerDialog(Food_Log.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, gui, year, month, day);
                dateDial.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dateDial.show();
            }
        });

        gui = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(FOOD, "onDataSet: mm/dd/yyyy: " + year + "/" + month + "/" + dayOfMonth);
                String today = dayOfMonth + "/" + month + "/" + year;
                Date.setText(today);
            }
        };
    }

    public void panels()
    {
        nav = findViewById(R.id.navigation);
        nav.setSelectedItemId(R.id.fLog);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent a = new Intent(Food_Log.this, Menu.class);
                        startActivity(a);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.wLog:
                        Intent b = new Intent(Food_Log.this, Weight_Log.class);
                        startActivity(b);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.cam:
                        Intent c = new Intent(Food_Log.this, Camera.class);
                        startActivity(c);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.fLog:
                        Intent d = new Intent(Food_Log.this, Food_Log.class);
                        startActivity(d);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.settings:
                        Intent e = new Intent(Food_Log.this, Settings.class);
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