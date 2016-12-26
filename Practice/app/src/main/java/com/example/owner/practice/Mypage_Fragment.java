package com.example.owner.practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Owner on 2016-12-22.
 */
public class Mypage_Fragment extends Fragment{
    final String TAG = "MYPAGE";
    ListView listView;
    ListViewAdapter listViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        DBManager dbManager = new DBManager(getContext(), "Mynotice.db", null , 1);
        View view = inflater.inflate(R.layout.mypage, container, false);

        Log.d(TAG, "listview creating");

        listView = (ListView)view.findViewById(R.id.listview);
        //if(listView == null) Log.d(TAG, "listvew is null");
        listViewAdapter = new ListViewAdapter(dbManager.getData());
        //if(listViewAdapter == null) Log.d(TAG, "adapter is null");
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Notice_List item = (Notice_List)adapterView.getAdapter().getItem(i);
                String url = item.getUrls();
                Log.d("LISTVIEW", "Click " + url);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        return view;
    }
}
