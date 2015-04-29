package edu.cmu.sv.flight.rescheduler.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.Airport;
import edu.cmu.sv.flight.rescheduler.util.Utils;

/**
 * Created by hsuantzl on 2015/4/28.
 */
public class DBInitializationAsyncTask extends AsyncTask {
    private Context context;
    @Override
    protected Object doInBackground(Object[] params) {
        context = (Context)params[0];
        String ASSETS_AIRPORT = (String)params[1];
        Utils utils = new Utils(context);

        List<String[]> airportList = utils.readCSVFile(ASSETS_AIRPORT);
        AirportCRUD airportCRUD = new AirportCRUD(context);
        for(String[] s: airportList) {
            Airport airport = new Airport(s[1], s[2], s[4],
                    Double.parseDouble(s[6]),
                    Double.parseDouble(s[7]), s[9]);
            airportCRUD.insertAirport(airport);
        }
        Log.d("Database", "Insert " + airportList.size() + " records into airport table");
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("Database", "Initialization asyncTask complete");
        Toast.makeText(context, "DB initialized", Toast.LENGTH_LONG).show();
    }
}
