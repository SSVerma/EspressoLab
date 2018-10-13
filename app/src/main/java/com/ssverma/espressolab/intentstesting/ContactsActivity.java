package com.ssverma.espressolab.intentstesting;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ssverma.espressolab.R;

public class ContactsActivity extends AppCompatActivity {

    public static final String EXTRA_PHONE = "extra_phone";

    public static void launchForResult(Activity activity, int reqCode) {
        Intent intent = new Intent(activity, ContactsActivity.class);
        activity.startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setResult(RESULT_OK, createPhoneNumberData("9461300000"));
        finish();
    }

    public static Intent createPhoneNumberData(String phone) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_PHONE, phone);
        return intent;
    }

}
