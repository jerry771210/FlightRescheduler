package edu.cmu.sv.flight.rescheduler.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import edu.cmu.sv.flight.rescheduler.ui.R;

public class PagerConfirmActivity extends Activity {

    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;
    Button btn_confirm;
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_confirm);
        viewPager = (ViewPager)findViewById(R.id.viewPagerConfirm);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);

    }

    // TODO we may need extract this class out because Cathy said we should not use inner class
    private class MyPagerAdapter extends PagerAdapter{

        int NumberOfPages = 5;

        int[] res = {
                R.drawable.boarding_pass_normal,
                R.drawable.boarding_pass_normal,
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


            ImageView imageView = new ImageView(PagerConfirmActivity.this);
            imageView.setImageResource(res[position]);


            LayoutParams imageParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            btn_confirm =  (Button)findViewById(R.id.buttonConfirm);
            btn_cancel = (Button)findViewById(R.id.buttonCancel);


            LinearLayout layout = new LinearLayout(PagerConfirmActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

            layout.setLayoutParams(layoutParams);
            layout.addView(imageView);

            final int page = position;

            btn_confirm.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent myIntent = new Intent(PagerConfirmActivity.this, BoardingPassActivity.class);
                    myIntent.putExtra("confirm","isConfirm");
                    startActivity(myIntent);
                }
            });
            btn_cancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    finish();
                }
            });


            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }

    }

}