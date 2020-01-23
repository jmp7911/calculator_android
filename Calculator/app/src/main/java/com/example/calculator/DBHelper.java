package com.example.calculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "history", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String historySQL = "create table tb_history (" +
                "expression, " +
                "result)";
        sqLiteDatabase.execSQL(historySQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            sqLiteDatabase.execSQL("drop table tb_history");
            onCreate(sqLiteDatabase);
        }
    }
}
