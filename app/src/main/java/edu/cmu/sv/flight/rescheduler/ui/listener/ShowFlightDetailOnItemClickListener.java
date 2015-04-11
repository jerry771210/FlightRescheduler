package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.activity.PagerConfirmActivity;

/**
 * Created by moumoutsay on 4/10/15.
 */
// TODO may need rename to ShowRouteDetailxxxx ...
public class ShowFlightDetailOnItemClickListener implements AdapterView.OnItemClickListener {

    private Activity act;
    private Dialog dialog;
    private Button buttonRebook;
    private Button buttonCancel;


    public ShowFlightDetailOnItemClickListener(Activity act) {
        this.act = act;
        dialog = new Dialog(act);
        dialog.setContentView(R.layout.flight_details);
        dialog.setTitle("Flight details");

        buttonRebook = (Button) dialog.findViewById(R.id.buttonRebook);
        buttonCancel = (Button) dialog.findViewById(R.id.buttonCancelRebook);
        buttonRebook.setOnClickListener(new DialogDismissAndIntentToAnotherActivityOnClickListener(act, dialog, PagerConfirmActivity.class));
        buttonCancel.setOnClickListener(new DialogDismissAndIntentToAnotherActivityOnClickListener(act, dialog, null));
    }

    private void display(String fightDetails) {
        // TODO, to get the real detail data in the future
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        display( ((TextView)view).getText().toString());
    }
}
