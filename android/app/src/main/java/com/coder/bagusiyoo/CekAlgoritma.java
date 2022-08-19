package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CekAlgoritma extends AppCompatActivity {
    private EditText mSuhu;
    private TextView mSolusi;
    private Button mCek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_algoritma);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cek Suhu");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        mCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suhux = mSuhu.getText().toString();
                if (suhux.length() == 0) {
                    pesan("Semua kolom harus diisi!");
                } else {
                    int suhu = Integer.parseInt(suhux);
                    if ((suhu >= 26 ) && (suhu <= 999)) {
                        mSolusi.setText("Suhu Telampau Tinggi");
                        Intent mIntent = new Intent(getApplicationContext(), kelembapan.class);
                        startActivity(mIntent);

                    }
                    else if ((suhu >=21 ) && (suhu <= 26)) {
                        mSolusi.setText(" suhu cocok untuk budidaya");
                        Intent mIntent = new Intent(getApplicationContext(), kelembapan.class);
                        startActivity(mIntent);
                    }
                    else if ((suhu >=1 ) && (suhu <= 20)) {
                        mSolusi.setText("Terlampau rendah");
                        Intent mIntent = new Intent(getApplicationContext(), kelembapan.class);
                        startActivity(mIntent);

                    }
                    else
                    {
                        mSolusi.setText("Format salah!");

                    }

                }
            }
        });

    }
    private void initData(){
        mSuhu = findViewById(R.id.edSuhu);
        mSolusi = findViewById(R.id.txt_solusi);
        mCek = findViewById(R.id.btn_cek);

    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}