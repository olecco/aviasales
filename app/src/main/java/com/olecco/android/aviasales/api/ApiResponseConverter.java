package com.olecco.android.aviasales.api;

import com.olecco.android.aviasales.model.ApiResponse;
import com.olecco.android.aviasales.model.City;
import com.olecco.android.aviasales.model.CityPair;

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

    @Nullable
    public static CityPair getCityPair(@NonNull ApiResponse apiResponse1, @NonNull ApiResponse apiResponse2) {
        City cityFrom1 = ApiResponseConverter.getCityFromResponse(apiResponse1);
        City cityTo1 = ApiResponseConverter.getCityFromResponse(apiResponse2);
        if (cityFrom1 != null && cityTo1 != null) {
            return new CityPair(cityFrom1, cityTo1);
        }
        return null;
    }

}
