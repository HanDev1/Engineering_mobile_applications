package com.example.a2hanj43.android5uis;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab1);

        fab.setOnClickListener (new View.OnClickListener() {

            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null).
                        setMessage("The FloatingActionButton was clicked!").show();
            }
        });

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
}
//on page 9