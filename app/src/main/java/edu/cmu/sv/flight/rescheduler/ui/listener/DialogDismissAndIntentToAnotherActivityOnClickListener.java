package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.Rescheduler;
import edu.cmu.sv.flight.rescheduler.ui.R;

/**
 * Created by moumoutsay on 4/9/15.
 *
 * If Class is null, then dismiss dialog only
 */
public class DialogDismissAndIntentToAnotherActivityOnClickListener implements View.OnClickListener {

    private Activity act;
    Dialog dia;
    Class actClass;


    public DialogDismissAndIntentToAnotherActivityOnClickListener(Activity act, Dialog dia, Class actClass) {
        this.act = act;
        this.dia = dia;
        this.actClass = actClass;
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
            Rescheduler rescheduler = new Rescheduler();
            rescheduler.updateBoardingPass(2, boardingPass);
            rescheduler.updateBoardingPass(3, boardingPass);
        }

        if (actClass != null) {
            act.startActivity(new Intent(act , actClass));
        }
    }
}
