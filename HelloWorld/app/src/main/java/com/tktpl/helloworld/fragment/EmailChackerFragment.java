package com.tktpl.helloworld.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tktpl.helloworld.R;
import com.tktpl.helloworld.util.EmailValidator;

public class EmailChackerFragment extends Fragment {

    private static final String TAG = "EmailCheckerFragment";

    private EditText resultTest;

    private Button checkButton;

    private EmailValidator mEmailValidator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        resultTest = (EditText) view.findViewById(R.id.emailInput);
        mEmailValidator = new EmailValidator();
        resultTest.addTextChangedListener(mEmailValidator);

        checkButton = (Button) view.findViewById(R.id.saveButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mEmailValidator.isValid()) {
                    resultTest.setError("Invalid Email");
                    Log.w(TAG,"Invalid Email");
                    return;
                }
                Log.w(TAG, "Email Valid");
                String email = resultTest.getText().toString();
                Toast.makeText(getActivity(), "Email Valid", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
