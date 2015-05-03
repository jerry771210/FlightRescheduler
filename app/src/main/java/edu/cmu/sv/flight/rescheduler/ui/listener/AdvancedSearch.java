package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;
import edu.cmu.sv.flight.rescheduler.ui.R;

/**
 * Created by hsuantzl on 2015/4/4.
 *
 * TODO, think about how to extract OnSeekBarChangeListener part
 * TODO, think about rename the class
 */
public class AdvancedSearch implements OnSeekBarChangeListener, OnClickListener {
    private CurrentRoute currentRoute;
    private Activity act;
    private int numStops = 1;

    private Dialog dialog;
    private TextView textViewNumStop;
    private SeekBar seekBar;
    private CheckBox checkBoxOverNight;
    private CheckBox checkBoxNoSeat;
    private CheckBox checkBoxNearbyAirport;
    private Button confirm;

    public AdvancedSearch(Activity activity) {
        act = activity;
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.advanced_search);
        textViewNumStop = (TextView) dialog.findViewById(R.id.textViewNumStops);
        seekBar = (SeekBar) dialog.findViewById(R.id.seekBarNumberOfStops);
        checkBoxOverNight  = (CheckBox) dialog.findViewById(R.id.checkBoxOverNight);
        checkBoxNoSeat = (CheckBox) dialog.findViewById(R.id.checkBoxNoSeat);
        checkBoxNearbyAirport = (CheckBox) dialog.findViewById(R.id.checkBoxNearbyAirport);
        confirm = (Button) dialog.findViewById(R.id.buttonAdvancedSearchConfirm);
    }

    public void init() {
        seekBar.setOnSeekBarChangeListener(this);
        currentRoute = CurrentRoute.getInstance();

        confirm.setOnClickListener(new DialogDismissAndIntentToAnotherActivityOnClickListener(
                act, dialog, null, null));
        dialog.setTitle("Advanced search");
    }
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        numStops = progress;
        textViewNumStop.setText(numStops + " stops");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        textViewNumStop.setText(numStops + " stops");
    }

    @Override
    public void onClick(View v) {
        init();
        showDialog();
    }
}
