package com.tktpl.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EmailValidator mEmailValidator;

    private EditText resultTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTest = (EditText) findViewById(R.id.emailInput);

        mEmailValidator = new EmailValidator();
        resultTest.addTextChangedListener(mEmailValidator);
    }

    public void onSaveClick(View view) {
        if(!mEmailValidator.isValid()) {
            resultTest.setError("Invalid Email");
            Log.w(TAG,"Invalid Email");
            return;
        }
        Log.w(TAG, "Email Valid");
        String email = resultTest.getText().toString();
        Toast.makeText(this, "Email Valid", Toast.LENGTH_LONG).show();
    }
}