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

        // but ConfirmPage->AlternativeOptions->FinishOnClick is not working now
        buttonCancelBooking.setOnClickListener(
                new IntentToActivityOnClickListener(getActivity(), BoardingPassActivity.class, true));
        buttonAdvancedSearch.setOnClickListener(new AdvancedSearch(getActivity()));
        buttonOtherAirlines.setOnClickListener(new OtherAirlinesOnClickListener(getActivity()));
        lv.setOnItemClickListener(new ShowRouteDetailOnItemClickListener(getActivity()));

        return view;
    }

    private List<String> getAlternativeList() {
        // Get depart and arrive info
        int index = CurrentRoute.getInstance().getStartingIndex();
        BoardingPass departBP = CurrentRoute.getInstance().getBoardingPass(index);
        String departAirport = departBP.getDeparture();
        String arriveAirport = CurrentRoute.getInstance().getLastBoardingPass().getArrival();
        Date curDate = departBP.getDepartureTime();

        /* create Rescheduler*/
        rescheduler = new ProxyRescheduler();
        rescheduler.findAvailableRoutes(departAirport, arriveAirport, false/*nearby*/,
                0, curDate, getActivity().getApplicationContext());

        return rescheduler.getRoutingResultInListView();
    }
}
