package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView displayInput;
    private TextView displayResult;
    private String currentNumber = "";
    private double firstOperand = 0;
    private char operator;
//    TextView result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayInput = findViewById(R.id.textView);
        displayResult = findViewById(R.id.textView2);

        displayInput.setText("");
        displayResult.setText("");
        // Set OnClickListener for number buttons
        int[] numberButtonIds = {R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four,
                R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine};

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNumber += ((Button) v).getText().toString();
                    displayInput.append(currentNumber);
                }
            });
        }

        // Set OnClickListener for operator buttons
        int[] operatorButtonIds = {R.id.plus, R.id.minus, R.id.asterisk, R.id.slash,R.id.remainder};
        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!currentNumber.isEmpty()) {
                        firstOperand = Double.parseDouble(currentNumber);
                        displayInput.append(((Button) v).getText().toString());
                        operator = ((Button) v).getText().toString().charAt(0);
                        currentNumber = "";
                    }
                }
            });
        }

        // Set OnClickListener for equals button
        Button btnEquals = findViewById(R.id.equal);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentNumber);
                    double result = calculate(firstOperand, secondOperand, operator);
                    displayResult.append(String.valueOf(result));
                    currentNumber = "";
                }
            }
        });

        // Set OnClickListener for clear button
        Button btnClear = findViewById(R.id.del);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber = "";
                firstOperand = 0;
                displayInput.setText("");
                displayResult.setText("");
            }
        });
    }

    private double calculate(double num1, double num2, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    displayResult.setText("Error: Division by zero");
                }
                break;
            case '%':
                result = num1 % num2;
                break;
        }
        return result;
    }
}