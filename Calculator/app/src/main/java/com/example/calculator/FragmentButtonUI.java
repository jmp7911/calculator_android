package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentButtonUI extends Fragment {
    View view;
    LayoutInflater layoutInflater;
    View mainView;
    Button buttonClear;
    EditText text;
    Button buttonBracket;
    Button buttonReamin;
    Button buttonDivide;
    Button buttonMultiply;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonEqual;
    Button buttonDot;
    Button buttonZero;
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;
    Button buttonToggle;
    Stack stack = new Stack();
    Stack<Character> stackUnit = new Stack<Character>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_button_ui, container, false);
        return view;
    }
/*
1 * / %
2 + -
3 ()

ex (-29*(-6+7=
후위 - exprList
stackUnit
expressionString
 */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        buttonClear = view.findViewById(R.id.clear);


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
            }
        });
        buttonBracket = view.findViewById(R.id.bracket);

        buttonReamin = view.findViewById(R.id.remain);

        buttonDivide = view.findViewById(R.id.divide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonMultiply = view.findViewById(R.id.multiply);
        buttonMinus = view.findViewById(R.id.minus);
        buttonPlus = view.findViewById(R.id.plus);
        buttonEqual = view.findViewById(R.id.equal);
        buttonDot = view.findViewById(R.id.dot);
        buttonZero = view.findViewById(R.id.zero);
        buttonOne = view.findViewById(R.id.one);
        buttonTwo = view.findViewById(R.id.two);
        buttonThree = view.findViewById(R.id.three);
        buttonFour = view.findViewById(R.id.four);
        buttonFive = view.findViewById(R.id.five);
        buttonSix = view.findViewById(R.id.six);
        buttonSeven = view.findViewById(R.id.seven);
        buttonEight = view.findViewById(R.id.eight);
        buttonNine = view.findViewById(R.id.nine);
        buttonToggle = view.findViewById(R.id.toggle);
        super.onActivityCreated(savedInstanceState);
    }
}
