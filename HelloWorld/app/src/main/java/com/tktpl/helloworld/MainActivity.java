package com.tktpl.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EmailValidator mEmailValidator;

    private EditText resultTest;

    private TextView timer;

    private Button start, pause, reset, exit;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;

    boolean isAppRunning = false;

    Handler handler;

    int Seconds, Minutes, MilliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        resultTest = (EditText) findViewById(R.id.emailInput);

//        mEmailValidator = new EmailValidator();
//        resultTest.addTextChangedListener(mEmailValidator);

        timer = (TextView) findViewById(R.id.tvMainText);
        start = (Button) findViewById(R.id.btStart);
        pause = (Button) findViewById(R.id.btPause);
        reset = (Button) findViewById(R.id.btReset);
        exit = (Button) findViewById(R.id.btExit);

        handler = new Handler();
    }

    /**
     * Lab 2 - Email Chacker
     * By Samuel - 1606889811
     */

//    public void onSaveClick(View view) {
//        if(!mEmailValidator.isValid()) {
//            resultTest.setError("Invalid Email");
//            Log.w(TAG,"Invalid Email");
//            return;
//        }
//        Log.w(TAG, "Email Valid");
//        String email = resultTest.getText().toString();
//        Toast.makeText(this, "Email Valid", Toast.LENGTH_LONG).show();
//    }

    /**
     * Lab 3 - Stopwatch - Background Application Task
     * By Samuel - 1606889811
     */

//    @Override
//    protected void onStop() {
//        super.onStop();
//        TimeBuff = UpdateTime;
//        Log.w(TAG, "Current TimeBuff: " + TimeBuff);
//        handler.postDelayed(runnable, 0);
//    }

    // Exit application when Exit Button clicked by User
    public void onExitButtonClicked(View view) {
        finish();
        Log.w(TAG, "Application Exited");
        System.exit(0);
    }

    // Start timer when start button clicked by User
    public void onStartClicked(View view) {
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
        Log.w(TAG, "Time Buffer: " + TimeBuff);
        Log.w(TAG, "StartTime Before: " + StartTime);
        isAppRunning = true;
        reset.setEnabled(false);
    }

    // Stop timer when stop button clicked by User
    public void onStopClicked(View view) {
        TimeBuff += MillisecondTime;
        handler.removeCallbacks(runnable);
        reset.setEnabled(true);
        isAppRunning = false;
    }

    // Restart timer when timer button clicked by User
    public void onRestartClicked(View view) {
        MillisecondTime = 0L;
        StartTime = 0L;
        TimeBuff = 0L;
        UpdateTime = 0L;
        Seconds = 0;
        Minutes = 0;
        MilliSeconds = 0;
        timer.setText("00 : 00 : 00");
    }

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

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("timeBuff", MillisecondTime);
        editor.putBoolean("isAppRunning", isAppRunning);
        editor.putLong("startTime", StartTime);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences savedInstanceState = getSharedPreferences("data", MODE_PRIVATE);
        TimeBuff = savedInstanceState.getLong("timeBuff", 0L);
        StartTime = savedInstanceState.getLong("startTime", 0L);
        Log.w(TAG, "Timebuff After: " + TimeBuff);
        Log.w(TAG, "Updateime: " + UpdateTime);
        Log.w(TAG, "Milliseconds: " + MillisecondTime);
        Log.w(TAG, "StartTime After: " + StartTime);
        isAppRunning = savedInstanceState.getBoolean("isAppRunning", false);
        if (isAppRunning) {
            handler.postDelayed(runnable, 0);
        } else {
            setTextTimer();
            handler.removeCallbacks(runnable);
        }
    }
}