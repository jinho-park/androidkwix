package com.example.owner.practice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Owner on 2016-12-22.
 */
public class ListViewAdapter extends BaseAdapter{
    private ArrayList<Notice_List> list= new ArrayList<Notice_List>();
    private String url;

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
        final DBManager dbManager = new DBManager(context, "Mynotice.db", null , 1);

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        final TextView category = (TextView)view.findViewById(R.id.cate);
        final TextView title = (TextView)view.findViewById(R.id.notice_title);
        final TextView date = (TextView)view.findViewById(R.id.notice_date);
        ImageButton delete = (ImageButton)view.findViewById(R.id.delete);

        final Notice_List item = list.get(i);

        category.setText(item.getCats());
        title.setText(item.getTitles());
        date.setText(item.getDates());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.delete(item.getCats(), item.getTitles(), item.getDates(), item.getUrls());
                list.remove(item);
                notifyDataSetChanged();
            }
        });

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
