package com.olecco.android.aviasales.ui.map;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olecco.android.aviasales.R;
import com.olecco.android.aviasales.model.CityPair;
import com.olecco.android.aviasales.model.Coordinates;

public class MapFragment extends Fragment {

    public static final String TAG = "MapFragment";

    private Coordinates coordinatesFrom;
    private Coordinates coordinatesTo;

    public static MapFragment newInstance(CityPair cityPair) {
        MapFragment fragment = new MapFragment();
        fragment.setArguments(CitiesMarshaller.toBundle(cityPair));
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coordinatesFrom = CitiesMarshaller.getFromCoordinates(getArguments());
        coordinatesTo = CitiesMarshaller.getToCoordinates(getArguments());


        Log.d("111", coordinatesFrom + "  -  " + coordinatesTo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);




        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
