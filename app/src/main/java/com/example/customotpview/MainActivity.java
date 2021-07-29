package com.example.customotpview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * @author yogitad
 * @since 28-07-2021
 */
public class MainActivity extends AppCompatActivity implements OTPView.OnTypingFinishListener {

    /**
     * OTP view reference
     */
    private OTPView OTPView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }

    /**
     * This method is used to bind all layout variables
     */
    public void setupViews() {
        //OTP view
        OTPView = findViewById(R.id.mvwCreateOTP);
        OTPView.setTypingFinishListener(this);
        OTPView.resetValues();
    }

    @Override
    public void onSuccessTyping(String mOTP) {

    }

    @Override
    public void onStop() {
        super.onStop();
        if (OTPView != null) {
            OTPView.resetValues();
        }
    }
}