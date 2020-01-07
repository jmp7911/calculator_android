package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton buttonHistory;
    FragmentManager fragmentManager;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
