package com.olecco.android.aviasales.api;

import com.olecco.android.aviasales.model.ApiResponse;
import com.olecco.android.aviasales.model.City;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class ApiResponseConverter {

    private ApiResponseConverter() {}

    @Nullable
    public static City getCityFromResponse(@NonNull ApiResponse apiResponse) {
        List<City> cities = apiResponse.getCities();
        if (!cities.isEmpty()) {
            return cities.get(0);
        }
        return null;
    }

}
