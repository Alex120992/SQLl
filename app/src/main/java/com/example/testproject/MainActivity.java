package com.example.testproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button;
    EditText editText;
    EditText editText2;
    SQLiteDatabase sqLiteDatabase;
    ItemAdapter itemAdapter;
    Button delete;
    static int locate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DataBaseHelper db = new DataBaseHelper(this);
        sqLiteDatabase = db.getWritableDatabase();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(this, getAllItem());
        recyclerView.setAdapter(itemAdapter);
        editText = (EditText) findViewById(R.id.number);
        editText2 = (EditText) findViewById(R.id.element);
        button = (Button) findViewById(R.id.add);
        Log.d("Позиция ", String.valueOf(locate));
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put(CreateDbSchem.Table.Columns.NUMBER, editText.getText().toString());
                cv.put(CreateDbSchem.Table.Columns.DATA, editText2.getText().toString());
                sqLiteDatabase.insert(CreateDbSchem.Table.TABLE_NAME, null, cv);
                itemAdapter.swapCursor(getAllItem());
                editText.getText().clear();
                editText2.getText().clear();
            }
        });
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = getAllItem();
                cursor.moveToLast();
                int number = cursor.getPosition();
                Log.d("позиция", String.valueOf(number));

                int del = sqLiteDatabase.delete(CreateDbSchem.Table.TABLE_NAME, "1", null);
                itemAdapter.swapCursor(getAllItem());
                Log.d("ELFK", String.valueOf(del));
                cursor.close();
            }
        });

    }

    public Cursor getAllItem() {
        return sqLiteDatabase.query(
                CreateDbSchem.Table.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                "_ID DESC"
        );
    }

}
