package edu.cmu.sv.flight.rescheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import edu.cmu.sv.flight.rescheduler.database.AirportCRUD;
import edu.cmu.sv.flight.rescheduler.database.DBUtil;
import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.listener.IntentToActivityOnClickListener;
import edu.cmu.sv.flight.rescheduler.ui.listener.ScanQRCodeOnClickListener;

public class LoginActivity extends ActionBarActivity {
    private DBUtil db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button scanButton = (Button)findViewById(R.id.buttonScanQRCode);
        Button loginButton = (Button)findViewById(R.id.buttonLogin);
        scanButton.setOnClickListener(new ScanQRCodeOnClickListener(this));
        loginButton.setOnClickListener(new IntentToActivityOnClickListener(this, BoardingPassActivity.class));

        // Force database to execute initialization process
        // It reads assets files into database
        db = new DBUtil(this);
        db.initialize();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Retrieve scan result
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Toast.makeText(getApplicationContext(), scanContent, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), scanFormat, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "No QR code received!", Toast.LENGTH_SHORT).show();
        }
    }
}
