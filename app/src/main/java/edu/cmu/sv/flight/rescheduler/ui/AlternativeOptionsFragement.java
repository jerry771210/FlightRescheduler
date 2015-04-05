package edu.cmu.sv.flight.rescheduler.ui;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei-Lin Tsai on 4/3/15.
 */
public class AlternativeOptionsFragement extends Fragment implements OnClickListener {

    private final String[] mockOptions = {
            "1 LAX - NYC Arrived at 08:00PM 10/23",
            "2 LAX - NYC Arrived at 09:00PM 10/23",
            "3 LAX - NYC Arrived at 05:00AM 10/24",
            "4 LAX - NYC Arrived at 10:00AM 10/24",
            "5 LAX - NYC Arrived at 11:00AM 10/24",
            "6 LAX - NYC Arrived at 08:00PM 10/23",
            "7 LAX - NYC Arrived at 09:00PM 10/23",
            "8 LAX - NYC Arrived at 05:00AM 10/24",
            "9 LAX - NYC Arrived at 10:00AM 10/24",
            "10 LAX - NYC Arrived at 11:00AM 10/24",
            "11 LAX - NYC Arrived at 08:00PM 10/23",
            "12 LAX - NYC Arrived at 09:00PM 10/23",
            "13 LAX - NYC Arrived at 05:00AM 10/24",
            "14 LAX - NYC Arrived at 10:00AM 10/24",
            "15 LAX - NYC Arrived at 11:00AM 10/24"
    };

    public AlternativeOptionsFragement() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alternatives_options_basic_search, container, false);

        /* For the list view part */
        List<String> weekForecast = Arrays.asList(mockOptions);
        ArrayAdapter<String> weekForecastAdapter = new ArrayAdapter<String> (getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, weekForecast);
        ListView lv = (ListView) rootView.findViewById(R.id.listviewAlternativeRoute);
        lv.setAdapter(weekForecastAdapter);

        /* For the spinner part */
        Spinner spinnerOptions = (Spinner) rootView.findViewById(R.id.spinnerAlternativeRoutes);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, mockOptions);
        // Apply the adapter to the spinner
        spinnerOptions.setAdapter(spinnerAdapter);


        /* For the button part */
        Button buttonCancelBooking = (Button) rootView.findViewById(R.id.buttonMaybeLater);
        Button buttonRebookingConfirm = (Button) rootView.findViewById(R.id.buttonRebookingConfirm);
        Button buttonAdvancedSearch = (Button) rootView.findViewById(R.id.buttonAdvancedSearch);
        Button buttonOtherAirlines = (Button) rootView.findViewById(R.id.buttonOtherAirlines);

        // Take action when click the button
        buttonCancelBooking.setOnClickListener(this);
        buttonRebookingConfirm.setOnClickListener(this);
        buttonAdvancedSearch.setOnClickListener(this);
        buttonOtherAirlines.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRebookingConfirm:
               break;
            case R.id.buttonOtherAirlines:
                break;
            case R.id.buttonAdvancedSearch:
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.advanced_search);
                dialog.setTitle("Advanced search");
                AdvancedSearch advancedSearch = new AdvancedSearch(dialog);
                advancedSearch.init();
                dialog.show();
                break;
        }
    }
}
