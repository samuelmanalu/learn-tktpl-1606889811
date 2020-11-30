package com.tktpl.helloworld.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tktpl.helloworld.R;

public class StopwatchFragment extends Fragment {

    private static final String TAG = "StopwatchFragment";

    private TextView timer;

    private Button start, pause, reset;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;

    boolean isAppRunning = false;

    Handler handler;

    int Seconds, Minutes, MilliSeconds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        timer = (TextView) view.findViewById(R.id.tvMainText);
        start = (Button) view.findViewById(R.id.btStart);
        pause = (Button) view.findViewById(R.id.btPause);
        reset = (Button) view.findViewById(R.id.btReset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                Log.w(TAG, "Time Buffer: " + TimeBuff);
                Log.w(TAG, "StartTime Before: " + StartTime);
                isAppRunning = true;
                reset.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                isAppRunning = false;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;
                timer.setText("00 : 00 : 00");
            }
        });

        handler = new Handler();
        return view;
    }

    /**
     * Lab 3 - Stopwatch - Background Application Task
     * By Samuel - 1606889811
     */

    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            setTextTimer();
            handler.postDelayed(this, 0);
        }

    };

    private void setTextTimer() {
        Seconds = (int) (UpdateTime / 1000);
        Minutes = Seconds / 60;
        Seconds = Seconds % 60;
        MilliSeconds = (int) (UpdateTime % 1000);
        timer.setText("" + String.format("%02d", Minutes) + ":"
                + String.format("%02d", Seconds) + ":"
                + String.format("%02d", MilliSeconds));
    }
}
