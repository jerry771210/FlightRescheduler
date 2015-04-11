package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.update.UpdateListview;

/**
 * Created by hsuantzl on 2015/4/4.
 *
 * TODO, think about how to extract OnSeekBarChangeListener part
 * TODO, refactor to move out onClick Switch to let it simpler
 * TODO, think about rename the class
 */
public class AdvancedSearch implements OnSeekBarChangeListener, OnClickListener {

    // TODO remove these mock data later
    private final String[] mockAdvancedSearch = {
            " 1 LAX - ACY Arrived at 08:00PM 10/23",
            " 2 LAX - EWR Arrived at 09:00PM 10/23",
            " 3 LAX - ACY Arrived at 05:00AM 10/24",
            " 4 LAX - TTN Arrived at 10:00AM 10/24",
            " 5 LAX - NYC Arrived at 11:00AM 10/24",
            " 6 LAX - TEB Arrived at 08:00PM 10/23",
            " 7 LAX - TEB Arrived at 09:00PM 10/23",
            " 8 LAX - ACY Arrived at 05:00AM 10/24",
            " 9 LAX - TTN Arrived at 10:00AM 10/24",
            "10 LAX - EWR Arrived at 11:00AM 10/24",
            "11 LAX - ACY Arrived at 08:00PM 10/23",
            "12 LAX - TTN Arrived at 09:00PM 10/23",
            "13 LAX - ACY Arrived at 05:00AM 10/24",
            "14 LAX - TTN Arrived at 10:00AM 10/24",
            "15 LAX - TTN Arrived at 11:00AM 10/24"
    };


    private Activity act;
    private int numStops = 1;
    private boolean overNight;
    private boolean noSeat;
    private boolean nearbyAirport;

    private Dialog dialog;
    private TextView textViewNumStop;
    private SeekBar seekBar;
    private CheckBox checkBoxOverNight;
    private CheckBox checkBoxNoSeat;
    private CheckBox checkBoxNearbyAirport;
    private Button confirm;

    public AdvancedSearch(FragmentActivity activity) {
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
            case R.id.buttonAdvancedSearch:
                init();
                showDialog();
                updateSpinnerAlternativeRoute(mockAdvancedSearch);
                UpdateListview.update(act, R.layout.list_item_available_route,
                        R.id.list_item_available_route_textview,
                        R.id.listviewAlternativeRoute,
                        mockAdvancedSearch);
                break;
        }
    }


    // TODO refactor this function, let it standalone
    private void updateSpinnerAlternativeRoute(String[] strArr) {

        Spinner spinnerOptions = (Spinner) act.findViewById(R.id.spinnerAlternativeRoutes);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(act, R.layout.list_item_available_route, R.id.list_item_available_route_textview, strArr);

        // Apply the adapter to the spinner
        spinnerOptions.setAdapter(spinnerAdapter);
    }
}
