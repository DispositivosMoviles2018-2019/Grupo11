package com.example.ludl.appperiodictask;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView mClock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClock = new TextView(this);
        setContentView(mClock);
    }

    private Handler mHandler = new Handler();
    private Runnable timerTask = new Runnable() {
        @Override
        public void run() {
            Calendar now = Calendar.getInstance();
            mClock.setText(String.format("%02d:%02d:%02d",
                    now.get(Calendar.HOUR),
                    now.get(Calendar.MINUTE),
                    now.get(Calendar.SECOND)) );
            //Schedule the next update
            mHandler.postDelayed(timerTask,1000);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.post(timerTask);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(timerTask);
    }
}