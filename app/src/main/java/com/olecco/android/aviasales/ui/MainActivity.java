package com.olecco.android.aviasales.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.olecco.android.aviasales.R;
import com.olecco.android.aviasales.model.CityPair;
import com.olecco.android.aviasales.ui.map.ShowMapFragment;

public class MainActivity extends Activity implements UIRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment searchFragment = getFragmentManager().findFragmentByTag(SearchFragment.TAG);
        if (searchFragment == null) {
            searchFragment = new SearchFragment();
            showFragment(searchFragment, false, SearchFragment.TAG);
        }

    }

    private void showFragment(Fragment fragment, boolean toBackStack, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, tag);
        if (toBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    public void showMap(CityPair cityPair) {
        showFragment(ShowMapFragment.newInstance(cityPair), true, ShowMapFragment.TAG);
    }

}
