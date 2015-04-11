package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;

/**
 * Created by moumoutsay on 4/9/15.
 *
 * If Class is null, then dismiss dialog only
 */
public class DiaglogDismissAndIntentToAnotherActivityOnClickListener implements View.OnClickListener {

    private Activity act;
    Dialog dia;
    Class actClass;


    public DiaglogDismissAndIntentToAnotherActivityOnClickListener(Activity act, Dialog dia, Class actClass) {
        this.act = act;
        this.dia = dia;
        this.actClass = actClass;
    }

    @Override
    public void onClick(View v) {
        dia.dismiss();
        if (actClass != null) {
            act.startActivity(new Intent(act , actClass));
        }
    }
}
