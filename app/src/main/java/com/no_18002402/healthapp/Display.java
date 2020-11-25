package com.no_18002402.healthapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Display extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataref = database.getReference("Information");

    BottomNavigationView nav;
    Button returnn;
    LineChart LineC;
    List<String> weights = new ArrayList<>();

    //ListView lView;

    //List<String> measurements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        returnn = findViewById(R.id.btnReturn);
        nav = findViewById(R.id.navigation);
        LineC = findViewById(R.id.line);

        panels();

        LineC.setDragEnabled(true);
        LineC.setScaleEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Display.this, android.R.layout.simple_list_item_1, weights);
        //lView.setAdapter(adapter);

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    String list = child.getValue(String.class);
                    weights.add(list);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayList<Entry> yValues = new ArrayList<>();
        //yValues.equals(weights);
        yValues.add(new Entry(0, 20));
        yValues.add(new Entry(1, 35));
        yValues.add(new Entry(2, 10));
        yValues.add(new Entry(3, 15));
        yValues.add(new Entry(4, 20));
        yValues.add(new Entry(5, 85));
        yValues.add(new Entry(6, 45));
        yValues.add(new Entry(7, 75));

        LineDataSet one = new LineDataSet(yValues, "Weight Values");

        one.setFillAlpha(110);
        one.setColor(Color.GREEN);
        one.setLineWidth(3f);
        one.setValueTextSize(10f);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(one);


        LineData data = new LineData(sets);
        LineC.setData(data);

        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(Display.this, Weight_Log.class);
                startActivity(inn);
            }
        });

    }

    public void panels() {
        nav = findViewById(R.id.navigation);
        nav.setSelectedItemId(R.id.wLog);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(Display.this, Menu.class);
                        startActivity(a);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.wLog:
                        Intent b = new Intent(Display.this, Weight_Log.class);
                        startActivity(b);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.cam:
                        Intent c = new Intent(Display.this, Camera.class);
                        startActivity(c);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.fLog:
                        Intent d = new Intent(Display.this, Food_Log.class);
                        startActivity(d);
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                    case R.id.settings:
                        Intent e = new Intent(Display.this, Settings.class);
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