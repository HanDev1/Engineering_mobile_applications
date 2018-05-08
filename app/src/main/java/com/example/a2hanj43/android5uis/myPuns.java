package com.example.a2hanj43.android5uis;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class myPuns extends ListActivity {

    MyHelper controller;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> Pun_ArrayList = new ArrayList<String>();
    ArrayList<String> Genre_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    String[] Puns, Genres;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        controller = new MyHelper(this);}/*

        Puns = new String[] {Pun};
        Genres = new String[] { Genre};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);


//        populateListView();

    }

    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        // handle list item selection
    }


    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            // We have to use ExampleListActivity.this to refer to the outer class (the activity)
            super(myPuns.this, android.R.layout.simple_list_item_1, names);
        }

        public View getView(int index, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.poientry, parent, false);
            TextView title = (TextView)view.findViewById(R.id.poi_name), detail =
                    (TextView)view.findViewById(R.id.poi_desc);
            title.setText(names[index]);
            detail.setText(details[index]);
            return view;
        }
    }

//    private void populateListView(){
//        Cursor res = controller.getalldata();
//        //array will be used by curosr to be placed into the listview. array will hold all id and puns
//        String[] fromFieldNames = new String[] {Helper.ID, Helper.Puns};
//        int[] toViewIDs = new int[] {R.id.TextViewItemNumber, R.id.TextViewItemPuns};
//        SimpleCurosrAdapter myCursorAdapter;
//        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_layout, cursor, fromFieldNames, toViewIDs, 0);
//        ListView MyList = (ListView) findViewById(R.id.listViewTasks);
//        myList.setAdapter(myCursorAdapter);
//
//    }

    */
}
