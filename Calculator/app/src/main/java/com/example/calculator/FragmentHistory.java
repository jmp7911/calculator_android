package com.example.calculator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHistory extends Fragment {

    ListView listView;
    DBHelper dbHelper;
    HistoryAdapter adapter;
    ArrayList<HistoryItem> histories = new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dbHelper = new DBHelper(context);
        adapter = new HistoryAdapter(context, R.layout.history_list_item, histories);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = view.findViewById(R.id.list_item);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_history", null);
        while (cursor.moveToNext()) {
            HistoryItem hi = new HistoryItem();
            hi.setInfixExpression(cursor.getString(0));
            hi.setResult(cursor.getString(1));
            histories.add(hi);
        }
        db.close();
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        histories.clear();
    }
}
