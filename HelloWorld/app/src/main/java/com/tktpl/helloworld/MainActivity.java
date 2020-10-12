package com.tktpl.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

    Button bvStopwatch, bvEmailChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bvStopwatch = (Button) findViewById(R.id.bvStopwatch);
        bvEmailChecker = (Button) findViewById(R.id.bvEmail);
    }

    public void stopwatchButtonClicked(View view) {
        Log.w(TAG, "Stopwatch Clicked");
        loadFragment(new StopwatchFragment());
    }

    public void emailButtonClicked(View view) {
        Log.w(TAG, "Email Clicked");
        loadFragment(new EmailChackerFragment());
    }

    private void loadFragment(Fragment fragment) {
        Log.w(TAG, "Trying to load fragment");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragment1, fragment);
        fragmentTransaction.commit();
    }


}