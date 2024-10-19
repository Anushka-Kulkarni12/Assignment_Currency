package com.example.assignment_convert_test_1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCon;
    TextView txtTextView;

    RadioButton rbCan, rbUsa, rbUk, rbChina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
    }

    private void initViews() {
        btnCon = findViewById(R.id.btnCon);
        txtTextView = findViewById(R.id.txtTextView);
    }

    private void initListener() {
        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Select Currency");

        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        RadioGroup radioGroup = dialogView.findViewById(R.id.rdGroup);
        rbCan = dialogView.findViewById(R.id.rbCan);
        rbUsa = dialogView.findViewById(R.id.rbUsa);
        rbUk = dialogView.findViewById(R.id.rbUk);
        rbChina = dialogView.findViewById(R.id.rbChina);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amountStr = txtTextView.getText().toString();

                // Ensure amount is entered
                if (amountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);
                double convertedAmount = 0;
                String result = "Converted to ";

                // Check which RadioButton is selected
                if (rbCan.isChecked()) {
                    convertedAmount = amount * 1.25;
                    result += "Canadian Dollar: " + convertedAmount;
                } else if (rbUsa.isChecked()) {
                    convertedAmount = amount * 1.0;
                    result += "USA Dollar: " + convertedAmount;
                } else if (rbUk.isChecked()) {
                    convertedAmount = amount * 0.75;
                    result += "UK Pound: " + convertedAmount;
                } else if (rbChina.isChecked()) {
                    convertedAmount = amount * 6.5;
                    result += "China Yuan: " + convertedAmount;
                } else {
                    result = "No currency selected";
                }

                txtTextView.setText(result);
            }
        });


        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
