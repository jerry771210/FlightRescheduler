package edu.cmu.sv.flight.rescheduler.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.CurrentRoute;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class BoardingPassAdapter extends PagerAdapter {
    private CurrentRoute currentRoute;
    private Activity activity;

    public BoardingPassAdapter(Activity activity) {
        this.activity = activity;
        currentRoute = CurrentRoute.getInstance();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return currentRoute.numOfBoardingPasses();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        BoardingPass boardingPass = currentRoute.getBoardingPass(position);

        BoardingPassView boardingPassView =
                new BoardingPassView(activity, inflater, boardingPass, position);
        View view = boardingPassView.getLayoutView();
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
