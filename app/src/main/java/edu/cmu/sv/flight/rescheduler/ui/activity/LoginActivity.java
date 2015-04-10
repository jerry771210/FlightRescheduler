package edu.cmu.sv.flight.rescheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.listener.IntentToPageActivityOnClickListener;

// TODO, refactor to move OnClickListener out

public class LoginActivity extends ActionBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button scanButton = (Button)findViewById(R.id.buttonScanQRCode);
        Button loginButton = (Button)findViewById(R.id.buttonLogin);
        scanButton.setOnClickListener(this);
        loginButton.setOnClickListener(new IntentToPageActivityOnClickListener(this));
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.buttonScanQRCode:
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                List<String> formats = new ArrayList<>();
                formats.add("QR Code");
                scanIntegrator.initiateScan(formats);
                break;

//            case R.id.buttonLogin:
//                startActivity(new Intent(this, PagerActivity.class));
//                break;
        }
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
