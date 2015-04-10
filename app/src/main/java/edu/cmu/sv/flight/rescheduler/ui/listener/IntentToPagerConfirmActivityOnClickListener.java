package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import edu.cmu.sv.flight.rescheduler.ui.activity.PagerConfirmActivity;

/**
 * Created by moumoutsay on 4/9/15.
 */
public class IntentToPagerConfirmActivityOnClickListener implements View.OnClickListener {

    private Activity act;

    public IntentToPagerConfirmActivityOnClickListener(Activity act) {
        this.act = act;
    }

    @Override
    public void onClick(View v) {
        act.startActivity(new Intent(act, PagerConfirmActivity.class));
    }
}
