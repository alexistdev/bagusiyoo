package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coder.bagusiyoo.sensor.Sensordashboard;
import com.google.android.material.card.MaterialCardView;

public class LoaderActivity extends AppCompatActivity {

    private MaterialCardView mSensor,mDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        initData();
        mSensor.setOnClickListener(view -> {
            Intent mIntent = new Intent(LoaderActivity.this, Sensordashboard.class);
            startActivity(mIntent);
        });
        mDiary.setOnClickListener(view -> {
            Intent mIntent = new Intent(LoaderActivity.this, MainActivity.class);
            startActivity(mIntent);
        });
    }

    private void initData(){
        mSensor  = findViewById(R.id.dtMenuSensor);
        mDiary  = findViewById(R.id.dtMenuDiary);
    }
}