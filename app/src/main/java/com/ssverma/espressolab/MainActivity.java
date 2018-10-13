package com.ssverma.espressolab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvText = findViewById(R.id.tv_typed_text);
        final EditText etText = findViewById(R.id.et_text);
        Button btnShowText = findViewById(R.id.btn_show_text);
        Button btnStartActivity = findViewById(R.id.btn_start_activity);

        btnShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvText.setText(etText.getText().toString());
            }
        });

        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTextActivity.launch(MainActivity.this, etText.getText().toString());
            }
        });

    }
}
