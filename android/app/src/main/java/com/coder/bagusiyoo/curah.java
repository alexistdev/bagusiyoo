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

public class curah extends AppCompatActivity {

    private EditText mcurah;
    private TextView mSolusiii;
    private Button mCik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curah);
        Toolbar toolbar = findViewById(R.id.ya);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cek Curah Hujan");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        mCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curahx = mcurah.getText().toString();
                if (curahx.length() == 0) {
                    pesan("Semua kolom harus diisi!");
                } else {
                    int curah = Integer.parseInt(curahx);
                    if ((curah >= 208.34) && (curah < 1000)) {
                        mSolusiii.setText("Hasil : Curah Ujan Terlampau Tinggi");
                        Intent mIntent = new Intent(getApplicationContext(), final_budidaya.class);
                        startActivity(mIntent);

                    }
                    else if ((curah >= 150 ) && (curah < 208.33)) {
                        mSolusiii.setText(" Curah Ujan sesuai dengan kisaran budidaya melon");
                        Intent mIntent = new Intent(getApplicationContext(), final_budidaya.class);
                        startActivity(mIntent);
                    }
                    else if ((curah >= 1) && (curah < 130)) {
                        mSolusiii.setText("Curah Ujan Terlampau Rendah");
                        Intent mIntent = new Intent(getApplicationContext(), final_budidaya.class);
                        startActivity(mIntent);
                    }
                    else
                    {
                        mSolusiii.setText("Format salah!");

                    }

                }
            }
        });

    }
    private void initData(){
        mcurah = findViewById(R.id.edcurah);
        mSolusiii = findViewById(R.id.txt_solusiii);
        mCik = findViewById(R.id.btn_cikk);

    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}