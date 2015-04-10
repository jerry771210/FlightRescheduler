package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import edu.cmu.sv.flight.rescheduler.ui.ShowFlightDetails;

/**
 * Created by moumoutsay on 4/10/15.
 */
// TODO may need rename to ShowRouteDetailxxxx ...
public class ShowFlightDetailOnItemClickListener implements AdapterView.OnItemClickListener {

    private Activity act;

    public ShowFlightDetailOnItemClickListener(Activity act) {
        this.act = act;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = ((TextView)view).getText().toString();
        ShowFlightDetails showDetails = new ShowFlightDetails(item, act);
        showDetails.display();
    }
}
