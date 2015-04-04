package com.example.flightrescheduler.flightrescheduler;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei-Lin Tsai on 4/3/15.
 */
public class AlternativeOptionsFragement extends Fragment {

    public AlternativeOptionsFragement() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.alternatives_options, container, false);
        List<String> weekForecast = new ArrayList<>();
        weekForecast.add("LAX - NYC Arrived at 08:00PM 10/23");
        weekForecast.add("LAX - NYC Arrived at 09:00PM 10/23");
        weekForecast.add("LAX - NYC Arrived at 05:00AM 10/24");
        weekForecast.add("LAX - NYC Arrived at 10:00AM 10/24");
        weekForecast.add("LAX - NYC Arrived at 11:00AM 10/24");

        ArrayAdapter<String> weekForecastAdapter = new ArrayAdapter<String> (getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, weekForecast);

        ListView lv = (ListView) rootView.findViewById(R.id.listviewAlternativeRoute);
        lv.setAdapter(weekForecastAdapter);

        return rootView;
    }
}
