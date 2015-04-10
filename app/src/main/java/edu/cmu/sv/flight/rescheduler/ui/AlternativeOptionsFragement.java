package edu.cmu.sv.flight.rescheduler.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.ui.activity.PagerConfirmActivity;
import edu.cmu.sv.flight.rescheduler.ui.listener.FinishOnClickListener;

/**
 * Created by Wei-Lin Tsai on 4/3/15.
 */
public class AlternativeOptionsFragement extends Fragment implements OnClickListener, OnItemClickListener {
    static final String LOG_TAG = AlternativeOptionsFragement.class.getSimpleName();
    private AdvancedSearch advancedSearch;

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


    private final String[] mockAdvancedSearch = {
            " 1 LAX - ACY Arrived at 08:00PM 10/23",
            " 2 LAX - EWR Arrived at 09:00PM 10/23",
            " 3 LAX - ACY Arrived at 05:00AM 10/24",
            " 4 LAX - TTN Arrived at 10:00AM 10/24",
            " 5 LAX - NYC Arrived at 11:00AM 10/24",
            " 6 LAX - TEB Arrived at 08:00PM 10/23",
            " 7 LAX - TEB Arrived at 09:00PM 10/23",
            " 8 LAX - ACY Arrived at 05:00AM 10/24",
            " 9 LAX - TTN Arrived at 10:00AM 10/24",
            "10 LAX - EWR Arrived at 11:00AM 10/24",
            "11 LAX - ACY Arrived at 08:00PM 10/23",
            "12 LAX - TTN Arrived at 09:00PM 10/23",
            "13 LAX - ACY Arrived at 05:00AM 10/24",
            "14 LAX - TTN Arrived at 10:00AM 10/24",
            "15 LAX - TTN Arrived at 11:00AM 10/24"
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
        Button buttonOtherAirlines = (Button) rootView.findViewById(R.id.buttonOtherAirlines);
        Button buttonCancelBooking = (Button) rootView.findViewById(R.id.buttonMaybeLater);
        Button buttonRebookingConfirm = (Button) rootView.findViewById(R.id.buttonRebookingConfirm);
        Button buttonAdvancedSearch = (Button) rootView.findViewById(R.id.buttonAdvancedSearch);

        // TODO rename button for buttonOtherAirlines
        buttonCancelBooking.setOnClickListener(new FinishOnClickListener(getActivity()));
        buttonRebookingConfirm.setOnClickListener(this);
        buttonAdvancedSearch.setOnClickListener(this);
        buttonOtherAirlines.setOnClickListener(this);
        lv.setOnItemClickListener(this);

        return rootView;
    }

    private void updateSwitchAirlinesListView(View v) {
        // get the button current value
        Button buttonOtherAirlines = (Button) v.findViewById(R.id.buttonOtherAirlines);
        String buttonVal = buttonOtherAirlines.getText().toString();
        List<String> availableList;
        if ("Other Airlines".equals(buttonVal)) {
            updateListviewAlternativeRoute(mockOtherAirlines);
        } else {
            updateListviewAlternativeRoute(mockOptions);
        }
    }

    private void updateSwitchAirLinesSpinner(View v) {
        Button buttonOtherAirlines = (Button) v.findViewById(R.id.buttonOtherAirlines);
        String buttonVal = buttonOtherAirlines.getText().toString();

        ArrayAdapter<String> spinnerAdapter;
        if ("Other Airlines".equals(buttonVal)) {
            updateSpinnerAlternativeRoute(mockOtherAirlines);
        } else {
            updateSpinnerAlternativeRoute(mockOptions);
        }

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


    private void onClickButtonOtherAirlines() {
        View rootView = getView();
        Log.d(LOG_TAG, "Click other airlines");
        updateSwitchAirLinesSpinner(rootView);
        updateSwitchAirlinesListView(rootView);
        updateSwitchAirLinesButtonVal(rootView);
    }

    private void updateListviewAlternativeRoute (String[] strArr) {
        View v = getView();
        List<String> availableList = Arrays.asList(strArr);

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, availableList);
        ListView lv = (ListView) v.findViewById(R.id.listviewAlternativeRoute);
        lv.setAdapter(adapter);
    }

    private void updateSpinnerAlternativeRoute(String[] strArr) {
        View v = getView();
        Spinner spinnerOptions = (Spinner) v.findViewById(R.id.spinnerAlternativeRoutes);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_available_route, R.id.list_item_available_route_textview, strArr);

        // Apply the adapter to the spinner
        spinnerOptions.setAdapter(spinnerAdapter);
    }

    private void updateAlternativeOptionMode(String str) {
        View v = getView();
        TextView tv = (TextView) v.findViewById(R.id.textAlternativeOptionsMode);
        tv.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.buttonMaybeLater:
//                getActivity().finish();
//                break;
            case R.id.buttonRebookingConfirm:
                startActivity(new Intent(getActivity(),PagerConfirmActivity.class));
                break;
            case R.id.buttonOtherAirlines:
                onClickButtonOtherAirlines();
                break;
            case R.id.buttonAdvancedSearch:
                advancedSearch = new AdvancedSearch(getActivity());
                advancedSearch.init();
                advancedSearch.showDialog();
                updateSpinnerAlternativeRoute(mockAdvancedSearch);
                updateListviewAlternativeRoute(mockAdvancedSearch);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = ((TextView)view).getText().toString();
        ShowFlightDetails showDetails = new ShowFlightDetails(item, getActivity());
        showDetails.display();

    }
}
