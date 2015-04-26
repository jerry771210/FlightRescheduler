package edu.cmu.sv.flight.rescheduler.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public BoardingPassView(Activity activity, LayoutInflater inflater, BoardingPass boardingPass) {
        this.activity = activity;
        this.view = inflater.inflate(R.layout.boarding_pass, null);
        this.boardingPass = boardingPass;

        textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
        textViewDepart = (TextView) view.findViewById(R.id.textViewDeparture);
        textViewArrive = (TextView) view.findViewById(R.id.textViewArrival);
        textViewFlightNum = (TextView) view.findViewById(R.id.textViewFlightNumber);
        textViewTerminal = (TextView) view.findViewById(R.id.textViewTerminal);
        textViewGate = (TextView) view.findViewById(R.id.textViewGate);

        layoutTop = (RelativeLayout) view.findViewById(R.id.RelativeLayoutTop);
        configure();
    }

    public View getLayoutView() {
        return view;
    }

    private void configure() {
        switch (boardingPass.getStatus()) {
            case LANDED:  // Default layout is landed
                textViewStatus.setText("Landed");
                layoutTop.setBackgroundColor(0xC8003269);
                textViewDepart.setText("TPE");
                textViewArrive.setText("NRT");
                textViewFlightNum.setText("824");
                textViewTerminal.setText("T5");
                textViewGate.setText("G08");
                break;
            case ON_TIME:
                textViewStatus.setText("On Time");
                layoutTop.setBackgroundColor(0xC8176935);
                textViewDepart.setText("NRT");
                textViewArrive.setText("LAX");
                textViewFlightNum.setText("6");
                textViewTerminal.setText("T3");
                textViewGate.setText("G18");
                break;
            case DELAYED:
                textViewStatus.setText("Delayed");
                layoutTop.setBackgroundColor(0xC8EBB027);
                view.setOnClickListener(new IntentToActivityOnClickListener(activity, AlternativeOptionsActivity.class));
                textViewDepart.setText("LAX");
                textViewArrive.setText("NYC");
                textViewFlightNum.setText("667");
                textViewTerminal.setText("T6");
                textViewGate.setText("G11");
                break;
            case CANCELED:
                textViewStatus.setText("Canceled");
                layoutTop.setBackgroundColor(0xC8EB0300);
                view.setOnClickListener(new IntentToActivityOnClickListener(activity, AlternativeOptionsActivity.class));
                textViewDepart.setText("NYC");
                textViewArrive.setText("BOS");
                textViewFlightNum.setText("2180");
                textViewTerminal.setText("T8");
                textViewGate.setText("G21");
                break;
        }
    }
}
