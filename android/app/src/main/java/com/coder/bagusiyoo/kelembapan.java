package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class kelembapan extends AppCompatActivity {

    private EditText mlembab;
    private TextView mSolusii;
    private Button mCok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelembapan);
        Toolbar toolbar = findViewById(R.id.yo);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cek Kelembaban");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        mCok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lembabx = mlembab.getText().toString();
                if (lembabx.length() == 0) {
                    pesan("Semua kolom harus diisi!");
                } else {
                    int kelembapan = Integer.parseInt(lembabx);
                    if ((kelembapan >= 88 ) && (kelembapan < 90)) {
                        mSolusii.setText("Kelembapan Terlampau Tinggi");
                        Intent mIntent = new Intent(getApplicationContext(), curah.class);
                        startActivity(mIntent);

                    }
                    else if ((kelembapan >= 75 ) && (kelembapan < 86)) {
                        mSolusii.setText(" Kelembapan sesuai dengan kisaran budidaya melon");
                        Intent mIntent = new Intent(getApplicationContext(), curah.class);
                        startActivity(mIntent);
                    }
                    else if ((kelembapan >= 30) && (kelembapan < 60)) {
                        mSolusii.setText("Kelembapan Terlampau Rendah");
                        Intent mIntent = new Intent(getApplicationContext(), curah.class);
                        startActivity(mIntent);

                    }
                    else
                    {
                        mSolusii.setText("Format salah!");

                    }

                }
            }
        });

    }
    private void initData(){
        mlembab = findViewById(R.id.edlembab);
        mSolusii = findViewById(R.id.txt_solusii);
        mCok = findViewById(R.id.btn_cekk);

    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}