package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coder.bagusiyoo.api.APIService;
import com.coder.bagusiyoo.model.DiaryModel;
import com.coder.bagusiyoo.model.TanamanModel;
import com.coder.bagusiyoo.response.GetTanaman;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Tambahdata extends AppCompatActivity {
    private Spinner mspinner;
    private Button mSimpan;
    private ProgressDialog pDialog;
    private EditText eJudul;
    private TextView mTanaman;

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
        populateSpinner();
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TanamanModel tanamanModel = (TanamanModel) parent.getItemAtPosition(position);
                mTanaman.setText(tanamanModel.getIdTanaman());
                mTanaman.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTanaman.setText("");
            }
        });

        mSimpan.setOnClickListener(v -> prosesSimpan());
    }

    private void populateSpinner() {
        try {
            Call<GetTanaman> call = APIService.Factory.create(getApplicationContext()).spinnerTanaman();
            call.enqueue(new Callback<GetTanaman>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<GetTanaman> call, Response<GetTanaman> response) {
                    if (response.body() != null) {
                        List<TanamanModel> tanamanModels = response.body().getItemTanaman();
                        ArrayAdapter<TanamanModel> statusAdapter = new ArrayAdapter<>(
                                getApplicationContext(), android.R.layout.simple_spinner_item, tanamanModels
                        );
                        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mspinner.setAdapter(statusAdapter);
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<GetTanaman> call, Throwable t) {
                    pesan(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            pesan(e.getMessage());
        }
    }

    private void prosesSimpan() {
        tampilLoading();
        String tanaman = mTanaman.getText().toString();
        String judul = eJudul.getText().toString();
        if (tanaman.length() == 0 || judul.length() == 0) {
            SembunyiLoading();
            pesan("Semua kolom harus diisi!");
        } else {
            try {
                Call<DiaryModel> call = APIService.Factory.create(getApplicationContext()).tambahData(tanaman, judul, "2");
                call.enqueue(new Callback<DiaryModel>() {
                    @EverythingIsNonNull
                    @Override
                    public void onResponse(Call<DiaryModel> call, Response<DiaryModel> response) {
                        if (response.isSuccessful()) {
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
            } catch (Exception e) {
                SembunyiLoading();
                e.printStackTrace();
                pesan(e.getMessage());
            }
        }
    }

    public void pesan(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void initData() {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading.....");
        mspinner = findViewById(R.id.spinnerTanaman);
        mSimpan = findViewById(R.id.btn_simpan);
        eJudul = findViewById(R.id.edJudul);
        mTanaman = findViewById(R.id.id_tanaman);
    }

    private void tampilLoading() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void SembunyiLoading() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
}