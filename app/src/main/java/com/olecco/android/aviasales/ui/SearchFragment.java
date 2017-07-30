package com.olecco.android.aviasales.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.olecco.android.aviasales.AviasalesApplication;
import com.olecco.android.aviasales.R;
import com.olecco.android.aviasales.api.ApiClient;
import com.olecco.android.aviasales.api.ApiResponseConverter;
import com.olecco.android.aviasales.model.ApiResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchFragment extends Fragment {

    public static final String TAG = "SearchFragment";

    private EditText cityFromEdit;
    private EditText cityToEdit;
    private Button searchButton;
    private Disposable requestDisposable;
    private UIRouter uiRouter;

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
        searchButton.setOnClickListener(v -> {
            if (validateInput()) {
                startSearch(getCityFrom(), getCityTo());
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

        requestDisposable = Observable
                .zip(cityFromData, cityToData, ApiResponseConverter::getCityPair)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityPair -> uiRouter.showMap(cityPair),
                           throwable -> showError(throwable.getMessage()));
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(getCityFrom())) {
            showError(getString(R.string.input_from));
            cityFromEdit.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(getCityTo())) {
            showError(getString(R.string.input_to));
            cityToEdit.requestFocus();
            return false;
        }

        return true;
    }

    private void showError(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    private String getCityFrom() {
        return cityFromEdit.getText().toString().trim();
    }

    private String getCityTo() {
        return cityToEdit.getText().toString().trim();
    }

    private ApiClient getApiClient() {
        return getAviasalesApplication().getApiClient();
    }

    private AviasalesApplication getAviasalesApplication() {
        return (AviasalesApplication) getActivity().getApplication();
    }

}
