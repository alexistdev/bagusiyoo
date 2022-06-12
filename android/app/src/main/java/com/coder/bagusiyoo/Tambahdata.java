package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.coder.bagusiyoo.api.APIService;
import com.coder.bagusiyoo.model.DiaryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Tambahdata extends AppCompatActivity {
    private Spinner mspinner;
    private Button mSimpan;
    private ProgressDialog pDialog;
    private EditText eJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahdata);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Tambah Data");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initData();
        String[] tanamans = {"Melon"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tanamans);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner.setAdapter(spinnerArrayAdapter);
        mSimpan.setOnClickListener(v -> prosesSimpan());
    }
    private void prosesSimpan(){
        tampilLoading();
        String tanaman = mspinner.getSelectedItem().toString();
        String judul = eJudul.getText().toString();
        if(tanaman.length() == 0 || judul.length() == 0 ){
            SembunyiLoading();
            pesan("Semua kolom harus diisi!");
        } else {
            try{
                Call<DiaryModel> call = APIService.Factory.create(getApplicationContext()).tambahData("7",judul,"2");
                call.enqueue(new Callback<DiaryModel>() {
                    @EverythingIsNonNull
                    @Override
                    public void onResponse(Call<DiaryModel> call, Response<DiaryModel> response) {
                        if(response.isSuccessful()) {
                            SembunyiLoading();
                            Intent intent = new Intent(Tambahdata.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            pesan("Data Tanaman Berhasil ditambahkan!");
                        }
                    }
                    @EverythingIsNonNull
                    @Override
                    public void onFailure(Call<DiaryModel> call, Throwable t) {
                        SembunyiLoading();
                        pesan(t.getMessage());
                    }
                });
            }catch (Exception e){
                SembunyiLoading();
                e.printStackTrace();
                pesan(e.getMessage());
            }
        }
    }
    public void pesan(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void initData(){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading.....");
        mspinner = findViewById(R.id.spinnerTanaman);
        mSimpan = findViewById(R.id.btn_simpan);
        eJudul = findViewById(R.id.edJudul);
    }

    private void tampilLoading(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void SembunyiLoading(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}