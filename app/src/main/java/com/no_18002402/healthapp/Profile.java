package com.no_18002402.healthapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Details");

    EditText Name, Age, Weight, Height, targetW, targetS;
    Button save, back;
    BottomNavigationView nav;
    //UserDetails details = new UserDetails();
    ProgressDialog pro;
    RadioGroup radGroup;
    RadioButton example;
    ToggleButton togHeight, togWeight;
    String namePush, agePush, genderPush, heightPush, weightPush, targetWeightPush, targetStepsPush;


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
        targetS = findViewById(R.id.tarSteps);
        radGroup = findViewById(R.id.temp);
        togHeight = findViewById(R.id.toggleBtnHeight);
        togWeight = findViewById(R.id.toggleBtnWeight);

        pro = new ProgressDialog(Profile.this);

        panels();

        togHeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                if(Height != null) {
                    if (isChecked) {
                        String h = Height.getText().toString();
                        double convHeight = Double.parseDouble(h);

                        double newHeight = convHeight * 3.281;

                        String a = "" + newHeight;
                        Height.setText(a);

                    } else {
                        String h = Height.getText().toString();
                        double convHeight = Double.parseDouble(h);

                        double newHeight = convHeight / 3.281;

                        String text = "" + newHeight;
                        Height.setText(text);
                    }
                }
                else
                {
                    Toast.makeText(Profile.this, "Please insert values before converting", Toast.LENGTH_LONG).show();
                }
            }
        });

        togWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Weight != null || targetW != null) {
                    if (isChecked) {
                        String w = Weight.getText().toString();
                        double convWeight = Double.parseDouble(w);
                        String tarW = targetW.getText().toString();
                        double convTW = Double.parseDouble(tarW);

                        double newWeight = convWeight * 2.205;
                        double newTarW = convTW * 2.205;

                        String text = "" + newWeight;
                        Weight.setText(text);
                        String textTar = "" + newTarW;
                        targetW.setText(textTar);
                    } else {
                        String w = Weight.getText().toString();
                        double convWeight = Double.parseDouble(w);
                        String tarW = targetW.getText().toString();
                        double convTW = Double.parseDouble(tarW);

                        double newWeight = convWeight / 2.205;
                        double newTarW = convTW / 2.205;

                        String text = "" + newWeight;
                        Weight.setText(text);
                        String textTar = "" + newTarW;
                        targetW.setText(textTar);
                    }
                } else
                {
                    Toast.makeText(Profile.this, "Please insert values before converting", Toast.LENGTH_LONG).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pro.setMessage("Uploading..");
                pro.show();

                int selection = radGroup.getCheckedRadioButtonId();
                example = findViewById(selection);

                namePush = Name.getText().toString();
                agePush = Age.getText().toString();
                genderPush = example.getText().toString();
                heightPush = Height.getText().toString();
                weightPush = Weight.getText().toString();
                targetWeightPush = targetW.getText().toString();
                targetStepsPush = targetS.getText().toString();

                //determine(heightPush, weightPush, targetWeightPush, targetHeightPush);

                if(namePush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else if(agePush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else if(genderPush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else if(heightPush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else if(weightPush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else if(targetWeightPush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else if(targetStepsPush.isEmpty())
                {
                    Toast.makeText(Profile.this, "Enter information in each field", Toast.LENGTH_LONG).show();
                }
                else
                {
                    UserDetails details = new UserDetails();
                    details.setFullName(namePush);
                    details.setAge(agePush);
                    details.setGender(genderPush);
                    details.setHeight(heightPush);
                    details.setWeight(weightPush);
                    details.setTargetWeight(targetWeightPush);
                    details.setTargetSteps(targetStepsPush);

                    dataref.child("Details").setValue(details);
                    Toast.makeText(Profile.this, "Successfully saved user information", Toast.LENGTH_LONG).show();
                    pro.dismiss();
                }
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
        nav.setSelectedItemId(R.id.settings);
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