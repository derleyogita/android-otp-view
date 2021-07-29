package com.example.customotpview;

import android.app.Service;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * @author yogitad
 * @since 28-07-2021
 * This custom class is used to handle OTP view
 */
public class OTPView extends LinearLayout implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher, View.OnClickListener {

    /**
     * Context of the application
     */
    private final Context context;

    /**
     * First mPIN EditText
     */
    private EditText editTextMPINFirstDigit;

    /**
     * Second mPIN EditText
     */
    private EditText editTextMPINSecondDigit;

    /**
     * Third mPIN EditText
     */
    private EditText editTextMPINThirdDigit;

    /**
     * Fourth mPIN EditText
     */
    private EditText editTextMPINForthDigit;

    /**
     * Fifth mPIN EditText
     */
    private EditText editTextMPINFifthDigit;

    /**
     * Sixth mPIN EditText
     */
    private EditText editTextMPINSixthDigit;

    /**
     * Button hide/show view
     */
    private AppCompatButton btnShowHide;

    /**
     * OTP digit at specified position
     */
    private String otpNumber = "";

    /**
     * Hidden edit text which will be used to provide the focus to show the input keyboard.
     */
    private EditText editTextMPINHidden;

    /**
     * Listener
     */
    private OTPView.OnTypingFinishListener onTypingFinishListener;

    /**
     * Flag used to show OTP- by default it is false
     */
    private boolean showOTP = false;


    /**
     * Constructor to pass the context of the application
     *
     * @param context Application context
     */
    public OTPView(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Constructor to pass the context and attribute.
     *
     * @param context Application context
     * @param attrs   AttributeSet instance
     */
    public OTPView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        setPINListeners();
    }

    /**
     * Sets focus on a specific EditText field.
     *
     * @param editText EditText to set focus on
     */
    public static void setFocus(EditText editText) {
        if (editText == null)
            return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    /**
     * This method is used to set the OnTypingFinishListener listener
     *
     * @param onTypingFinishListener OnTypingFinishListener reference
     */
    public void setTypingFinishListener(OTPView.OnTypingFinishListener onTypingFinishListener) {
        this.onTypingFinishListener = onTypingFinishListener;
    }


    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /**
     * Initialize EditText fields.
     */
    private void init() {
        LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mInflater != null) {
            mInflater.inflate(R.layout.otp_view_layout, this);
            editTextMPINFirstDigit = findViewById(R.id.etMPINView1);
            editTextMPINSecondDigit = findViewById(R.id.etMPINView2);
            editTextMPINThirdDigit = findViewById(R.id.etMPINView3);
            editTextMPINForthDigit = findViewById(R.id.etMPINView4);
            editTextMPINFifthDigit = findViewById(R.id.etMPINView5);
            editTextMPINSixthDigit = findViewById(R.id.etMPINView6);
            editTextMPINHidden = findViewById(R.id.etMPINHiddenView);

            btnShowHide = findViewById(R.id.btnShowHide);
            ((AppCompatButton) findViewById(R.id.btnShowHide)).setText(context.getString(R.string.str_show));

            //hide OTP
            showOTP = false;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        final int mId = view.getId();
        switch (mId) {
            case R.id.etMPINView1:

            case R.id.etMPINView6:

            case R.id.etMPINView5:

            case R.id.etMPINView4:

            case R.id.etMPINView3:

            case R.id.etMPINView2:
                if (hasFocus) {
                    setFocus(editTextMPINHidden);
                    showSoftKeyboard(editTextMPINHidden);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            final int mId = v.getId();
            if (mId == R.id.etMPINHiddenView) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (editTextMPINHidden.getText().length() == 6) {
                        editTextMPINSixthDigit.getText().clear();
                        editTextMPINSixthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        DrawableCompat.setTint(editTextMPINSixthDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

                    } else if (editTextMPINHidden.getText().length() == 5) {
                        editTextMPINFifthDigit.getText().clear();
                        editTextMPINFifthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        DrawableCompat.setTint(editTextMPINFifthDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

                    } else if (editTextMPINHidden.getText().length() == 4) {
                        editTextMPINForthDigit.setText("");
                        editTextMPINForthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        DrawableCompat.setTint(editTextMPINForthDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

                    } else if (editTextMPINHidden.getText().length() == 3) {
                        editTextMPINThirdDigit.getText().clear();
                        editTextMPINThirdDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        DrawableCompat.setTint(editTextMPINThirdDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

                    } else if (editTextMPINHidden.getText().length() == 2) {
                        editTextMPINSecondDigit.getText().clear();
                        editTextMPINSecondDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        DrawableCompat.setTint(editTextMPINSecondDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

                    } else if (editTextMPINHidden.getText().length() == 1) {
                        editTextMPINFirstDigit.getText().clear();
                        editTextMPINFirstDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        DrawableCompat.setTint(editTextMPINFirstDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

                    }
                    if (editTextMPINHidden.length() > 0) {
                        editTextMPINHidden.setText(editTextMPINHidden.getText().subSequence(0, editTextMPINHidden.length() - 1));
                    }
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (charSequence.length() == 0) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                editTextMPINFirstDigit.setText("");
            } else {
                editTextMPINFirstDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

        }

        if (charSequence.length() == 1) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                String firstDigit = String.valueOf(charSequence.charAt(0));
                editTextMPINFirstDigit.setText(firstDigit);
            } else {
                editTextMPINFirstDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            }
            DrawableCompat.setTint(editTextMPINFirstDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (charSequence.length() == 2) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                String secondDigit = String.valueOf(charSequence.charAt(1));
                otpNumber = charSequence.toString();
                editTextMPINSecondDigit.setText(secondDigit);

            } else {
                editTextMPINSecondDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            }
            DrawableCompat.setTint(editTextMPINSecondDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (charSequence.length() == 3) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                String thirdDigit = String.valueOf(charSequence.charAt(2));
                otpNumber = charSequence.toString();
                editTextMPINThirdDigit.setText(thirdDigit);

            } else {
                editTextMPINThirdDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            }
            DrawableCompat.setTint(editTextMPINThirdDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (charSequence.length() == 4) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                String fourthDigit = String.valueOf(charSequence.charAt(3));
                otpNumber = charSequence.toString();
                editTextMPINForthDigit.setText(fourthDigit);

            } else {
                editTextMPINForthDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            }
            DrawableCompat.setTint(editTextMPINForthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (charSequence.length() == 5) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                String fifthDigit = String.valueOf(charSequence.charAt(4));
                otpNumber = charSequence.toString();
                editTextMPINFifthDigit.setText(fifthDigit);
            } else {
                editTextMPINFifthDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            }
            DrawableCompat.setTint(editTextMPINFifthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (charSequence.length() == 6) {
            otpNumber = charSequence.toString();
            if (showOTP) {
                String sixthDigit = String.valueOf(charSequence.charAt(5));
                otpNumber = charSequence.toString();
                editTextMPINSixthDigit.setText(sixthDigit);
            } else {
                editTextMPINSixthDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            }
            DrawableCompat.setTint(editTextMPINSixthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

            hideSoftKeyboard(editTextMPINSixthDigit);
            onTypingFinishListener.onSuccessTyping(charSequence.toString());
        }
    }

    /**
     * Method used to hide entered OTP
     */
    private void hideOTP() {
        showOTP = false;
        if (editTextMPINFirstDigit.getText().length() > 0) {
            editTextMPINFirstDigit.getText().clear();
            editTextMPINFirstDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            DrawableCompat.setTint(editTextMPINFirstDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (editTextMPINSecondDigit.getText().length() > 0) {
            editTextMPINSecondDigit.getText().clear();
            editTextMPINSecondDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            DrawableCompat.setTint(editTextMPINSecondDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (editTextMPINThirdDigit.getText().length() > 0) {
            editTextMPINThirdDigit.getText().clear();
            editTextMPINThirdDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            DrawableCompat.setTint(editTextMPINThirdDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (editTextMPINForthDigit.getText().length() > 0) {
            editTextMPINForthDigit.getText().clear();
            editTextMPINForthDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            DrawableCompat.setTint(editTextMPINForthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (editTextMPINFifthDigit.getText().length() > 0) {
            editTextMPINFifthDigit.getText().clear();
            editTextMPINFifthDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            DrawableCompat.setTint(editTextMPINFifthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
        if (editTextMPINSixthDigit.getText().length() > 0) {
            editTextMPINSixthDigit.getText().clear();
            editTextMPINSixthDigit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bg_login_circle_m_pin, 0, 0);
            DrawableCompat.setTint(editTextMPINSixthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));

        }
    }

    /**
     * Method used to show Entered OTP
     *
     * @param charSequence - Entered character sequence
     */
    private void showOTP(CharSequence charSequence) {
        showOTP = true;
        for (int i = 0; i < charSequence.length(); i++) {
            if (i == 0) {
                String firstDigit = String.valueOf(charSequence.charAt(0));
                otpNumber = charSequence.toString();
                editTextMPINFirstDigit.setText(firstDigit);
                editTextMPINFirstDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                DrawableCompat.setTint(editTextMPINFirstDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
            if (i == 1) {
                String secondDigit = String.valueOf(charSequence.charAt(1));
                otpNumber = charSequence.toString();
                editTextMPINSecondDigit.setText(secondDigit);
                editTextMPINSecondDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                DrawableCompat.setTint(editTextMPINSecondDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
            if (i == 2) {
                String thirdDigit = String.valueOf(charSequence.charAt(2));
                otpNumber = charSequence.toString();
                editTextMPINThirdDigit.setText(thirdDigit);
                editTextMPINThirdDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                DrawableCompat.setTint(editTextMPINThirdDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
            if (i == 3) {
                String fourthDigit = String.valueOf(charSequence.charAt(3));
                otpNumber = charSequence.toString();
                editTextMPINForthDigit.setText(fourthDigit);
                editTextMPINForthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                DrawableCompat.setTint(editTextMPINForthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
            if (i == 4) {
                String fifthDigit = String.valueOf(charSequence.charAt(4));
                otpNumber = charSequence.toString();
                editTextMPINFifthDigit.setText(fifthDigit);
                editTextMPINFifthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                DrawableCompat.setTint(editTextMPINFifthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
            if (i == 5) {
                String sixthDigit = String.valueOf(charSequence.charAt(5));
                otpNumber = charSequence.toString();
                editTextMPINSixthDigit.setText(sixthDigit);
                editTextMPINSixthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                DrawableCompat.setTint(editTextMPINSixthDigit.getBackground(), ContextCompat.getColor(context, R.color.colorPrimaryDark));
            }
        }
    }


    /**
     * This method sets listeners for EditText fields.
     */
    private void setPINListeners() {
        editTextMPINHidden.addTextChangedListener(this);

        editTextMPINFirstDigit.setOnFocusChangeListener(this);
        editTextMPINSecondDigit.setOnFocusChangeListener(this);
        editTextMPINThirdDigit.setOnFocusChangeListener(this);
        editTextMPINForthDigit.setOnFocusChangeListener(this);
        editTextMPINFifthDigit.setOnFocusChangeListener(this);
        editTextMPINSixthDigit.setOnFocusChangeListener(this);

        editTextMPINFirstDigit.setOnKeyListener(this);
        editTextMPINSecondDigit.setOnKeyListener(this);
        editTextMPINThirdDigit.setOnKeyListener(this);
        editTextMPINForthDigit.setOnKeyListener(this);
        editTextMPINFifthDigit.setOnKeyListener(this);
        editTextMPINSixthDigit.setOnKeyListener(this);
        editTextMPINHidden.setOnKeyListener(this);

        btnShowHide.setOnClickListener(this);
    }

    /**
     * Shows soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public void showSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        mInputMethodManager.showSoftInput(editText, 0);
    }

    /**
     * Hides soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public void hideSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnShowHide) {
            if (btnShowHide.getText().toString().equalsIgnoreCase(context.getString(R.string.str_show))) {
                btnShowHide.setText(context.getString(R.string.str_hide));
                CharSequence cs = otpNumber;
                showOTP(cs);
            } else {
                btnShowHide.setText(context.getString(R.string.str_show));
                hideOTP();
            }
        }
    }

    /**
     * This method is used to reset the all the values of mPIN
     */
    public void resetValues() {
        editTextMPINFirstDigit.setText("");
        editTextMPINSecondDigit.setText("");
        editTextMPINThirdDigit.setText("");
        editTextMPINForthDigit.setText("");
        editTextMPINFifthDigit.setText("");
        editTextMPINSixthDigit.setText("");
        editTextMPINHidden.setText("");

        DrawableCompat.setTint(editTextMPINFirstDigit.getBackground(), ContextCompat.getColor(context, R.color.black));
        DrawableCompat.setTint(editTextMPINSecondDigit.getBackground(), ContextCompat.getColor(context, R.color.black));
        DrawableCompat.setTint(editTextMPINThirdDigit.getBackground(), ContextCompat.getColor(context, R.color.black));
        DrawableCompat.setTint(editTextMPINForthDigit.getBackground(), ContextCompat.getColor(context, R.color.black));
        DrawableCompat.setTint(editTextMPINFifthDigit.getBackground(), ContextCompat.getColor(context, R.color.black));
        DrawableCompat.setTint(editTextMPINSixthDigit.getBackground(), ContextCompat.getColor(context, R.color.black));

        editTextMPINFirstDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        editTextMPINSecondDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        editTextMPINThirdDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        editTextMPINForthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        editTextMPINFifthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        editTextMPINSixthDigit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

    }

    /**
     * Typing finish listener interface
     */
    public interface OnTypingFinishListener {
        /**
         * This method will be called when all the mOTP edit text is filled.
         *
         * @param mOTP mOTP entered by user
         */
        void onSuccessTyping(String mOTP);
    }

}

