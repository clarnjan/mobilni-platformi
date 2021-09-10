package com.example.mobilniproekt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databaza.db";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                "tabela" + " (" +
                "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name" + " TEXT NOT NULL, " +
                "diff" + " TEXT NOT NULL, " +
                "e1" + " TEXT NOT NULL, "+
                "e2" + " TEXT NOT NULL, "+
                "e3" + " TEXT NOT NULL, "+
                "e4" + " TEXT NOT NULL, "+
                "e5" + " TEXT NOT NULL, "+
                "e6" + " TEXT NOT NULL, "+
                "e7" + " TEXT NOT NULL, "+
                "e8" + " TEXT NOT NULL, "+
                "img" + " BLOB NOT NULL, "+
                "timestamp" + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "tabela");
        onCreate(db);
    }
}