package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class kembali extends AppCompatActivity {



    private Button mkembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kembali);
        Toolbar toolbar = findViewById(R.id.lo);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Menu Peringatan");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        mkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mIntent);

                    }



        });

    }
    private void initData(){


        mkembali = findViewById(R.id.btn_home);

    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}