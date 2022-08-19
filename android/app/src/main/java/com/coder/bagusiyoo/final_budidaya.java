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
    private TextView msolusiiii;
    private Button mcukk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_budidaya);
        Toolbar toolbar = findViewById(R.id.yaa);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pengujian Algoritma Genetika");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        mcukk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budidayax = mbudidaya.getText().toString();
                if (budidayax.length() == 0) {
                    pesan("Semua kolom harus diisi!");
                } else {
                    int Budidaya = Integer.parseInt(budidayax);
                     if ((Budidaya == 1)) {
                         msolusiiii.setText(" Budidaya berhasil maka perkiraan panen maret");
                        Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                        startActivity(mIntent);
                    }
                    else if ((Budidaya == 2)) {
                         msolusiiii.setText("Budidaya tidak berhasil dikarenakan bulan maret dan april cuaca tidak mendukung");
                         Intent mIntent = new Intent(getApplicationContext(), kembali.class);
                         startActivity(mIntent);
                    }
                    else if ((Budidaya==3)) {
                         msolusiiii.setText("Budidaya tidak berhasil dikarenakan bulan april dan mei cuaca tidak mendukung");
                         Intent mIntent = new Intent(getApplicationContext(), kembali.class);
                         startActivity(mIntent);


                     }
                    else if ((Budidaya==4)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen juni");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==5)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen juli");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==6)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen agustus");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==7)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen september");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==8)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen oktober");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==9)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen November");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==10)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen Desember");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==11)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen Januari");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else if ((Budidaya==12)) {
                         msolusiiii.setText("Budidaya berhasil maka perkiraan panen Febuari");
                         Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
                         startActivity(mIntent);

                    }
                    else
                    {
                        msolusiiii.setText("format salah!");

                    }

                }
            }
        });

    }
    private void initData(){
        mbudidaya = findViewById(R.id.edbudidaya);
        msolusiiii = findViewById(R.id.txt_budidaya);
        mcukk = findViewById(R.id.btn_cukk);

    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}