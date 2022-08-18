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

public class final_budidaya extends AppCompatActivity {

    private EditText mbudidaya;
    private TextView mSolusiiii;
    private Button mCuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_budidaya);
        Toolbar toolbar = findViewById(R.id.yaa);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cek Waktu Tanam");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        mCuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budidayax = mbudidaya.getText().toString();
                if (budidayax.length() == 0) {
                    pesan("Semua kolom harus diisi!");
                } else {
                    int Budidaya = Integer.parseInt(budidayax);
                     if ((Budidaya==1)) {
                        mbudidaya.setText(" Budidaya berhasil maka perkiraan panen maret");
                        Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                        startActivity(mIntent);
                    }
                    else if ((Budidaya==2)) {
                        mbudidaya.setText("Budidaya tidak berhasil dikarenakan bulan maret dan april cuaca tidak mendukung");

                    }
                    else if ((Budidaya==3)) {
                        mbudidaya.setText("Budidaya tidak berhasil dikarenakan bulan april dan mei cuaca tidak mendukung");

                    }
                    else if ((Budidaya==4)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen juni");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==5)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen juli");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==6)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen agustus");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==7)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen september");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==8)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen oktober");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==9)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen November");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==10)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen Desember");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==11)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen Januari");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==12)) {
                        mbudidaya.setText("Budidaya berhasil maka perkiraan panen Febuari");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else
                    {
                        mbudidaya.setText("format salah!");

                    }

                }
            }
        });

    }
    private void initData(){
        mbudidaya = findViewById(R.id.edbudidaya);
        mSolusiiii = findViewById(R.id.txt_solusii);
        mCuk = findViewById(R.id.btn_cukk);

    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}