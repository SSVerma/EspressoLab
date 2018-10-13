package com.ssverma.espressolab.intentstesting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssverma.espressolab.R;

public class CallActivity extends AppCompatActivity {

    private static final int REQ_CODE_PICK_CONTACT = 1916;
    private static final int RC_CALL_PERMISSION = 101;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        etPhone = findViewById(R.id.et_phone);
        Button btnCall = findViewById(R.id.btn_call);
        Button btnPickContact = findViewById(R.id.btn_pick_contact);

        btnPickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsActivity.launchForResult(CallActivity.this, REQ_CODE_PICK_CONTACT);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                if (hasCallPermission()) {
                    fireCallIntent(phone);
                } else {
                    Toast.makeText(CallActivity.this, "Please grant call permission", Toast.LENGTH_SHORT).show();
                    requestCallPermission();
                }
            }
        });
    }

    private void requestCallPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, RC_CALL_PERMISSION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_PICK_CONTACT:
                if (resultCode == RESULT_OK) {
                    etPhone.setText(data.getStringExtra(ContactsActivity.EXTRA_PHONE));
                }
                break;
        }
    }

    private void fireCallIntent(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    private boolean hasCallPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }
}
