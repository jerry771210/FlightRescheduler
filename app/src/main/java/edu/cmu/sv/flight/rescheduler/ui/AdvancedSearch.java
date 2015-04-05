package edu.cmu.sv.flight.rescheduler.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by hsuantzl on 2015/4/4.
 */
public class AdvancedSearch implements SeekBar.OnSeekBarChangeListener {
    private Dialog dialog;
    private TextView textViewNumStop;
    private SeekBar seekBar;
    private Button confirm;
    private int numStops = 1;

    public AdvancedSearch(Dialog dialog) {
        this.dialog = dialog;
        textViewNumStop = (TextView) dialog.findViewById(R.id.textViewNumStops);
        seekBar = (SeekBar) dialog.findViewById(R.id.seekBarNumberOfStops);
        confirm = (Button) dialog.findViewById(R.id.buttonAdvancedSearch);
    }
    public void init() {
        seekBar.setOnSeekBarChangeListener(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        numStops = progress+1;
        textViewNumStop.setText(numStops + " stops");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        textViewNumStop.setText(numStops + " stops");
    }
}
