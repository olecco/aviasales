package com.olecco.android.aviasales.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.olecco.android.aviasales.AviasalesApplication;
import com.olecco.android.aviasales.R;
import com.olecco.android.aviasales.api.ApiClient;
import com.olecco.android.aviasales.api.ApiResponseConverter;
import com.olecco.android.aviasales.model.ApiResponse;
import com.olecco.android.aviasales.model.City;
import com.olecco.android.aviasales.model.CityPair;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchFragment extends Fragment {

    public static final String TAG = "SearchFragment";

    private EditText cityFromEdit;
    private EditText cityToEdit;
    private Button searchButton;
    private Disposable requestDisposable;
    private UIRouter uiRouter;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiRouter = (UIRouter) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        uiRouter = (UIRouter) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        uiRouter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        cityFromEdit = (EditText) view.findViewById(R.id.cityFrom);
        cityToEdit = (EditText) view.findViewById(R.id.cityTo);

        searchButton = (Button) view.findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityFrom = cityFromEdit.getText().toString();
                String cityTo = cityToEdit.getText().toString();
                startSearch(cityFrom, cityTo);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cityFromEdit = null;
        cityToEdit = null;
        searchButton = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (requestDisposable != null) {
            requestDisposable.dispose();
            requestDisposable = null;
        }
    }

    private void startSearch(String cityFrom, String cityTo) {

        Observable<ApiResponse> cityFromData = getApiClient().loadCityData(cityFrom, "ru");
        Observable<ApiResponse> cityToData = getApiClient().loadCityData(cityTo, "ru");


        requestDisposable = Observable.zip(cityFromData, cityToData, new BiFunction<ApiResponse, ApiResponse, CityPair>() {

            @Override
            public CityPair apply(@NonNull ApiResponse apiResponse1, @NonNull ApiResponse apiResponse2) throws Exception {


                City cityFrom = ApiResponseConverter.getCityFromResponse(apiResponse1);
                City cityTo = ApiResponseConverter.getCityFromResponse(apiResponse2);

                if (cityFrom != null && cityTo != null) {
                    return new CityPair(cityFrom, cityTo);
                }


                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityPair>() {

                    @Override
                    public void accept(@NonNull CityPair cityPair) throws Exception {

                        City city = cityPair.getCityFrom();
                        Log.d("111", "cityFrom: " + city.getFullName() + " : " + city.getCoordinates().getLat() + " : " +
                                city.getCoordinates().getLon());

                        city = cityPair.getCityTo();
                        Log.d("111", "cityTo: " + city.getFullName() + " : " + city.getCoordinates().getLat() + " : " +
                                city.getCoordinates().getLon());

                        uiRouter.showMap(cityPair);

                    }
                });


    }

    private ApiClient getApiClient() {
        return getAviasalesApplication().getApiClient();
    }

    private AviasalesApplication getAviasalesApplication() {
        return (AviasalesApplication) getActivity().getApplication();
    }

}
