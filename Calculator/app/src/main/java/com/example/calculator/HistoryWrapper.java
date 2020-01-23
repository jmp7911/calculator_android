package com.example.calculator;

import android.view.View;
import android.widget.TextView;
public class HistoryWrapper {
    TextView expression;
    TextView result;

    public HistoryWrapper(View root) {
        expression = root.findViewById(R.id.expression);
        result = root.findViewById(R.id.result);
    }

}
