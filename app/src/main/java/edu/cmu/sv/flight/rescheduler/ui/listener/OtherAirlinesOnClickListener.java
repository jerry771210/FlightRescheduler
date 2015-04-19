package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;
import edu.cmu.sv.flight.rescheduler.ui.AlternativeOptionsFragment;
import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.update.UpdateListview;

/**
 * Created by moumoutsay on 4/9/15.
 *
 * Handle everything when user click other airlines button
 * 1. Update spinner
 * 2. Update listView
 * 3. Update airline button value
 * Note: The behavior will depends on the current value of buttons
 *
 * TODO: We use mock data for now. Will use real model in the future
 */
public class OtherAirlinesOnClickListener implements View.OnClickListener {
    private static final String LOG_TAG = AlternativeOptionsFragment.class.getSimpleName();
    private CurrentRoute currentRoute; // TODO, replace with real rescheduler

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


    private Activity act;

    public OtherAirlinesOnClickListener(Activity act) {
        this.act = act;
        currentRoute = new CurrentRoute();
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "Click other airlines");
        updateSwitchAirlinesListView();
        updateSwitchAirLinesButtonVal();
    }

    private void updateSwitchAirlinesListView() {
        // get the button current value
        Button buttonOtherAirlines = (Button) act.findViewById(R.id.buttonOtherAirlines);
        String buttonVal = buttonOtherAirlines.getText().toString();
        List<String> availableList;
        // TODO availableList = rescheduler.getXXX + further processing
        if ("Other Airlines".equals(buttonVal)) {
            UpdateListview.update(act, R.layout.list_item_available_route,
                    R.id.textViewListItemAvailableRoute,
                    R.id.listViewAlternativeRoute,
                    mockOtherAirlines);
        } else {
            UpdateListview.update(act, R.layout.list_item_available_route,
                    R.id.textViewListItemAvailableRoute,
                    R.id.listViewAlternativeRoute,
                    mockOptions);
        }
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
