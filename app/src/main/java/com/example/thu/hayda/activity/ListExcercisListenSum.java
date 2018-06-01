package com.example.thu.hayda.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.thu.hayda.Class_ExerciesListen;
import com.example.thu.hayda.Database;
import com.example.thu.hayda.R;
import com.example.thu.hayda.adapter.AdapterExcerciesListen;

import java.util.ArrayList;

public class ListExcercisListenSum extends AppCompatActivity {
    final String DATABASE_NAME = "thth.sqlite";
    SQLiteDatabase database;
    ListView list_ExerciesListenSum;

    ArrayList<Class_ExerciesListen> list;
    AdapterExcerciesListen adapter;

    String Result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_excercis_listen_sum);

        list_ExerciesListenSum=findViewById(R.id.list_ExerciesListenSum);

        Intent intent = getIntent();
        Result = intent.getStringExtra("ID");

        list = new ArrayList<>();
        adapter = new AdapterExcerciesListen(ListExcercisListenSum.this, list);
        list_ExerciesListenSum.setAdapter(adapter);

        database = Database.initDatabase(this, DATABASE_NAME);
//           Cursor cursor = database.rawQuery("select * from word ", null);
        Cursor cursor = database.rawQuery("SELECT WordOne,WordTwo FROM ExcerciseSumListen  where ID_Level=? ", new String[]{Result});
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String word_True = cursor.getString(0);
            String word_False = cursor.getString(1);
            list.add(new Class_ExerciesListen(word_True,word_False));


        }
        adapter.notifyDataSetChanged();
    }
}
