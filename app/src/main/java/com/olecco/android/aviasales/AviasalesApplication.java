package com.olecco.android.aviasales;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.olecco.android.aviasales.api.ApiClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AviasalesApplication extends Application {

    private ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

}
