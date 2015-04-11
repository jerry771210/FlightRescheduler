package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import edu.cmu.sv.flight.rescheduler.ui.activity.AlternativeOptionsActivity;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class IntentToAlternativeOptsActivityOnClickListener implements View.OnClickListener {

    private Activity act;

    public IntentToAlternativeOptsActivityOnClickListener(Activity act) {
        this.act = act;
    }

    @Override
    public void onClick(View v) {
        act.startActivity(new Intent(act, AlternativeOptionsActivity.class));
    }
}
