package edu.cmu.sv.flight.rescheduler.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;

/**
 * Created by hsuantzl on 2015/4/18.
 * Utility functions now empty.
 */
public class Utils {
    private Context context;

    public Utils() { /* For the methods that do not need context only */ }

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
                // Neglect the record if IATA/FAA code is not assigned
                if(parts.length >= 5 && parts[4].equals("\"\""))
                    continue;

                for(int i = 0; i < parts.length; i++) {
                    // Remove " in the String
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

    public String parseDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return df.format(date);
    }

    public Date parseStringToDate(String s) {
        // JSON format is 2015-05-01T08:20:00.000
        DateFormat df;
        if(s.contains("-"))  // For JSON format (ISO 8601)
            df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        else  // For boardingPass format
            df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = null;
        try {
            date = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String parseListBoardingPassToDescription(List<BoardingPass> boardingPassList) {
        StringBuilder sb = new StringBuilder();
        StringBuilder airlines = new StringBuilder(boardingPassList.get(0).getCarrierCode());
        StringBuilder route = new StringBuilder(boardingPassList.get(0).getDeparture());
        String time = "Arrive at " + parseDateToString(
                boardingPassList.get(boardingPassList.size()-1).getArrivalTime());

        for(BoardingPass boardingPass: boardingPassList) {
            if(!airlines.toString().contains(boardingPass.getCarrierCode())) {
                airlines.append("/" + boardingPass.getCarrierCode());
            }
            route.append("-" + boardingPass.getArrival());
        }

        sb.append(airlines);
        sb.append(" ");
        sb.append(route);
        sb.append(" ");
        sb.append(time);

        return sb.toString();
    }
}
