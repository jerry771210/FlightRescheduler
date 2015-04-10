package edu.cmu.sv.flight.rescheduler.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import edu.cmu.sv.flight.rescheduler.ui.activity.PagerConfirmActivity;

/**
 * Created by hsuantzl on 2015/4/4.
 */
public class ShowFlightDetails implements OnClickListener {
    private FragmentActivity fragActivity;
    private Dialog dialog;
    private Button buttonRebook;
    private Button buttonCancel;

    public ShowFlightDetails(String flight, FragmentActivity activity) {
        this.fragActivity = activity;
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.flight_details);
        buttonRebook = (Button) dialog.findViewById(R.id.buttonRebook);
        buttonRebook.setOnClickListener(this);
        buttonCancel = (Button) dialog.findViewById(R.id.buttonCancelRebook);
        buttonCancel.setOnClickListener(this);
    }
    public void display() {
        dialog.setTitle("Flight details");
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRebook:
                dialog.dismiss();
                fragActivity.startActivity(new Intent(fragActivity , PagerConfirmActivity.class));
                break;
            case R.id.buttonCancelRebook:
                dialog.dismiss();
                break;
        }
    }
}
