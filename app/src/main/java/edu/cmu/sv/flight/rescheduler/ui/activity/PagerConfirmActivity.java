package edu.cmu.sv.flight.rescheduler.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

import edu.cmu.sv.flight.rescheduler.ui.BoardingPassAdapter;
import edu.cmu.sv.flight.rescheduler.ui.R;
import edu.cmu.sv.flight.rescheduler.ui.listener.IntentToActivityOnClickListener;


public class PagerConfirmActivity extends Activity {
    private ViewPager viewPager;
    BoardingPassAdapter adapter;
    private Button buttonConfirm;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_confirm);

        buttonCancel = (Button) findViewById(R.id.buttonCancelConfirm);
        buttonCancel.setOnClickListener(new IntentToActivityOnClickListener(this, AlternativeOptionsActivity.class));
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new IntentToActivityOnClickListener(this, BoardingPassActivity.class, true));

        viewPager = (ViewPager)findViewById(R.id.viewPagerConfirm);
        adapter = new BoardingPassAdapter(this);
        viewPager.setAdapter(adapter);

        //Bind the CirclePageIndicator to the adapter
        CirclePageIndicator viewPagerIndicator = (CirclePageIndicator)findViewById(R.id.viewPagerIndicator);
        viewPagerIndicator.setViewPager(viewPager);

    }

}