package com.example.owner.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Vector;

/**
 * Created by Owner on 2016-12-28.
 */
public class Search_listviewAdapter extends BaseAdapter {
    private Vector<Notice_List> list;
    private DBManager dbManager;

    public Search_listviewAdapter(Vector<Notice_List> list){
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
        final DBManager dbManager = new DBManager(context, "Mynotice.db", null , 1);

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        final TextView category = (TextView)view.findViewById(R.id.cate);
        final TextView title = (TextView)view.findViewById(R.id.notice_title);
        final TextView date = (TextView)view.findViewById(R.id.notice_date);
        ImageButton delete = (ImageButton)view.findViewById(R.id.delete);
        delete.setImageResource(R.drawable.ic_notice_list_btn_on);

        final Notice_List item = list.get(i);

        category.setText(item.getCats());
        title.setText(item.getTitles());
        date.setText(item.getDates());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
