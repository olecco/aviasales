package com.olecco.android.aviasales.ui.map;

import android.os.Bundle;

import com.olecco.android.aviasales.model.City;
import com.olecco.android.aviasales.model.CityPair;
import com.olecco.android.aviasales.model.Coordinates;

public class CitiesMarshaller {

    private static final String LAT_FROM_EXTRA = "LAT_FROM_EXTRA";
    private static final String LON_FROM_EXTRA = "LON_FROM_EXTRA";
    private static final String LAT_TO_EXTRA = "LAT_TO_EXTRA";
    private static final String LON_TO_EXTRA = "LON_TO_EXTRA";

    private CitiesMarshaller() {}

    public static Bundle toBundle(CityPair cityPair) {
        Bundle bundle = new Bundle();

        City cityFrom = cityPair.getCityFrom();
        City cityTo = cityPair.getCityTo();

        bundle.putDouble(LAT_FROM_EXTRA, cityFrom.getCoordinates().getLat());
        bundle.putDouble(LON_FROM_EXTRA, cityFrom.getCoordinates().getLon());

        bundle.putDouble(LAT_TO_EXTRA, cityTo.getCoordinates().getLat());
        bundle.putDouble(LON_TO_EXTRA, cityTo.getCoordinates().getLon());

        return bundle;
    }

    public static Coordinates getFromCoordinates(Bundle bundle) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(bundle.getDouble(LAT_FROM_EXTRA, 0.0));
        coordinates.setLon(bundle.getDouble(LON_FROM_EXTRA, 0.0));
        return coordinates;
    }

    public static Coordinates getToCoordinates(Bundle bundle) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(bundle.getDouble(LAT_TO_EXTRA, 0.0));
        coordinates.setLon(bundle.getDouble(LON_TO_EXTRA, 0.0));
        return coordinates;
    }

}
