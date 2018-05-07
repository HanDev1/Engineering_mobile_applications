package com.example.a2hanj43.android5uis;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> PunID;
    ArrayList<String> PunName;
    ArrayList<String> PunGenre;


    public SQLiteListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> pun,
            ArrayList<String> genre
    )
    {

        this.context = context2;
        this.PunID = id;
        this.PunName = pun;
        this.PunGenre = genre;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return PunID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewpun = (TextView) child.findViewById(R.id.textViewPun);
            holder.textviewgenre = (TextView) child.findViewById(R.id.textViewGenre);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(PunID.get(position));
        holder.textviewpun.setText(PunName.get(position));
        holder.textviewgenre.setText(PunGenre.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewpun;
        TextView textviewgenre;
    }

}