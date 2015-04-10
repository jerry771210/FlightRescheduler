package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moumoutsay on 4/9/15.
 */
public class ScanQRCodeOnClickListener implements View.OnClickListener {

    private Activity act;

    public ScanQRCodeOnClickListener(Activity act) {
        this.act = act;
    }

    @Override
    public void onClick(View v) {
        IntentIntegrator scanIntegrator = new IntentIntegrator(act);
        List<String> formats = new ArrayList<>();
        formats.add("QR Code");
        scanIntegrator.initiateScan(formats);
    }
}
