package edu.cmu.sv.flight.rescheduler.ui.listener;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Created by moumoutsay on 4/9/15.
 */
public class IntentToActivityOnClickListener implements View.OnClickListener {
    private static final String LOG_TAG = IntentToActivityOnClickListener.class.getSimpleName();

    private Activity act;
    private Class toActivityClass;
    private boolean clearTop;
    private Integer index;

    public IntentToActivityOnClickListener(Activity act, Class toActivityClass) {
        this.act = act;
        this.toActivityClass = toActivityClass;
    }

    public IntentToActivityOnClickListener(Activity act, Class toActivityClass, int index) {
        this.act = act;
        this.toActivityClass = toActivityClass;
        this.index = new Integer(index);
    }


    public IntentToActivityOnClickListener(Activity act, Class toActivityClass, boolean clearTop) {
        this(act, toActivityClass);
        this.clearTop = clearTop;
    }

    @Override
    public void onClick(View v) {
        if (toActivityClass == null) {
            Log.d (LOG_TAG, "The to go activity is null");
        } else {
            Intent intent = new Intent(act, toActivityClass);
            if(clearTop) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            if (index != null) {
                intent.putExtra("indexOfBoardingPass", index.intValue());
            }
            act.startActivity(intent);
        }
    }
}
