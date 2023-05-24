package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button add, subtract, multiply, divide, enter,
            one, two, three, four, five, six, seven, eight, nine, zero;

    TextView answer, equation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        equation = findViewById(R.id.equation);
        answer = findViewById(R.id.answer);
        initializeButton();
    }

    /**
     * intializes all the buttons
     */
    public void initializeButton() {
        enter = findViewById(R.id.enter);
        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);

        enter.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
    }

    /**
     * Calculates a string equation in order of operations. Assumes the equation is
     * typed in correctly (not +3-4*)
     * @param input the equation in string format
     * @return the correct answer as decimal
     */
    public static double Calculate(String input) {
        String strArray[] = input.split(" ");
        ArrayList<String> operations = new ArrayList<>();
        for(int i = 0; i < strArray.length; i++) {
            if(strArray[i].equals("x") || strArray[i].equals("/")) {
                String num; //number that if the answer
                if(strArray[i].equals("x")) { //if multiplying
                    num = Double.parseDouble(operations.get(operations.size()-1)) * Double.parseDouble(strArray[i+1]) + "";
                } else { //if dividing
                    num = Double.parseDouble(operations.get(operations.size()-1)) /  Double.parseDouble(strArray[i+1]) + "";
                    System.out.println(num);
                }
                i++; //skip the next number
                operations.remove(operations.size()-1);
                operations.add(num);
            }
            else { //otherwise simply add the operation/number
                operations.add(strArray[i]);
            }
        }
        System.out.println(operations);
        double num = Integer.parseInt(operations.get(0));
        //calculates the answer
        //loop through the string but only odds because that is what the operations are
        for(int i = 1; i < operations.size(); i+=2) {
            if(operations.get(i).equals("+")) { //if is addition
                num += Double.parseDouble(operations.get(i+1));
            } else { //if is subtraction
                num -= Double.parseDouble(operations.get(i+1));
            }
        }
        return num;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String equationStr = equation.getText().toString();

        if(buttonText.equals("=")) {
            double answerStr = Calculate(equation.getText().toString());
            answer.setText(answerStr + "");
        } else {
            try {
                Integer.parseInt(buttonText);
                equation.setText(equationStr + buttonText);
            } catch (Exception e) {
                equation.setText(equationStr + " " + buttonText + " ");
            }
        }
    }
}