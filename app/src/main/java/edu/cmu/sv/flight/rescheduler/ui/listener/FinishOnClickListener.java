package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.view.View;

/**
 * Created by moumoutsay on 4/9/15.
 */
public class FinishOnClickListener implements View.OnClickListener {

    private Activity act;

    public FinishOnClickListener(Activity act) {
        this.act = act;
    }

    @Override
    public void onClick(View v) { act.finish(); }
}
