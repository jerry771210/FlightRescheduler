package edu.cmu.sv.flight.rescheduler.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.IRescheduler;
import edu.cmu.sv.flight.rescheduler.entities.rescheduler.ProxyRescheduler;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class BoardingPassAdapter extends PagerAdapter {
    private IRescheduler rescheduler;
    private Activity activity;

    public BoardingPassAdapter(Activity activity) {
        this.activity = activity;
        rescheduler = new ProxyRescheduler();
        this.notifyDataSetChanged();
    }

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

        BoardingPass boardingPass = rescheduler.getBoardingPass(position);

        BoardingPassView boardingPassView =
                new BoardingPassView(activity, inflater, boardingPass);
        View view = boardingPassView.getLayoutView();
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
