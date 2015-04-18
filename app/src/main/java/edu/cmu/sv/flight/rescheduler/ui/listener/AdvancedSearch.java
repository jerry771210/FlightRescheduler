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

import edu.cmu.sv.flight.rescheduler.entities.rescheduler.IRescheduler;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.ProxyRescheduler;
import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.update.UpdateListview;

/**
 * Created by hsuantzl on 2015/4/4.
 *
 * TODO, think about how to extract OnSeekBarChangeListener part
 * TODO, think about rename the class
 */
public class AdvancedSearch implements OnSeekBarChangeListener, OnClickListener {

    private IRescheduler rescheduler;

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

    private AtomicBoolean overNight;
    private AtomicBoolean noSeat;
    private AtomicBoolean nearbyAirport;

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
        overNight = new AtomicBoolean(false);
        noSeat = new AtomicBoolean(false);
        nearbyAirport = new AtomicBoolean(false);
    }

    public boolean isNearbyAirport() {
        return nearbyAirport.get();
    }

    public boolean isNoSeat() {
        return noSeat.get();
    }

    public boolean isOverNight() {
        return overNight.get();
    }

    public int getNumStops() {
        return numStops;
    }

    public void init() {
        seekBar.setOnSeekBarChangeListener(this);
        rescheduler = new ProxyRescheduler();

        confirm.setOnClickListener(new DialogDismissAndIntentToAnotherActivityOnClickListener(act, dialog, null));
        checkBoxOverNight.setOnClickListener(new CheckBoxOnClickListener(overNight));
        checkBoxNoSeat.setOnClickListener(new CheckBoxOnClickListener(noSeat));
        checkBoxNearbyAirport.setOnClickListener(new CheckBoxOnClickListener(nearbyAirport));
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
        // TODO, get data from ProxyRescheduler
        UpdateListview.update(act, R.layout.list_item_available_route,
                R.id.textViewListItemAvailableRoute,
                R.id.listViewAlternativeRoute,
                mockAdvancedSearch);
    }
}
