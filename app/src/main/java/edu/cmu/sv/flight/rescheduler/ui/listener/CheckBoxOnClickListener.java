package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.view.View;
import android.widget.CheckBox;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by moumoutsay on 4/9/15.
 */
public class CheckBoxOnClickListener implements View.OnClickListener {

    private AtomicBoolean bool; // true if checkBox is checked

    public CheckBoxOnClickListener(AtomicBoolean bool) {
        this.bool = bool;
    }

    @Override
    public void onClick(View v) {
        if(((CheckBox)v).isChecked()) {
            bool.set(true);
        } else {
            bool.set(false);
        }
    }
}
