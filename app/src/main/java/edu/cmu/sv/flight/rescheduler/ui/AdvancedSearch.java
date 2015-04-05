package edu.cmu.sv.flight.rescheduler.ui;


import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * Created by hsuantzl on 2015/4/4.
 */
public class AdvancedSearch implements OnSeekBarChangeListener, OnClickListener {
    private Dialog dialog;
    private TextView textViewNumStop;
    private SeekBar seekBar;
    private CheckBox checkBoxOverNight;
    private CheckBox checkBoxNoSeat;
    private CheckBox checkBoxNearbyAirport;
    private Button confirm;

    private int numStops = 1;
    private boolean overNight;
    private boolean noSeat;
    private boolean nearbyAirport;

    public AdvancedSearch(Dialog dialog) {
        this.dialog = dialog;
        textViewNumStop = (TextView) dialog.findViewById(R.id.textViewNumStops);
        seekBar = (SeekBar) dialog.findViewById(R.id.seekBarNumberOfStops);
        checkBoxOverNight  = (CheckBox) dialog.findViewById(R.id.checkBoxOverNight);
        checkBoxNoSeat = (CheckBox) dialog.findViewById(R.id.checkBoxNoSeat);
        checkBoxNearbyAirport = (CheckBox) dialog.findViewById(R.id.checkBoxNearbyAirport);
        confirm = (Button) dialog.findViewById(R.id.buttonAdvancedSearch);
    }

    public boolean isNearbyAirport() {
        return nearbyAirport;
    }

    public boolean isNoSeat() {
        return noSeat;
    }

    public boolean isOverNight() {
        return overNight;
    }

    public int getNumStops() {
        return numStops;
    }

    public void init() {
        seekBar.setOnSeekBarChangeListener(this);
        confirm.setOnClickListener(this);
        checkBoxOverNight.setOnClickListener(this);
        checkBoxNoSeat.setOnClickListener(this);
        checkBoxNearbyAirport.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkBoxOverNight:
                if(((CheckBox) v).isChecked())
                    overNight = true;
                else
                    overNight = false;
                break;
            case R.id.checkBoxNoSeat:
                if(((CheckBox) v).isChecked())
                    noSeat = true;
                else
                    noSeat = false;
                break;
            case R.id.checkBoxNearbyAirport:
                if(((CheckBox )v).isChecked())
                    nearbyAirport = true;
                else
                    nearbyAirport = false;
                break;
            case R.id.buttonAdvancedSearchConfirm:
                dialog.dismiss();
                break;
        }
    }
}
