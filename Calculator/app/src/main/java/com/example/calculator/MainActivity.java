package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements FragmentButtonUI.ClickedTextListener {
    @Override
    public void clickedTextSet(String text) {
        String setText = editText.getText().toString() + text;
        editText.setText(setText);
    }
    @Override
    public void clickedClearSet() {
        editText.setText("");
        postfixExprList.clear();
    }
    @Override
    public void clickedBracketSet() {
        String text = editText.getText().toString();
        int indexOftarget = editText.length() - 1;
        String target = "";
        if (text.length() >= 1) {
            target = Character.toString(text.charAt(indexOftarget));
            if (target.equals("(")) {
                stackBracket.push(getResources().getString(R.string.bracketopen));
                clickedTextSet(getResources().getString(R.string.bracketopen));
            } else if (target.equals(")")) {
                if (stackBracket.empty()) {
                    clickedTextSet(getResources().getString(R.string.multiply));
                    clickedTextSet(getResources().getString(R.string.bracketopen));
                    stackBracket.push(getResources().getString(R.string.bracketopen));
                } else {
                    stackBracket.pop();
                    clickedTextSet(getResources().getString(R.string.bracketclose));
                }
            } else {
                for (Op o : Op.values()) {
                    if (o.getName().equals(target)) {
                        stackBracket.push(getResources().getString(R.string.bracketopen));
                        clickedTextSet(getResources().getString(R.string.bracketopen));
                        break;
                    }
                }
                for (Num n : Num.values()) {
                    if (n.getName().equals(target)) {
                        if (stackBracket.empty()) {
                            clickedTextSet(getResources().getString(R.string.multiply));
                            clickedTextSet(getResources().getString(R.string.bracketopen));
                            stackBracket.push(getResources().getString(R.string.bracketopen));
                            break;
                        }
                        else {
                            stackBracket.pop();
                            clickedTextSet(getResources().getString(R.string.bracketclose));
                            break;
                        }
                    }
                }
            }
        } else {
            stackBracket.push(getResources().getString(R.string.bracketopen));
            clickedTextSet(getResources().getString(R.string.bracketopen));
        }
    }
    @Override
    public void clickedEqualSet() {
        String infixExpr = editText.getText().toString();
        toPostfixExpression(infixExpr, 0);
        Object result = postfixExpressionCalculate(postfixExprList);
        if (result instanceof Double) {
            editText.setText(String.format("%f", result));
        } else {
            editText.setText(String.format("%d", result));
        }
        postfixExprList.clear();

    }
    public void toPostfixExpression (String infixExpr, int start) {
        if (start >= infixExpr.length()) {
            while (!stackUnit.empty()) {
                postfixExprList.add(stackUnit.pop());
            }
            return;
        }
        String target = Character.toString(infixExpr.charAt(start++));
        if (target.equals("(")) {
            stackUnit.push(target);
        } else if (target.equals(")")) {
            while (true) {
                postfixExprList.add(stackUnit.pop());
                if (stackUnit.empty()) break;
                if (stackUnit.peek().equals("(")) {
                    stackUnit.pop();
                    break;
                }
            }
        } else if (target.equals("+") || target.equals("-")) {
            stackUnit.push(target);
        } else if (target.equals("X") || target.equals("%") || target.equals("/")) {
            if (stackUnit.empty()) {
                stackUnit.push(target);
            } else {
                if (stackUnit.peek().equals("+") || stackUnit.peek().equals("-")) {
                    while (!stackUnit.empty()) {
                        if (stackUnit.peek().equals("(")) {
                            stackUnit.pop();
                            break;
                        }
                        postfixExprList.add(stackUnit.pop());
                    }
                    stackUnit.push(target);
                } else {
                    stackUnit.push(target);
                }
            }
        } else if (target.equals(".")) {
            num += target;
        } else {
            num += target;
            double operand;
            if (start != infixExpr.length()) {
                String targetNext = Character.toString(infixExpr.charAt(start));
                for (Op o : Op.values()) {
                    if (o.getName().equals(targetNext)) {
                            operand = Double.parseDouble(num);
                            num = "";
                            postfixExprList.add(operand);
                            break;
                        }
                    }
            } else {
                    operand = Double.parseDouble(num);
                    num = "";
                    postfixExprList.add(operand);
            }
        }
        toPostfixExpression(infixExpr, start);
    }
    public Object postfixExpressionCalculate (ArrayList<Object> exprList) {
        Stack<Double> operandStack = new Stack<>();
        for (Object o : exprList) {
            Log.d("calculate", "postfixExpressionCalculate: "+ exprList);
            double operand_1, operand_2;
            if (o instanceof Double) {
                operandStack.push((Double) o);
            } else {
                if (o.equals("+")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    operandStack.push(operand_1 + operand_2);
                } else if (o.equals("-")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    operandStack.push(operand_1 - operand_2);
                } else if (o.equals("X")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    operandStack.push(operand_1 * operand_2);
                } else if (o.equals("/")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    operandStack.push(operand_1 / operand_2);
                } else if (o.equals("%")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    operandStack.push(operand_1 % operand_2);
                }
            }
        }
        return operandStack.pop();
    }
    ToggleButton buttonHistory;
    FragmentManager fragmentManager;
    EditText editText;
    Button buttonRemove;
    Stack<String> stackBracket = new Stack<>();
    Stack<String> stackUnit = new Stack<>();
    ArrayList<Object> postfixExprList = new ArrayList<>();
    String num = "";
    Boolean isFloat = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.text);
        buttonRemove = findViewById(R.id.remove);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                String removedString = "";
                int indexOfString = text.length() - 1;
                if (!editText.getText().toString().equals("")) {
                    for (int i = 0; i < indexOfString; i++) {
                        removedString += Character.toString(text.charAt(i));
                    }
                    editText.setText(removedString);
                }
            }
        });
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new FragmentButtonUI());
        fragmentTransaction.commit();
        buttonHistory = findViewById(R.id.history);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonHistory.isChecked()) {
                    buttonHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.calculator, null));
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new FragmentHistory());
                    fragmentTransaction.commit();
                }
                else {
                    buttonHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.history, null));
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new FragmentButtonUI());
                    fragmentTransaction.commit();
                }
            }
        });
    }
    public enum Num {
        ONE("1"),TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),SIX("6"),SEVEN("7"),EIGHT("8"),NINE("9"),ZERO("0");
        final private String number;

        Num(String number) {
            this.number = number;
        }
        public String getName() {
            return number;
        }
    }
    public enum Op {
        PLUS("+"),MINUS("-"),MULTIPLY("X"),DIVIDE("/"),REMAIN("%");
        final private String op;

        Op(String op) {
            this.op = op;
        }
        public String getName() {
            return op;
        }
    }
}
