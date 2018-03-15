package com.example.a2hanj43.android5uis;

/**
 * Created by 2hanj43 on 15/03/2018.
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class Frag1 extends android.app.Fragment {
    public Frag1() {

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle 		savedInstanceState) {
        return inflater.inflate(R.layout.frag1, group, false);
    }



}
