package com.example.a2hanj43.android5uis;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.a2hanj43.android5uis.MyHelper.TABLE_NAME;

public class ListViewActivity extends Activity {

    MyHelper Controller;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> Pun_ArrayList = new ArrayList<String>();
    ArrayList<String> Genre_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        Controller = new MyHelper(this);

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = Controller.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        ID_ArrayList.clear();
        Pun_ArrayList.clear();
        Genre_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.COL_1)));

                Pun_ArrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.COL_2)));

                Genre_ArrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.COL_3)));

            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteListAdapter(ListViewActivity.this,

                ID_ArrayList,
                Pun_ArrayList,
                Genre_ArrayList


        );

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
}
