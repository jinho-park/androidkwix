package com.example.owner.practice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Vector;

/**
 * Created by Owner on 2016-12-27.
 */
public class NewNotice_Fragment extends Fragment {
    private DBManager dbManager;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.newnotice, container, false);
        dbManager = new DBManager(getContext(), "Newnotice.db", null, 1);
        Log.d("NEWNOTICE", "Fragment start");

        listView = (ListView)view.findViewById(R.id.newnotice_listview);
        Home_ListviewAdapter adapter = new Home_ListviewAdapter(dbManager.getData());
        listView.setAdapter(adapter);

        return view;
    }
}
