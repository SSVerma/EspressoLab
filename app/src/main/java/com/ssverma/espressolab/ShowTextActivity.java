package com.ssverma.espressolab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTextActivity extends AppCompatActivity {

    private static final String EXTRA_PASSED_TEXT = "extra_passed_text";

    public static void launch(Context context, String text) {
        Intent intent = new Intent(context, ShowTextActivity.class);
        intent.putExtra(EXTRA_PASSED_TEXT, text);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        TextView tvText = findViewById(R.id.tv_text);
        tvText.setText(getIntent().getStringExtra(EXTRA_PASSED_TEXT));
    }
}
