package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
    public interface ClickedTextListener {
        void clickedTextSet (String text);
        void clickedClearSet();
        void clickedBracketSet ();
        void clickedEqualSet();
    }
    private ClickedTextListener clickedTextListener;
    private View view;
    private Button buttonClear;
    private Button buttonBracket;
    private Button buttonReamin;
    private Button buttonDivide;
    private Button buttonMultiply;
    private Button buttonMinus;
    private Button buttonPlus;
    private Button buttonEqual;
    private Button buttonDot;
    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonToggle;
    Stack<String> stack = new Stack<String>();
    Stack<String> stackUnit = new Stack<String>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ClickedTextListener) {
            clickedTextListener = (ClickedTextListener) context;
        }
    }

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
                clickedTextListener.clickedClearSet();
            }
        });
        buttonBracket = view.findViewById(R.id.bracket);
        buttonBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedBracketSet();
            }
        });
        buttonReamin = view.findViewById(R.id.remain);
        buttonReamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.remain));
            }
        });
        buttonDivide = view.findViewById(R.id.divide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.divide));
            }
        });
        buttonMultiply = view.findViewById(R.id.multiply);
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.multiply));
            }
        });
        buttonMinus = view.findViewById(R.id.minus);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.minus));
            }
        });
        buttonPlus = view.findViewById(R.id.plus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.plus));
            }
        });
        buttonEqual = view.findViewById(R.id.equal);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedEqualSet();
            }
        });
        buttonDot = view.findViewById(R.id.dot);
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.dot));
            }
        });
        buttonZero = view.findViewById(R.id.zero);
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.zero));
            }
        });
        buttonOne = view.findViewById(R.id.one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.one));
            }
        });
        buttonTwo = view.findViewById(R.id.two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.two));
            }
        });
        buttonThree = view.findViewById(R.id.three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.three));
            }
        });
        buttonFour = view.findViewById(R.id.four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.four));
            }
        });
        buttonFive = view.findViewById(R.id.five);
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.five));
            }
        });
        buttonSix = view.findViewById(R.id.six);
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.six));
            }
        });
        buttonSeven = view.findViewById(R.id.seven);
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.seven));
            }
        });
        buttonEight = view.findViewById(R.id.eight);
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.eight));
            }
        });
        buttonNine = view.findViewById(R.id.nine);
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTextListener.clickedTextSet(getResources().getString(R.string.nine));
            }
        });
        buttonToggle = view.findViewById(R.id.toggle);
        buttonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickedTextListener = null;
    }

}
