package com.example.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentButtonUI extends Fragment {
    View view;
    Button buttonClear;
    TextView text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_button_ui, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        buttonClear = view.findViewById(R.id.clear);
        text = view.findViewById(R.id.text);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
