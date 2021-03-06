package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;

import java.util.Date;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.ProxyRescheduler;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.Rescheduler;
import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.update.UpdateListView;

/**
 * Created by moumoutsay on 4/9/15.
 *
 * If Class is null, then dismiss dialog only
 */
public class DialogDismissAndIntentToAnotherActivityOnClickListener implements View.OnClickListener {
    private Activity act;
    private Dialog dia;
    private Class actClass;
    private Object object = null;

    public DialogDismissAndIntentToAnotherActivityOnClickListener(Activity act, Dialog dia,
                                                                  Class actClass, Object object) {
        this.act = act;
        this.dia = dia;
        this.actClass = actClass;
        this.object = object;
    }

    @Override
    public void onClick(View v) {
        dia.dismiss();
        switch (v.getId()) {
            case R.id.buttonRebook:
                Integer alternativeOptionIndex = (Integer) object;
                CurrentRoute.getInstance().backup();
                CurrentRoute currentRoute = CurrentRoute.getInstance();
                Rescheduler rescheduler = new ProxyRescheduler();
                currentRoute.updateBoardingPass(
                            rescheduler.getRoutingResult().get(alternativeOptionIndex));
                break;
            case R.id.buttonAdvancedSearchConfirm:
                // Get depart and arrive info
                int index = CurrentRoute.getInstance().getStartingIndex();
                BoardingPass departBP = CurrentRoute.getInstance().getBoardingPass(index);
                String departAirport = departBP.getDeparture();
                String arriveAirport = CurrentRoute.getInstance().getLastBoardingPass().getArrival();
                Date curDate = departBP.getDepartureTime();

                // Get parameters of advanced search
                boolean isNearbyAirport = ((CheckBox)dia.findViewById(R.id.checkBoxNearbyAirport)).isChecked();
                boolean isNoSeat =((CheckBox)dia.findViewById(R.id.checkBoxNoSeat)).isChecked();
                boolean isOverNight = ((CheckBox)dia.findViewById(R.id.checkBoxOverNight)).isChecked();
                int numStops = ((SeekBar)dia.findViewById(R.id.seekBarNumberOfStops)).getProgress();
                Log.d("AdvancedSearch", "nearby:" + isNearbyAirport);
                Log.d("AdvancedSearch", "numStops:" + numStops);

                /* create Rescheduler*/
                rescheduler = new ProxyRescheduler();
                rescheduler.findAvailableRoutes(departAirport, arriveAirport, isNearbyAirport,
                        numStops, curDate, act.getApplicationContext());


                UpdateListView.update(act, R.layout.list_item_available_route,
                        R.id.textViewListItemAvailableRoute,
                        R.id.listViewAlternativeRoute,
                        rescheduler.getRoutingResultInListView());
                break;

        }

        if (actClass != null) {
            act.startActivity(new Intent(act , actClass));
        }
    }
}
