package com.ssverma.espressolab.idlingresources;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssverma.espressolab.R;

public class IdlingResSampleActivity extends AppCompatActivity {

    private CountingIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idling_res_sample);

        final TextView tvText = findViewById(R.id.tv_text);
        final EditText etText = findViewById(R.id.et_text);
        Button btnProcess = findViewById(R.id.btn_process_message);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvText.setText("Processing...");

                MessageProcessor.processMessage(etText.getText().toString(),
                        idlingResource, new MessageProcessor.MessageProcessListener() {
                            @Override
                            public void onProcessed(String text) {
                                tvText.setText(text);
                            }
                        });

            }
        });

    }

    @VisibleForTesting
    public CountingIdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new CountingIdlingResource("Test");
        }
        return idlingResource;
    }

}
