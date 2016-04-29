package com.example.jhoang.mysqldemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Music_Sheet extends AppCompatActivity {

    String MvtNum;
    String ShowId;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__sheet);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                MvtNum= null;
            } else {
                MvtNum= extras.getString("STRING_I_NEED");
            }
        } else {
            MvtNum= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ShowId= null;
            } else {
                ShowId= extras.getString("STRING_I_NEED1");
            }
        } else {
            ShowId= (String) savedInstanceState.getSerializable("STRING_I_NEED1");
        }
    }
}
