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
        if (text.equals("+") || text.equals("-")) {
            stackUnit.push(text);
        } else if (text.equals("X") || text.equals("%") || text.equals("/")) {
            if (stackUnit.peek().equals("+") || stackUnit.peek().equals("-")) {
                while(!stackUnit.empty()) {
                    if (stackUnit.peek().equals("(")) {
                        break;
                    }
                    exprList.add(stackUnit.pop());
                }
                stackUnit.push(text);
            }
        } else {
            exprList.add(text);
        }
    }
    @Override
    public void clickedClearSet() {
        editText.setText("");
    }
    @Override
    public void clickedBracketSet() {
        String text = editText.getText().toString();
        int indexOftarget = editText.length() - 1;
        String target = "";
        if (indexOftarget >= 0) {
            target = Character.toString(text.charAt(indexOftarget));
        }
        if (target.equals("")) {
            stack.push(getResources().getString(R.string.bracketopen));
            stackUnit.push(getResources().getString(R.string.bracketopen));
            clickedTextSet(getResources().getString(R.string.bracketopen));
        } else if (target.equals("+") || target.equals("-") || target.equals("X") ||
                target.equals("/") || target.equals("%")) {
            stack.push(getResources().getString(R.string.bracketopen));
            stackUnit.push(getResources().getString(R.string.bracketopen));
            clickedTextSet(getResources().getString(R.string.bracketopen));
        } else if (target.equals("(")) {
            stack.push(getResources().getString(R.string.bracketopen));
            stackUnit.push(getResources().getString(R.string.bracketopen));
            clickedTextSet(getResources().getString(R.string.bracketopen));
        } else if (target.equals(")") && !stack.empty()) {
                stack.pop();
                while (!stackUnit.peek().equals("(")) {
                    exprList.add(stackUnit.pop());
                }
                clickedTextSet(getResources().getString(R.string.bracketclose));
        }
        else {
            if (stack.empty()) {
                stackUnit.push(getResources().getString(R.string.multiply));
                stackUnit.push(getResources().getString(R.string.bracketopen));
                clickedTextSet(getResources().getString(R.string.multiply));
                clickedTextSet(getResources().getString(R.string.bracketopen));
                stack.push(getResources().getString(R.string.bracketopen));

            }
            else {
                stack.pop();
                clickedTextSet(")");
            }
        }
    }
    @Override
    public void clickedEqualSet() {
        String expr = editText.getText().toString();

    }

    ToggleButton buttonHistory;
    FragmentManager fragmentManager;
    EditText editText;
    Button buttonRemove;
    Stack<String> stack = new Stack<>();
    Stack<String> stackUnit = new Stack<>();
    ArrayList<String> exprList = new ArrayList<>();
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
}
