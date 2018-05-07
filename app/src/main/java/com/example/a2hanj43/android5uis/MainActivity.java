package com.example.a2hanj43.android5uis;

import android.Manifest;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.a2hanj43.android5uis.MyHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    EditText Pun, Genre;
    TextView textview;
    MyHelper controller;
    Cursor cursor;
    //combining list activity
    /*
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> Pun_ArrayList = new ArrayList<String>();
    ArrayList<String> Genre_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab1);

        Pun = (EditText)findViewById(R.id.pun);
        Genre = (EditText)findViewById(R.id.genre);

        controller = new MyHelper(this);

        EditText pun = (EditText)findViewById(R.id.pun);
        EditText genre = (EditText)findViewById(R.id.genre);

        addData();
        showAllPuns();

        //adding listview
        //LISTVIEW = (ListView) findViewById(R.id.listView1);



//fab.onclicklistenre went here withoutthe adddata

        NavigationView nView = (NavigationView)findViewById(R.id.navigationView);
        final DrawerLayout dLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


        nView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            public boolean onNavigationItemSelected(MenuItem item) {
                //String what = "";
                switch(item.getItemId()) {
                    case R.id.save:
                        //what = "SAVE";
                        break;

                    case R.id.load:
                        //what = "LOAD";
                        Cursor res = controller.getAllData();

                        if (res.getCount() == 0){// means if there is no data
                            //show message
                            showMessage("Error", "no data has been found");
                            //return
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res .moveToNext()) { //mmovetonext means it will move the cursor to the next result
                            buffer.append("ID : " + res.getString(0) + "\n"); //parse index of a collumn. indicies start from 0. 0 = id from ou dadtabase
                            buffer.append("Pun : " + res.getString(1) + "\n");
                            buffer.append("Genre : " + res.getString(2) + "\n\n");
                        }
                        //showing all data
                        showMessage("Data", buffer.toString());

                        break;

                    case R.id.about:
                        //what = "ABOUT US";
                        String about = "Candidate Q12248843's EMA AE2 assessment - Featuring a pun finder";
                        new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
                        setMessage(about).show();
                        break;

                }
                //new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).set
                        //setMessage(what).show();
                dLayout.closeDrawers();// Close the navigation drawer
                return true;
            }
        });


    }

    //addinglistviewactivity
    /*
    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = controller.getWritableDatabase();

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

        ListAdapter = new SQLiteListAdapter(MainActivity.this,

                ID_ArrayList,
                Pun_ArrayList,
                Genre_ArrayList


        );

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
    */

    public void addData(){
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab1);
            fab.setOnClickListener (new View.OnClickListener() {

        public void onClick(View view) {
            new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
                    setMessage("pun has been submitted").show();
            boolean isInserted = controller.insertData(Pun.getText().toString(),
                    Genre.getText().toString());
            if (isInserted=true){ // check to see if the data has been inserted
                Toast.makeText(MainActivity.this, "data has been inserted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this, "data has NOT been inserted", Toast.LENGTH_LONG).show();
            }
            //refresh edittext boxes to blank after fab is clicked.
            Pun.setText(null);
            Genre.setText(null);

            //
            //-AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            //-alertDialog.setMessage("Enter your pun!");
//                final EditText input = new EditText(MainActivity.this);

//                +if(input.getVisibility() == View.VISIBLE)
//                +{
//                +    input.setVisibility(View.INVISIBLE);
//                +}
//                +else {
//                +    input.setVisibility(View.VISIBLE);
//                +}
////                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.MATCH_PARENT);
//                input.setLayoutParams(lp);
//                alertDialog.setView(input);

        }


    });}

    public void showAllPuns() {
        Cursor res = controller.getAllData();

        if (res.getCount() == 0) {// means if there is no data
            //show message
            showMessage("Error", "no data has been found");
            //return
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) { //mmovetonext means it will move the cursor to the next result
            buffer.append("ID : " + res.getString(0) + "\n"); //parse index of a collumn. indicies start from 0. 0 = id from ou dadtabase
            buffer.append("Pun : " + res.getString(1) + "\n");
            buffer.append("Genre : " + res.getString(2) + "\n\n");
        }
        //showing all data
        showMessage("Puns", buffer.toString());
    }

    public void showMessage (String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // can set title and message using this builder here to create dialogue
        builder.setCancelable(true); // so we can cancel it after its use
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

    //gets all puns to display as a news feed
    public void showFeed (String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // can set title and message using this builder here to create dialogue

        builder.setCancelable(true); // so we can cancel it after its use
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

   /* class SearchHandler implements SearchView.OnQueryTextListener {


            public SearchHandler(){

            }

        public boolean onQueryTextChange(String s) {
            // do nothing... (this method runs when the user types a new character)
            return true;
        }

        public boolean onQueryTextSubmit(String s) {
            // show the search text in an alert dialog
            System.out.println("hi onquery");
//            new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
//                    setMessage(txt).show();


            return true;

        }
    } */




    public boolean onCreateOptionsMenu(Menu menu)
    {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView sv = (SearchView) MenuItemCompat.getActionView(item);
//        sv.setOnQueryTextListener(new SearchHandler());
//
//
//        return true;

        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView sv = (SearchView) menu.findItem(R.id.search).getActionView();
        sv.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String s) {

                System.out.println(s);
//                cursor =  controller.getSearchData(s);
//                if (cursor == null) {
//                    Toast.makeText(MainActivity.this, "No records found!", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(MainActivity.this, cursor.getCount() + " records found!", Toast.LENGTH_LONG).show();
//                }

                Cursor res = controller.getSearchData(s);

                if (res.getCount() == 0){// means if there is no data
                    //show message
                    showMessage("Error", "no data has been found");
                    //return
                }
                StringBuffer buffer = new StringBuffer();
                while (res .moveToNext()) { //mmovetonext means it will move the cursor to the next result
                    buffer.append("ID : " + res.getString(0) + "\n"); //parse index of a collumn. indicies start from 0. 0 = id from ou dadtabase
                    buffer.append("Pun : " + res.getString(1) + "\n");
                    buffer.append("Genre : " + res.getString(2) + "\n\n");
                }
                //showing all data
                showMessage("Puns", buffer.toString());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                cursor = controller.getSearchData(s);
                return false;
            }

        });

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId() == R.id.settings)
        {
            System.out.println("HI settings");
            // react to the menu item being selected...
            new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("settings...").show();
            return true;
        }

        else if(item.getItemId() == R.id.search)
        {
            System.out.println("HI FRIEND");
             //react to the menu item being selected...
          new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("searching...").show();
            return true;
        }
        return false;
    }

    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        // handle list item selection
    }



}
//on page 9