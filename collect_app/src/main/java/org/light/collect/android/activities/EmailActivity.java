package org.light.collect.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.light.collect.android.R;
import org.light.collect.android.utilities.DialogUtils;
import org.light.collect.android.utilities.SharedPreferenceUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends CollectAbstractActivity {

    View card;
    TextInputLayout txtInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        bindUI();
        txtInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        txtInput.getEditText().addTextChangedListener(null);
    }

    @OnClick(R.id.btnSave)
    public void saveEmail() {
        String email = txtInput.getEditText().getText().toString().trim();
        boolean isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (isValidEmail) {
            hideKeyboardInActivity(this);
            String msg = String.format("Saving email %s", email);
            DialogUtils.createActionDialog(this, "Review", msg)
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        SharedPreferenceUtils.savEmail(email);
                        startActivity(new Intent(EmailActivity.this, MainMenuActivity.class));
                        finish();
                    })
                    .setNegativeButton("Dismiss", null)
                    .show();
        } else {
            txtInput.setError("Invalid email!");
        }
    }


    private void bindUI() {
        card = findViewById(R.id.card);
        txtInput = findViewById(R.id.txtInput);
    }




    /**
     * Only works from an activity, using getActivity() does not work
     *
     * @param activity
     */
    public void hideKeyboardInActivity(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }

}
