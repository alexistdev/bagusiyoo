package com.coder.bagusiyoo.sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coder.bagusiyoo.R;
import com.coder.bagusiyoo.api.APIService;
import com.coder.bagusiyoo.api.NoConnectivityException;
import com.coder.bagusiyoo.config.Constants;
import com.coder.bagusiyoo.model.SensorModel;
import com.coder.bagusiyoo.model.SiramModel;
import com.coder.bagusiyoo.utils.SessionUtils;
import com.google.android.material.textview.MaterialTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Detailsensor extends AppCompatActivity {
    private MaterialTextView mPhtanah,mKelembabanTanah,mSuhu,mKelembabanUdara;
    private TextView mUpdate,mNamaLab,mStatusPenyiraman,mStatusPemupukan;
    private ImageView mSiram,mPupuk,mPHTanah,mKlTanah,mGSuhu,mKlUdara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsensor);
        Toolbar toolbar = findViewById(R.id.tbtoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            setTitle("Detail Kebun");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        initData();
        Intent iin= getIntent();
        Bundle extra = iin.getExtras();
        if(extra != null) {
            final String idLab = extra.getString("idLab", "0");
            final String namaLab = extra.getString("namaLab", "Nama Lab");
            mNamaLab.setText(namaLab);
            setData(idLab);
            SharedPreferences sharedPreferences = this.getSharedPreferences(
                    Constants.KEY_USER_SESSION, Context.MODE_PRIVATE);
            mPupuk.setOnClickListener(view -> {
                String sPupuk = sharedPreferences.getString("Statuspupuk", "0");
                if(sPupuk.equals("1")){
                    pupuk(idLab,"0");
                } else {
                    pupuk(idLab,"1");
                }
            });
            mSiram.setOnClickListener(view -> penyiraman(idLab));
        }
    }

    /** penyiraman */
    private void penyiraman(String idLab){
        try{
            Call<SiramModel> call = APIService.Factory.create(getApplicationContext(),2).siram(idLab);
            call.enqueue(new Callback<SiramModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<SiramModel> call, Response<SiramModel> response) {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            String sSiramx = response.body().getPump();
                            if(sSiramx.equals("1")){
                                mStatusPenyiraman.setText(getString(R.string.mn_sensor26));
                                pesan("Penyiraman diaktifkan");
                            }else{

                                mStatusPenyiraman.setText(getString(R.string.mn_sensor27));
                                pesan("Penyiraman dimatikan");
                            }
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<SiramModel> call, Throwable t) {
                    if(t instanceof NoConnectivityException) {
                        pesan("Internet Offline!");
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
            pesan(e.getMessage());
        }
    }

    /** pemupukan */
    private void pupuk(String idLab,String nozzel){
        try{
            Call<SensorModel> call =APIService.Factory.create(getApplicationContext(),2).pupuk(idLab,nozzel);
            call.enqueue(new Callback<SensorModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<SensorModel> call, Response<SensorModel> response) {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            String StatusPemupukan = response.body().getStatusNozzel();
                            if(StatusPemupukan.equals("1")){
                                SessionUtils.dataSensor(Detailsensor.this,StatusPemupukan,null);
                                mStatusPemupukan.setText(R.string.mn_sensor26);
                                pesan("Pemupukan diaktifkan");
                            } else {
                                SessionUtils.dataSensor(Detailsensor.this,StatusPemupukan,null);
                                mStatusPemupukan.setText(R.string.mn_sensor27);
                                pesan("Pemupukan dimatikan");
                            }
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<SensorModel> call, Throwable t) {
                    if(t instanceof NoConnectivityException) {
                        pesan("Internet Offline!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            pesan(e.getMessage());
        }
    }

    private void setData(String idLab) {
        try {
            Call<SensorModel> call = APIService.Factory.create(getApplicationContext(),2).detailSensor(idLab);
            call.enqueue(new Callback<SensorModel>() {
                @EverythingIsNonNull
                @Override
                public void onResponse(Call<SensorModel> call, Response<SensorModel> response) {
                    if(response.isSuccessful()){
                        if(response.body() != null){
                            String phTanah = response.body().getPhTanah() ;
                            String Ktanah = response.body().getKelembabanTanah();
                            String KUdara = response.body().getKelembabanUdara();
                            String Suhu = response.body().getSuhuUdara();


                            mPhtanah.setText(String.format("%-10s",phTanah + getString(R.string.mn_sensor23)));
                            mKelembabanTanah.setText(String.format("%-10s",Ktanah + getString(R.string.mn_sensor24)));
                            mSuhu.setText(String.format("%-10s",Suhu + getString(R.string.mn_sensor25)));
                            mKelembabanUdara.setText(String.format("%-10s",KUdara + getString(R.string.mn_sensor24)));
                            mUpdate.setText(response.body().getUpdateDate());

                            String StatusPenyiraman = response.body().getStatusPump();
                            String StatusPemupukan = response.body().getStatusNozzel();
                            if(StatusPenyiraman.equals("1")){
                                mStatusPenyiraman.setText(getString(R.string.mn_sensor26));
                            } else {
                                mStatusPenyiraman.setText(R.string.mn_sensor27);
                            }
                            if(StatusPemupukan.equals("1")){
                                mStatusPemupukan.setText(R.string.mn_sensor26);
                            } else {
                                mStatusPemupukan.setText(R.string.mn_sensor27);
                            }

                            ubahStatus(phTanah,Ktanah,KUdara,Suhu);
                        }
                    }
                }
                @EverythingIsNonNull
                @Override
                public void onFailure(Call<SensorModel> call, Throwable t) {
                    if(t instanceof NoConnectivityException) {
                        pesan("Internet Offline!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            pesan(e.getMessage());
        }
    }

    private void ubahStatus(String phTanah, String Ktanah,String Kudara, String Suhu)
    {
        int ph_tanah = Integer.parseInt(phTanah);
        int kl_tanah = Integer.parseInt(Ktanah);
        int kl_udara = Integer.parseInt(Kudara);
        int temperature = Integer.parseInt(Suhu);
        if(ph_tanah == 7){
            mPHTanah.setImageResource(R.drawable.btnbaik);
        } else {
            mPHTanah.setImageResource(R.drawable.btnberlebih);
        }
        if(kl_tanah < 80 || kl_tanah > 90){
            mKlTanah.setImageResource(R.drawable.btnberlebih);
        } else {
            mKlTanah.setImageResource(R.drawable.btnbaik);
        }
        if(kl_udara < 80 || kl_udara > 90){
            mKlUdara.setImageResource(R.drawable.btnberlebih);
        } else {
            mKlUdara.setImageResource(R.drawable.btnbaik);
        }
        if(temperature < 30 || temperature > 37){
            mGSuhu.setImageResource(R.drawable.btnberlebih);
        } else {
            mGSuhu.setImageResource(R.drawable.btnbaik);
        }
    }

    private void initData(){
//        progressDialog = ProgressDialog.show(this, "", "Loading.....", true, false);
        mPhtanah = findViewById(R.id.count_ph);
        mKelembabanTanah = findViewById(R.id.count_ktanah);
        mSuhu = findViewById(R.id.count_suhu);
        mKelembabanUdara = findViewById(R.id.count_kudara);
        mUpdate = findViewById(R.id.txt_update);
        mSiram = findViewById(R.id.btnPenyiraman);
        mPupuk = findViewById(R.id.btnPemupukan);
        mNamaLab = findViewById(R.id.txt_judul);
        mPHTanah = findViewById(R.id.gbr_ph);
        mKlTanah = findViewById(R.id.gbr_kltanah);
        mGSuhu = findViewById(R.id.gbr_suhu);
        mKlUdara = findViewById(R.id.gbr_kludara);
        mStatusPenyiraman = findViewById(R.id.status_penyiraman);
        mStatusPemupukan = findViewById(R.id.status_pemupukan);
    }

    public void pesan(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}