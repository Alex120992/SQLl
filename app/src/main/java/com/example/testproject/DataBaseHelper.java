package com.example.testproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.testproject.CreateDbSchem.Table;

;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String BASE_NAME = "data_list";
    public static final int VERSION = 1;


    public DataBaseHelper(Context context) {
        super(context, BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String query = "CREATE TABLE " + Table.TABLE_NAME + " (" +
                Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.Columns.DATA + " TEXT, " +
                Table.Columns.NUMBER + " INTEGER " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
