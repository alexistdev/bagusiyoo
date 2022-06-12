package com.coder.bagusiyoo.api;

import android.content.Context;

import com.coder.bagusiyoo.BuildConfig;
import com.coder.bagusiyoo.config.Constants;
import com.coder.bagusiyoo.model.DiaryModel;
import com.coder.bagusiyoo.response.GetAktivitas;
import com.coder.bagusiyoo.response.GetDiary;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @GET("api/diary")
    Call<GetDiary> dapatDiary(@Query("idUser") String id_user);

    @GET("api/aktivitas")
    Call<GetAktivitas> dapatAktivitas(@Query("idTanaman") String id_tanaman);

    @FormUrlEncoded
    @POST("api/diary")
    Call<DiaryModel> tambahData(
                                @Field("idTanaman") String id_tanaman,
                                @Field("judul") String judul,
                                @Field("id_user") String id_user);


    class Factory{
        public static APIService create(Context mContext){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.addInterceptor(new NetworkConnectionInterceptor(mContext));
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            if(BuildConfig.DEBUG){
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            }else {
//                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
//            }

            OkHttpClient client = builder.build();
//            OkHttpClient client = builder.addInterceptor(logging).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}
