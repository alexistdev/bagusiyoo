package com.coder.bagusiyoo.api;

import android.content.Context;

import com.coder.bagusiyoo.config.Constants;
import com.coder.bagusiyoo.model.DiaryModel;
import com.coder.bagusiyoo.model.SensorModel;
import com.coder.bagusiyoo.model.SiramModel;
import com.coder.bagusiyoo.response.GetAktivitas;
import com.coder.bagusiyoo.response.GetDiary;
import com.coder.bagusiyoo.response.GetLab;
import com.coder.bagusiyoo.response.GetTanaman;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/tanaman")
    Call<GetTanaman> spinnerTanaman();

    @GET("api/diary")
    Call<GetDiary> dapatDiary(@Query("idUser") String id_user);

    @GET("api/aktivitas")
    Call<GetAktivitas> dapatAktivitas(@Query("idTanaman") String id_tanaman,
                                      @Query("idBook") String id_book);

    @FormUrlEncoded
    @POST("api/diary")
    Call<DiaryModel> tambahData(
                                @Field("idTanaman") String id_tanaman,
                                @Field("judul") String judul,
                                @Field("id_user") String id_user);

    @DELETE("api/diary")
    Call<DiaryModel> hapusData(@Query("idBook") String id_Book);


    /** TANI CERDAS SENSOR */
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PATCH("api/sensor/penyiraman")
    Call<SiramModel> siram(@Field("id_lab") String idLab);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PATCH("api/sensor/pemupukan")
    Call<SensorModel> pupuk(@Field("id_lab") String idLab,
                            @Field("nozzel") String nozzel);

    @Headers("Accept: application/json")
    @GET("api/data")
    Call<SensorModel> detailSensor(@Query("id_lab") String idLab);

    @Headers("Accept: application/json")
    @GET("api/lab")
    Call<GetLab> dataSensor(@Query("id_user") String idUser);



    class Factory{
        public static APIService create(Context mContext,int tipe){

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.addInterceptor(new NetworkConnectionInterceptor(mContext));
            OkHttpClient client = builder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl((tipe == 1)?Constants.URL:Constants.URL2)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(APIService.class);
        }
    }
}
