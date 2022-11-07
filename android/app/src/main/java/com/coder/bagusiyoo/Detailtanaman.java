package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.coder.bagusiyoo.adapter.AktivitasAdapter;
import com.coder.bagusiyoo.api.APIService;
import com.coder.bagusiyoo.api.NoConnectivityException;
import com.coder.bagusiyoo.model.AktivitasModel;
import com.coder.bagusiyoo.response.GetAktivitas;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Detailtanaman extends AppCompatActivity {
    private RecyclerView gridAktivitas;
    private AktivitasAdapter aktivitasAdapter;
    private List<AktivitasModel> daftarAktivitas;
    private ProgressDialog progressDialog;
    private TextView mHarike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtanaman);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Tanaman");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String idBook = "0";
        String idTanaman = "0";
        initData();
        setupRecyclerView();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                idBook= extras.getString("idDiary");
                idTanaman= extras.getString("idTanaman");
                setData(idBook,idTanaman);
            }
        }
    }

    private void setData(String idBook,String idTanaman){
        try{
            Call<GetAktivitas> call = APIService.Factory.create(getApplicationContext(),1).dapatAktivitas(idTanaman,idBook);
            call.enqueue(new Callback<GetAktivitas>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<GetAktivitas> call, Response<GetAktivitas> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful()){
                        if(response.body() != null){
                            mHarike.setText("Hari ke-"+response.body().getHarike());
                            daftarAktivitas = response.body().getListAktivitas();
                            aktivitasAdapter.replaceData(daftarAktivitas);
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<GetAktivitas> call, Throwable t) {
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

    public void pesan(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        aktivitasAdapter = new AktivitasAdapter(new ArrayList<>());
        gridAktivitas.setLayoutManager(linearLayoutManager);
        gridAktivitas.setAdapter(aktivitasAdapter);
    }

    private void initData(){
        progressDialog = ProgressDialog.show(this, "", "Loading.....", true, false);
        gridAktivitas = findViewById(R.id.rcAktivitas);
        mHarike = findViewById(R.id.harike);
    }
}