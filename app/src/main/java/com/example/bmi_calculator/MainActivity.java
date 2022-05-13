package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText weight, feet, inches;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        weight = findViewById(R.id.editText_weight);
        feet = findViewById(R.id.editText_feet);
        inches = findViewById(R.id.editText_inches);

        message = findViewById(R.id.textView_message);

        message.setText("hello");

        //Log.d("demo", this.generateBMIMessage(32));
    }

    @Override
    public void onClick(View v) {

        String textEdit_weight = weight.getText().toString();
        String textEdit_feet = feet.getText().toString();
        String textEdit_inches = inches.getText().toString();

        if (textEdit_weight.trim().equals("") || textEdit_feet.trim().equals("") || textEdit_inches.trim().equals("")){
            message.setText("Click on Calculate BMI to get your BMI");
            Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_LONG).show();
        }

        else {
            Float weight_in_lbs = Float.valueOf(textEdit_weight);
            Float feet_in_ft = Float.valueOf(textEdit_feet);
            Float inches_in_in = Float.valueOf(textEdit_inches);

            inches_in_in += feet_in_ft*12;

            float bmi = 703*(weight_in_lbs/(inches_in_in * inches_in_in));
            String BMI = String.valueOf(bmi);

            String bmiStatus = "";

            if (bmi <18.5) {
                bmiStatus = "Underweight";
            }
            if (bmi <24.9) {
                bmiStatus = "Normal weight";
            }
            if (bmi <29.9) {
                bmiStatus = "Overweight";
            }
            else {
                bmiStatus = "Obese";
            }

            String final_message = String.format("Your BMI: %.1f \nYou are " + bmiStatus, bmi);

            message.setText(final_message);

            Toast.makeText(this, "BMI Calculated", Toast.LENGTH_LONG).show();

        }

    }

    // from height in ft, in get total inches
    public float getInches(float inches, float feet) {
        return feet * 12 + inches;
    }

    // from lbs and height, get bmi
    public float getBMI(float pounds, float inches) {
        return 703*(pounds/inches * inches);
    }

    // from BMI get BMI Status
    public String getBMIStatus(float bmi) {
        String bmiMessage = "";

        if (bmi <18.5) {
            bmiMessage = "Underweight";
        }
        if (bmi <24.9) {
            bmiMessage = "Normal weight";
        }
        if (bmi <29.9) {
            bmiMessage = "Overweight";
        }
        else {
            bmiMessage = "Obese";
        }

        return bmiMessage;

    }

    // from a given BMI get the message that displays bmi
    // and displays bmi status
    public String generateBMIMessage(float bmi) {
        return String.format("Your BMI: %1d \n You are" + this.getBMIStatus(bmi), bmi);
    }


}