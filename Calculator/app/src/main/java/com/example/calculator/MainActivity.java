package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements FragmentButtonUI.ClickedTextListener {
    public interface CalculateListener {
        ArrayList toPostfixExpression(String infixExpr, int start);
        Object postfixExpressionCalculate(ArrayList<Object> exprList);
    }
    @Override
    public void clickedTextSet(String text) {
        String setText = editText.getText().toString() + text;
        editText.setText(setText);
    }
    @Override
    public void clickedClearSet() {
        editText.setText("");
    }
    @Override
    public void clickedBracketSet() {
        String text = editText.getText().toString();
        int indexOftarget = editText.length() - 1;
        String target;
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
                for (Operation o : Operation.values()) {
                    if (o.getName().equals(target)) {
                        stackBracket.push(getResources().getString(R.string.bracketopen));
                        clickedTextSet(getResources().getString(R.string.bracketopen));
                        break;
                    }
                }
                for (DecimalNumber n : DecimalNumber.values()) {
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
        Calculate calculate = new Calculate();
        ArrayList<Object> postfixExprList = calculate.toPostfixExpression(infixExpr, 0);
        Object result = calculate.postfixExpressionCalculate(postfixExprList);
//        if (result instanceof Double) {
//            editText.setText(String.format("%f", result));
//        } else {
//            editText.setText(String.format("%d", result));
//        }
        editText.setText(result.toString());
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into tb_history(expression, result) values (?,?)",
                new String[] {infixExpr, result.toString()});
        db.close();
    }
    ToggleButton buttonHistory;
    FragmentManager fragmentManager;
    EditText editText;
    Button buttonRemove;
    Stack<String> stackBracket = new Stack<>();
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


