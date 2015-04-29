package edu.cmu.sv.flight.rescheduler.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsuantzl on 2015/4/18.
 * Utility functions now empty.
 */
public class Utils {
    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    /* Read CSV text file in assets folder */
    public List<String[]> readCSVFile(String filename) {
        List<String[]> result = new ArrayList<>();
        BufferedReader br = null;
        AssetManager assetManager = context.getAssets();

        try {
            br = new BufferedReader(new InputStreamReader(assetManager.open(filename)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*,\\s*");
                // Remove " in the String
                if(parts[4].equals("\"\"")) // Neglect the record if IATA/FAA code is not assigned
                    continue;
                for(int i = 0; i < parts.length; i++) {
                        parts[i] = parts[i].replaceAll("\"","");
                }
                result.add(parts);
            }
        }
        catch (IOException e) {
            Log.d("Utils", "IOException when readCSVFile"+Log.getStackTraceString(e));
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    Log.d("Utils", "Can not close BufferedReader");
                }
            }
        }
        Log.d("Utils", "readCSVFile(" + filename + ") complete");
        return result;
    }

    /**
     * Calculate Distance by Latitude and Longitude
     * @param lat1: Latitude of location1
     * @param lng1: Longitude of location1
     * @param lat2: Latitude of location2
     * @param lng2: Longitude of location2
     * @return: Distance in miles
     */
    public double distanceFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sinLat = Math.sin(dLat / 2);
        double sinLng = Math.sin(dLng / 2);
        double a = Math.pow(sinLat, 2) + Math.pow(sinLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }
}
