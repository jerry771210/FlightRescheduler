package edu.cmu.sv.flight.rescheduler.ui.activity;

/**
 * Created by chihengw on 4/4/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;

import edu.cmu.sv.flight.rescheduler.ui.BoardingPassAdapter;
import edu.cmu.sv.flight.rescheduler.ui.R;


public class BoardingPassActivity extends Activity {
    ViewPager viewPager;
    BoardingPassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        adapter = new BoardingPassAdapter(this);
        viewPager.setAdapter(adapter);

        //Bind the CirclePageIndicator to the adapter
        CirclePageIndicator viewPagerIndicator = (CirclePageIndicator)findViewById(R.id.viewPagerIndicator);
        viewPagerIndicator.setViewPager(viewPager);

    }
}