package edu.cmu.sv.flight.rescheduler.ui.activity;

/**
 * Created by chihengw on 4/4/15.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import edu.cmu.sv.flight.rescheduler.model.BoardingPass;
import edu.cmu.sv.flight.rescheduler.model.Rescheduler;
import edu.cmu.sv.flight.rescheduler.ui.BoardingPassView;
import edu.cmu.sv.flight.rescheduler.ui.R;


public class BoardingPassActivity extends Activity {
    Rescheduler rescheduler = new Rescheduler();
    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return rescheduler.numOfBoardingPasses();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) container.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            BoardingPass boardingPass = new Rescheduler().getBoardingPass(position);
            BoardingPassView boardingPassView =
                    new BoardingPassView(BoardingPassActivity.this, inflater, boardingPass);
            View view = boardingPassView.getLayoutView();
            container.addView(view, 0);
            return view;

//            boolean confirm = false;
//
//            TextView textView = new TextView(BoardingPassActivity.this);
//            textView.setTextColor(Color.WHITE);
//            textView.setTextSize(50);
//            textView.setTypeface(Typeface.DEFAULT_BOLD);
//            textView.setText(String.valueOf(position));
//
//            ImageView imageView = new ImageView(BoardingPassActivity.this);
//
//
//            Intent curIntent = getIntent();
//            String isConfirm = curIntent.getStringExtra("confirm");
//            if("isConfirm".equals(isConfirm)) {
//                imageView.setImageResource(res2[position]);
//                confirm = true;
//            } else
//                imageView.setImageResource(boardingPasses[position]);
//            //imageView.setImageResource(R.drawable.boarding_pass_dept);
//
//            LayoutParams imageParams = new LayoutParams(
//                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//            imageView.setLayoutParams(imageParams);
//
//            LinearLayout layout = new LinearLayout(BoardingPassActivity.this);
//            layout.setOrientation(LinearLayout.VERTICAL);
//            LayoutParams layoutParams = new LayoutParams(
//                    LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
//            //layout.setBackgroundColor(backgroundcolor[position]);
//
//            layout.setLayoutParams(layoutParams);
////            layout.addView(textView);
//            layout.addView(imageView);
//
//            final int page = position;
//
//            final boolean finalConfirm = confirm;
//            layout.setOnClickListener(new OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(PagerActivity.this,
////                            "Page " + page + " clicked",
////                            Toast.LENGTH_LONG).show();
//                    if(!finalConfirm)
//                        if(boardingPasses[page] == R.drawable.boarding_pass_cancel)
//                            startActivity(new Intent(BoardingPassActivity.this, AlternativeOptionsActivity.class));
//                }});
//
//            container.addView(layout);
//            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }

    }

}