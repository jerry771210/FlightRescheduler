package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.IRescheduler;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.ProxyRescheduler;
import edu.cmu.sv.flight.rescheduler.ui.R;

/**
 * Created by moumoutsay on 4/9/15.
 *
 * If Class is null, then dismiss dialog only
 */
public class DialogDismissAndIntentToAnotherActivityOnClickListener implements View.OnClickListener {
    private Activity act;
    private Dialog dia;
    private Class actClass;
    private Integer alternativeOptionIndex = null;


    public DialogDismissAndIntentToAnotherActivityOnClickListener(Activity act, Dialog dia, Class actClass) {
        this.act = act;
        this.dia = dia;
        this.actClass = actClass;
    }

    public DialogDismissAndIntentToAnotherActivityOnClickListener(Activity act, Dialog dia, Class actClass, Integer index) {
        this(act, dia, actClass);
        alternativeOptionIndex = index;
    }

    @Override
    public void onClick(View v) {
        dia.dismiss();
        if(v.getId() == R.id.buttonRebook) {
            // TODO: Update the boarding pass after confirmation
            // TODO: Need algorithms for retrieving new boarding passes
            // Mock the updated boarding passes
            // Not sure whether it's good to do it here
            BoardingPass boardingPass = new BoardingPass();
            boardingPass.setStatus(BoardingPass.Status.ON_TIME);
            CurrentRoute currentRoute = CurrentRoute.getInstance();
            IRescheduler rescheduler = new ProxyRescheduler();
            if(alternativeOptionIndex != null)
                currentRoute.updateBoardingPass(
                        rescheduler.getRoutingResult().get(alternativeOptionIndex));
            else {
                Log.d("Exception", "[DialogDismiss] alternativeOptionIndex is not set");
            }

        }

        if (actClass != null) {
            act.startActivity(new Intent(act , actClass));
        }
    }
}
