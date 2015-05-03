package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.ProxyRescheduler;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.Rescheduler;
import edu.cmu.sv.flight.rescheduler.ui.AlternativeOptionsFragment;
import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.update.UpdateListView;

/**
 * Created by moumoutsay on 4/9/15.
 *
 * Handle everything when user click other airlines button
 * 1. Update spinner
 * 2. Update listView
 * 3. Update airline button value
 * Note: The behavior will depends on the current value of buttons
 */
public class OtherAirlinesOnClickListener implements View.OnClickListener {
    private static final String LOG_TAG = AlternativeOptionsFragment.class.getSimpleName();

    private Activity act;

    public OtherAirlinesOnClickListener(Activity act) {
        this.act = act;
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "Click other airlines");
        Rescheduler rescheduler = new ProxyRescheduler();
        rescheduler.setIsMultipleAirlines(!rescheduler.isMultipleAirlines());

        // Get depart and arrive info
        int index = CurrentRoute.getInstance().getStartingIndex();
        BoardingPass departBP = CurrentRoute.getInstance().getBoardingPass(index);
        String departAirport = departBP.getDeparture();
        String arriveAirport = CurrentRoute.getInstance().getLastBoardingPass().getArrival();
        Date curDate = departBP.getDepartureTime();

        rescheduler.findAvailableRoutes(departAirport, arriveAirport, false, 0, curDate, act);
        updateSwitchAirlinesListView(rescheduler.getRoutingResultInListView());
        updateSwitchAirLinesButtonVal();
    }

    private void updateSwitchAirlinesListView(List<String> list) {
    UpdateListView.update(act, R.layout.list_item_available_route,
            R.id.textViewListItemAvailableRoute, R.id.listViewAlternativeRoute, list);

    }


    private void updateSwitchAirLinesButtonVal() {
        // get the button current value
        Button buttonOtherAirlines = (Button) act.findViewById(R.id.buttonOtherAirlines);
        String buttonVal = buttonOtherAirlines.getText().toString();
        if ("Other Airlines".equals(buttonVal)) {
            buttonOtherAirlines.setText("Original Airlines");
        } else {
            buttonOtherAirlines.setText("Other Airlines");
        }
        return;
    }
}
