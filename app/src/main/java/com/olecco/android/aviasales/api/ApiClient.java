package com.olecco.android.aviasales.api;

import com.olecco.android.aviasales.model.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    String BASE_URL = "https://yasen.hotellook.com";

    @GET("/autocomplete")
    Observable<ApiResponse> loadCityData(@Query("term") String term, @Query("lang") String lang);

}
