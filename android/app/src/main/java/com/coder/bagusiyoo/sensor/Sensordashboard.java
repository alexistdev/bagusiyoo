package com.coder.bagusiyoo.sensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coder.bagusiyoo.R;
import com.coder.bagusiyoo.adapter.SensorAdapter;
import com.coder.bagusiyoo.api.APIService;
import com.coder.bagusiyoo.api.NoConnectivityException;
import com.coder.bagusiyoo.model.LabModel;
import com.coder.bagusiyoo.response.GetLab;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Sensordashboard extends AppCompatActivity {
    private RecyclerView gridSensor;
    private SensorAdapter sensorAdapter;
    private List<LabModel> daftarLab;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensordashboard);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Kebun");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);

        }
        initData();
        setupRecyclerView();
        setData();
    }

    private void setData() {
        try {
            Call<GetLab> call = APIService.Factory.create(getApplicationContext(),2).dataSensor("1");
            call.enqueue(new Callback<GetLab>() {
                @EverythingIsNonNull
                @Override

                public void onResponse(Call<GetLab> call, Response<GetLab> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful()){
                        if(response.body() != null){
                            daftarLab = response.body().getDaftarLab();
                            sensorAdapter.replaceData(daftarLab);
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<GetLab> call, @NonNull Throwable t) {
                    progressDialog.dismiss();
                    if(t instanceof NoConnectivityException) {
                        pesan("Internet Offline!");
                    }
                }
            });
        } catch (Exception e) {
            progressDialog.dismiss();
            e.printStackTrace();
            pesan(e.getMessage());
        }
    }

    private void initData(){
        progressDialog = ProgressDialog.show(this, "", "Loading.....", true, false);
        gridSensor = findViewById(R.id.rcSensor);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };

        sensorAdapter = new SensorAdapter(new ArrayList<>());
        gridSensor.setLayoutManager(linearLayoutManager);
        gridSensor.setAdapter(sensorAdapter);
    }


    public void pesan(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}