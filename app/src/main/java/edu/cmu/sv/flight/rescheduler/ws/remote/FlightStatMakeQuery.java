package edu.cmu.sv.flight.rescheduler.ws.remote;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
* Created by moumoutsay on 5/2/15.
*/
public class FlightStatMakeQuery extends AsyncTask<String/*String of URL*/, Void, String> {
    private String res;
    @Override
    protected String doInBackground(String... params) {
        res = new String();
        // get raw data
        try {
            String tmpString;
            URL targetURL = new URL(params[0]);
            URLConnection uc = targetURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((tmpString = in.readLine()) != null) {
                res = res + tmpString;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(FlightStatAPIWrapper.LOG_TAG, "Can not query data" + e);
            res = null;
        }
//        Log.i("FlightStatMakeQuery", "Get json" + res);
        return res;
    }
}
