package edu.cmu.sv.flight.rescheduler.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.cmu.sv.flight.rescheduler.entities.BoardingPass;
import edu.cmu.sv.flight.rescheduler.ui.activity.AlternativeOptionsActivity;
import edu.cmu.sv.flight.rescheduler.ui.listener.IntentToActivityOnClickListener;

/**
 * Created by hsuantzl on 2015/4/10.
 */
public class BoardingPassView {
    private Activity activity;
    private View view;
    private TextView textViewStatus;
    private RelativeLayout layoutTop;
    private BoardingPass boardingPass;
    private TextView textViewDepart;
    private TextView textViewArrive;
    private TextView textViewFlightNum;
    private TextView textViewTerminal;
    private TextView textViewGate;
    private TextView textViewHourDigit1;
    private TextView textViewHourDigit2;
    private TextView textViewMinuteDigit1;
    private TextView textViewMinuteDigit2;

    private int index;

    public BoardingPassView(Activity activity, LayoutInflater inflater, BoardingPass boardingPass, int index) {
        this.index = index;
        this.activity = activity;
        this.view = inflater.inflate(R.layout.boarding_pass, null);
        this.boardingPass = boardingPass;

        textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
        textViewDepart = (TextView) view.findViewById(R.id.textViewDeparture);
        textViewArrive = (TextView) view.findViewById(R.id.textViewArrival);
        textViewFlightNum = (TextView) view.findViewById(R.id.textViewFlightNumber);
        textViewTerminal = (TextView) view.findViewById(R.id.textViewTerminal);
        textViewGate = (TextView) view.findViewById(R.id.textViewGate);
        textViewHourDigit1 = (TextView) view.findViewById(R.id.textViewHourDigit1);
        textViewHourDigit2 = (TextView) view.findViewById(R.id.textViewHourDigit2);
        textViewMinuteDigit1 = (TextView) view.findViewById(R.id.textViewMinuteDigit1);
        textViewMinuteDigit2 = (TextView) view.findViewById(R.id.textViewMinuteDigit2);

        layoutTop = (RelativeLayout) view.findViewById(R.id.RelativeLayoutTop);

        configure();
    }

    public View getLayoutView() {
        return view;
    }

    private void configure() {
        textViewDepart.setText(boardingPass.getDeparture());
        textViewArrive.setText(boardingPass.getArrival());
        textViewFlightNum.setText(boardingPass.getFlightNumber());
        textViewGate.setText(boardingPass.getGate());

        Date date = boardingPass.getDepartureTime();
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);  // assigns calendar to given date
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int minute = calendar.get(Calendar.MINUTE);
        textViewHourDigit1.setText(Integer.toString(hour/10));
        textViewHourDigit2.setText(Integer.toString(hour%10));
        textViewMinuteDigit1.setText(Integer.toString(minute/10));
        textViewMinuteDigit2.setText(Integer.toString(minute%10));

        switch (boardingPass.getStatus()) {
            case LANDED:  // Default layout is landed
                textViewStatus.setText("Landed");
                layoutTop.setBackgroundColor(0xC8003269);
                break;
            case ON_TIME:
                textViewStatus.setText("On Time");
                layoutTop.setBackgroundColor(0xC8176935);
                break;
            case DELAYED:
                textViewStatus.setText("Delayed");
                layoutTop.setBackgroundColor(0xC8EBB027);
                view.setOnClickListener(new IntentToActivityOnClickListener(activity, AlternativeOptionsActivity.class, index));
                break;
            case CANCELED:
                textViewStatus.setText("Canceled");
                layoutTop.setBackgroundColor(0xC8EB0300);
                view.setOnClickListener(new IntentToActivityOnClickListener(activity, AlternativeOptionsActivity.class, index));
                break;
        }
    }
}
