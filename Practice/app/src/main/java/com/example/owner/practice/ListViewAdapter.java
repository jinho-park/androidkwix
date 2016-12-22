package com.example.owner.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Owner on 2016-12-22.
 */
public class ListViewAdapter extends BaseAdapter {
    private ArrayList<Notice_List> list= new ArrayList<Notice_List>();

    public ListViewAdapter(ArrayList<Notice_List> list){
        this.list = list;
    }

    public ListViewAdapter(){

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

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        TextView category = (TextView)view.findViewById(R.id.cate);
        TextView title = (TextView)view.findViewById(R.id.notice_title);
        TextView date = (TextView)view.findViewById(R.id.notice_date);

        Notice_List item = list.get(i);

        category.setText(item.getCats());
        title.setText(item.getTitles());
        date.setText(item.getDates());

        return view;
    }

    public void addItem(String cate, String title, String date, String url){
        Notice_List item = new Notice_List();

        item.setCat(cate);
        item.setTitle(title);
        item.setDate(date);
        item.setUrls(url);

        list.add(item);
    }
}
