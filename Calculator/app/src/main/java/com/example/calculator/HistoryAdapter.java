package com.example.calculator;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class HistoryAdapter extends ArrayAdapter<HistoryItem> {
    Context context;
    int resid;
    ArrayList<HistoryItem> histories;
    public HistoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HistoryItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resid = resource;
        this.histories = objects;
    }

    @Override
    public int getCount() {
        return histories.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resid, null);

            HistoryWrapper wrapper = new HistoryWrapper(convertView);
            convertView.setTag(wrapper);
        }
        HistoryWrapper wrapper = (HistoryWrapper)convertView.getTag();
        TextView expression = wrapper.expression;
        TextView result = wrapper.result;

        HistoryItem historyItem = histories.get(position);
        expression.setText(historyItem.getInfixExpression());
        result.setText(historyItem.getResult());
        return convertView;
    }
}
