package edu.cmu.sv.flight.rescheduler.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.ProxyRescheduler;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.Rescheduler;
import edu.cmu.sv.flight.rescheduler.ui.activity.BoardingPassActivity;
import edu.cmu.sv.flight.rescheduler.ui.listener.AdvancedSearch;
import edu.cmu.sv.flight.rescheduler.ui.listener.IntentToActivityOnClickListener;
import edu.cmu.sv.flight.rescheduler.ui.listener.OtherAirlinesOnClickListener;
import edu.cmu.sv.flight.rescheduler.ui.listener.ShowRouteDetailOnItemClickListener;


/**
 * Created by Wei-Lin Tsai on 4/3/15.
 */
public class AlternativeOptionsFragment extends Fragment {
    static final String LOG_TAG = AlternativeOptionsFragment.class.getSimpleName();
    private Rescheduler rescheduler;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alternatives_options_basic_search, container, false);

        /* For the list view part */
        List<String> alternativesList = getAlternativeList();
        ArrayAdapter<String> alternativesAdapter =
                new ArrayAdapter<String> (getActivity(), R.layout.list_item_available_route,
                        R.id.textViewListItemAvailableRoute, alternativesList);
        ListView lv = (ListView) view.findViewById(R.id.listViewAlternativeRoute);
        lv.setAdapter(alternativesAdapter);


        /* For the button part */
        Button buttonOtherAirlines = (Button) view.findViewById(R.id.buttonOtherAirlines);
        Button buttonCancelBooking = (Button) view.findViewById(R.id.buttonMaybeLater);
        Button buttonAdvancedSearch = (Button) view.findViewById(R.id.buttonAdvancedSearch);

        // TODO rename button for buttonOtherAirlines
        // TODO: Should use FinishOnClick here
        // but ConfirmPage->AlternativeOptions->FinishOnClick is not working now
        buttonCancelBooking.setOnClickListener(
                new IntentToActivityOnClickListener(getActivity(), BoardingPassActivity.class, true));
        buttonAdvancedSearch.setOnClickListener(new AdvancedSearch(getActivity()));
        buttonOtherAirlines.setOnClickListener(new OtherAirlinesOnClickListener(getActivity()));
        lv.setOnItemClickListener(new ShowRouteDetailOnItemClickListener(getActivity()));

        return view;
    }

    private List<String> getAlternativeList() {
        int index;
        /* Get index from previous activity */
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            index = extras.getInt("indexOfBoardingPass");
        } else {
            Log.d (LOG_TAG, "Can not receive index of boarding pass");
            return new ArrayList<String>();
        }

        // Get depart and arrive info
        BoardingPass departBP = CurrentRoute.getInstance().getBoardingPass(index);
        String departAirport = departBP.getDeparture();
        String arriveAirport = CurrentRoute.getInstance().getLastBoardingpass().getArrival();

        Date curDate = departBP.getDepartureTime(); // TODO, may use current date to provide better
                                                    // result
        /* create Rescheduler*/
        rescheduler = new ProxyRescheduler();
        rescheduler.findAvailableRoutes(departAirport, arriveAirport, false, curDate, getActivity());

        return Arrays.asList(mockOptions);
    }
}
