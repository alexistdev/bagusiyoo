package com.coder.bagusiyoo.api;

import android.content.Context;

import com.coder.bagusiyoo.BuildConfig;
import com.coder.bagusiyoo.config.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface APIService {
//    @GET("api/Paket/harga")
//    Call<GetHarga> dapatkanHarga(@Query("id_paket") String id_paket);

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
