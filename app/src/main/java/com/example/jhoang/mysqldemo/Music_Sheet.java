package com.example.jhoang.mysqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Music_Sheet extends AppCompatActivity {

    String MvtNum;
    String ShowId;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__sheet);

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
