package com.example.owner.practice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Owner on 2016-12-27.
 */
public class Home_ListviewAdapter extends BaseAdapter {
    private ArrayList<Notice_List> list ;
    final String TAG = "HOME_ADPATER";

    public Home_ListviewAdapter(ArrayList<Notice_List> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();
        final DBManager dbManager = new DBManager(context, "Newnotice.db", null , 1);

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview_newitem, viewGroup, false);
        }

        final TextView category = (TextView)view.findViewById(R.id.newitem_cate);
        final TextView title = (TextView)view.findViewById(R.id.newitem_title);
        final TextView date = (TextView)view.findViewById(R.id.newitem_date);
        if(category == null) Log.d(TAG, "category is null");
        final Notice_List item = list.get(i);

        category.setText(item.getCats());
        title.setText(item.getTitles());
        date.setText(item.getDates());

        return view;
    }
}
