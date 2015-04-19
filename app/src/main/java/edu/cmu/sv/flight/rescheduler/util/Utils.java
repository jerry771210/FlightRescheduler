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
}
