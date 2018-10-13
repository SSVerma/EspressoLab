package com.ssverma.espressolab.custommatcher;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssverma.espressolab.R;

import java.util.Arrays;
import java.util.List;

public class CofferPreparationActivity extends AppCompatActivity {

    @VisibleForTesting
    public static final List<String> COFFEE_PREPARATIONS =
            Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew");

    @VisibleForTesting
    public static final String VALID_ENDING = "coffee";

    private TextView tvSuccess;
    private TextView tvFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffer_preparation);

        final EditText etChoice = findViewById(R.id.et_coffee);
        Button btnValidate = findViewById(R.id.btn_validate);
        tvSuccess = findViewById(R.id.tv_success);
        tvFail = findViewById(R.id.tv_fail);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResult(isValid(etChoice.getText().toString()));
            }
        });

    }

    private void displayResult(boolean isValid) {
        tvSuccess.setVisibility(isValid ? View.VISIBLE : View.GONE);
        tvFail.setVisibility(isValid ? View.GONE : View.VISIBLE);
    }

    private boolean isValid(String choice) {
        if (choice.toLowerCase().endsWith(VALID_ENDING)) {
            return true;
        }

        for (String preparation : COFFEE_PREPARATIONS) {
            if (preparation.equalsIgnoreCase(choice)) {
                return true;
            }
        }
        return false;
    }
}
