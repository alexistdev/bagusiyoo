package com.coder.bagusiyoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.coder.bagusiyoo.adapter.DiaryAdapter;
import com.coder.bagusiyoo.api.APIService;
import com.coder.bagusiyoo.api.NoConnectivityException;
import com.coder.bagusiyoo.model.DiaryModel;
import com.coder.bagusiyoo.response.GetDiary;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {
    private RecyclerView gridDiary;
    private DiaryAdapter diaryAdapter;
    private List<DiaryModel> daftarDiary;
    private ProgressDialog progressDialog;
    private ImageView mTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Diary Tanaman");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        initData();
        setupRecyclerView();
        setData();
        mTambah.setOnClickListener(v -> {
            Intent mIntent = new Intent(getApplicationContext(), Tambahdata.class);
            startActivity(mIntent);
        });
    }

    private void setData(){
        try{
            Call<GetDiary> call = APIService.Factory.create(getApplicationContext()).dapatDiary("2");
            call.enqueue(new Callback<GetDiary>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<GetDiary> call, Response<GetDiary> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful()){
                        if(response.body() != null){
                            daftarDiary = response.body().getListDiary();
                            diaryAdapter.replaceData(daftarDiary);
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<GetDiary> call, Throwable t) {
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
        mTambah = findViewById(R.id.btnTambah);
        progressDialog = ProgressDialog.show(this, "", "Loading.....", true, false);
        gridDiary = findViewById(R.id.rcDiary);
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

        diaryAdapter = new DiaryAdapter(new ArrayList<>());
        gridDiary.setLayoutManager(linearLayoutManager);
        gridDiary.setAdapter(diaryAdapter);
    }
}