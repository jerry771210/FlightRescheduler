package edu.cmu.sv.flight.rescheduler.ui.update;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by moumoutsay on 4/10/15.
 */
public class UpdateSpinner {
    public static void update(Activity act, int layoutId, int textViewId, int spinnerId, String[] strArr) {
//        Spinner spinnerOptions = (Spinner) act.findViewById(R.id.spinnerAlternativeRoutes);
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(act, R.layout.list_item_available_route, R.id.list_item_available_route_textview, strArr);
//
//        // Apply the adapter to the spinner
//        spinnerOptions.setAdapter(spinnerAdapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(act, layoutId, textViewId, strArr);
        Spinner spinnerOptions = (Spinner) act.findViewById(spinnerId);

        // Apply the adapter to the spinner
        spinnerOptions.setAdapter(spinnerAdapter);
    }
}
