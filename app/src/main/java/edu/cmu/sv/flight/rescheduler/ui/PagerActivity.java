package edu.cmu.sv.flight.rescheduler.ui;

/**
 * Created by chihengw on 4/4/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                R.drawable.boarding_pass_cancel,
                R.drawable.boarding_pass_normal,
                R.drawable.boarding_pass_cancel};

        int[] res2 = {
                R.drawable.boarding_pass_landed,
                R.drawable.boarding_pass_landed,
                R.drawable.boarding_pass_normal,
                R.drawable.boarding_pass_normal,
                R.drawable.boarding_pass_normal};

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

            boolean confirm = false;

            TextView textView = new TextView(PagerActivity.this);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(50);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setText(String.valueOf(position));

            ImageView imageView = new ImageView(PagerActivity.this);


            Intent curIntent = getIntent();
            String isConfirm = curIntent.getStringExtra("confirm");
            if("isConfirm".equals(isConfirm)) {
                imageView.setImageResource(res2[position]);
                confirm = true;
            } else
                imageView.setImageResource(res[position]);
            //imageView.setImageResource(R.drawable.boarding_pass_dept);

            LayoutParams imageParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(PagerActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            //layout.setBackgroundColor(backgroundcolor[position]);

            layout.setLayoutParams(layoutParams);
//            layout.addView(textView);
            layout.addView(imageView);

            final int page = position;

            final boolean finalConfirm = confirm;
            layout.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View v) {
//                    Toast.makeText(PagerActivity.this,
//                            "Page " + page + " clicked",
//                            Toast.LENGTH_LONG).show();
                    if(!finalConfirm)
                        if(res[page] == R.drawable.boarding_pass_cancel)
                            startActivity(new Intent(PagerActivity.this, AlternativeOptionsActivity.class));
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