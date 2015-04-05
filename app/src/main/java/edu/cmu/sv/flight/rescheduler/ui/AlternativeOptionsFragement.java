package edu.cmu.sv.flight.rescheduler.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei-Lin Tsai on 4/3/15.
 */
public class AlternativeOptionsFragement extends Fragment {
    static final String LOG_TAG = AlternativeOptionsFragement.class.getSimpleName();

    private final String[] mockOptions = {
            " 1 LAX - NYC Arrived at 08:00PM 10/23",
            " 2 LAX - NYC Arrived at 09:00PM 10/23",
            " 3 LAX - NYC Arrived at 05:00AM 10/24",
            " 4 LAX - NYC Arrived at 10:00AM 10/24",
            " 5 LAX - NYC Arrived at 11:00AM 10/24",
            " 6 LAX - NYC Arrived at 08:00PM 10/23",
            " 7 LAX - NYC Arrived at 09:00PM 10/23",
            " 8 LAX - NYC Arrived at 05:00AM 10/24",
            " 9 LAX - NYC Arrived at 10:00AM 10/24",
            "10 LAX - NYC Arrived at 11:00AM 10/24",
            "11 LAX - NYC Arrived at 08:00PM 10/23",
            "12 LAX - NYC Arrived at 09:00PM 10/23",
            "13 LAX - NYC Arrived at 05:00AM 10/24",
            "14 LAX - NYC Arrived at 10:00AM 10/24",
            "15 LAX - NYC Arrived at 11:00AM 10/24"
    };

    private final String[] mockOtherAirlines = {
            " 1 EVA LAX - NYC Arrived at 08:00PM 10/23",
            " 2 EVA LAX - NYC Arrived at 09:00PM 10/23",
            " 3 ANA LAX - NYC Arrived at 05:00AM 10/24",
            " 4 ANA LAX - NYC Arrived at 10:00AM 10/24",
            " 5 CI  LAX - NYC Arrived at 11:00AM 10/24",
            " 6 CI  LAX - NYC Arrived at 08:00PM 10/23",
            " 7 AA  LAX - NYC Arrived at 09:00PM 10/23",
            " 8 AA  LAX - NYC Arrived at 05:00AM 10/24",
            " 9 EVA LAX - NYC Arrived at 10:00AM 10/24",
            "10 EVA LAX - NYC Arrived at 11:00AM 10/24",
            "11 CI  LAX - NYC Arrived at 08:00PM 10/23",
            "12 EVA LAX - NYC Arrived at 09:00PM 10/23",
            "13 ANA LAX - NYC Arrived at 05:00AM 10/24",
            "14 EVA LAX - NYC Arrived at 10:00AM 10/24",
            "15 EVA LAX - NYC Arrived at 11:00AM 10/24"
    };

    public AlternativeOptionsFragement() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_alternatives_options_basic_search, container, false);

        /* For the list view part */
        List<String> alternativesList = Arrays.asList(mockOptions);
        ArrayAdapter<String> alternativesAdapter = new ArrayAdapter<String> (getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, alternativesList);
        ListView lv = (ListView) rootView.findViewById(R.id.listviewAlternativeRoute);
        lv.setAdapter(alternativesAdapter);

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
        buttonCancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        buttonRebookingConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PagerConfirmActivity.class));
            }
        });

        buttonOtherAirlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Click other airlines");
                /* Update view part */
                List<String> otherAirlinesList= Arrays.asList(mockOtherAirlines);
                ArrayAdapter<String> otherLinesAdapter = new ArrayAdapter<String> (getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, otherAirlinesList);
                ListView lv = (ListView) rootView.findViewById(R.id.listviewAlternativeRoute);
                lv.setAdapter(otherLinesAdapter);
                updateSwitchAirLinesButtonVal(v);
            }
        });

        return rootView;
    }

    private void updateSwitchAirlinesListView(View v) {
        // get the button current value
        Button buttonOtherAirlines = (Button) v.findViewById(R.id.buttonOtherAirlines);
        String buttonVal = buttonOtherAirlines.getText().toString();
        if ("Other Airlines".equals(buttonVal)) {
            // change to other airlines
        } else {
            // change to
        }

        return;
    }

    private void updateSwitchAirLinesSpinner(View v) {
        return;
    }

    private void updateSwitchAirLinesButtonVal(View v) {
        // get the button current value
        Button buttonOtherAirlines = (Button) v.findViewById(R.id.buttonOtherAirlines);
        String buttonVal = buttonOtherAirlines.getText().toString();
        if ("Other Airlines".equals(buttonVal)) {
            buttonOtherAirlines.setText("Original Airlines");
        } else {
            buttonOtherAirlines.setText("Other Airlines");
        }
        return;
    }


}
