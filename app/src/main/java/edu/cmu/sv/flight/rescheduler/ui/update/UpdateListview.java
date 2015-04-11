package edu.cmu.sv.flight.rescheduler.ui.update;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by moumoutsay on 4/10/15.
 */
public class UpdateListview {
    public static void update(Activity act, int layoutId, int textViewId, int listViewId, String[] strArr) {
//        List<String> availableList = Arrays.asList(strArr);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String> (act, R.layout.list_item_available_route, R.id.list_item_available_route_textview, availableList);
//        ListView lv = (ListView) act.findViewById(R.id.listviewAlternativeRoute);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (act, layoutId, textViewId, strArr);
        ListView lv = (ListView) act.findViewById(listViewId);
        lv.setAdapter(adapter);
    }
}
