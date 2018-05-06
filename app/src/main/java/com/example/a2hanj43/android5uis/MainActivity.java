package com.example.a2hanj43.android5uis;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Pun, Genre;
    TextView textview;
    MyHelper controller;

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


//fab.onclicklistenre went here withoutthe adddata

        NavigationView nView = (NavigationView)findViewById(R.id.navigationView);
        final DrawerLayout dLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


        nView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            public boolean onNavigationItemSelected(MenuItem item) {
                String what = "";
                switch(item.getItemId()) {
                    case R.id.save:
                        what = "SAVE";
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
                        what = "ABOUT US";
                        break;

                }
                new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
                        setMessage(what).show();
                dLayout.closeDrawers();// Close the navigation drawer
                return true;
            }
        });

        MyHelper helper; //attribute of the helper activity class

        helper = new MyHelper(this);

    }
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
        showMessage("Data", buffer.toString());
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

    class SearchHandler implements SearchView.OnQueryTextListener {


        public SearchHandler(){

        }

        public boolean onQueryTextChange(String txt) {
            // do nothing... (this method runs when the user types a new character)
            return true;
        }

        public boolean onQueryTextSubmit(String txt) {
            // show the search text in an alert dialog
            new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
                    setMessage(txt).show();
            return true;
        }
    }




    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView sv = (SearchView) MenuItemCompat.getActionView(item);
        sv.setOnQueryTextListener(new SearchHandler());


        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.save)
        {
            // react to the menu item being selected...
            new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("saving...").show();
            return true;

        }
        else if(item.getItemId() == R.id.create)
        {
            // react to the menu item being selected...
            new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("creating...").show();
            return true;
        }

        else if(item.getItemId() == R.id.settings)
        {
            // react to the menu item being selected...
            new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("settings...").show();
            return true;
        }

        else if(item.getItemId() == R.id.search)
        {
            // react to the menu item being selected...
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