package edu.cmu.sv.flight.rescheduler.ui;

/**
 * Created by chihengw on 4/4/15.
 */

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

public class PagerActivity extends Activity {

    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = (ViewPager)findViewById(R.id.myviewpager);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);

    }

    private class MyPagerAdapter extends PagerAdapter{

        int NumberOfPages = 5;

        int[] res = {
                R.drawable.boarding_pass_depart,
                R.drawable.boarding_pass_landed,
                R.drawable.boarding_pass_normal,
                R.drawable.boarding_pass_normal,
                R.drawable.boarding_pass_cancel};

        int[] backgroundcolor = {
                0xffffffff,
                0xffffffff,
                0xffffffff,
                0xffffffff,
                0xffffffff};

        @Override
        public int getCount() {
            return NumberOfPages;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            TextView textView = new TextView(PagerActivity.this);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(50);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setText(String.valueOf(position));

            ImageView imageView = new ImageView(PagerActivity.this);
            imageView.setImageResource(res[position]);
            //imageView.setImageResource(R.drawable.boarding_pass_dept);

            LayoutParams imageParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(PagerActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            layout.setBackgroundColor(backgroundcolor[position]);
            layout.setLayoutParams(layoutParams);
            layout.addView(textView);
            layout.addView(imageView);

            final int page = position;
            layout.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View v) {
                    Toast.makeText(PagerActivity.this,
                            "Page " + page + " clicked",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(PagerActivity.this, LoginActivity.class));
                }});

            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }

    }

}