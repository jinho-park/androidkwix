package com.example.owner.practice;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jinhyeon kim on 2016-12-27.
 */

public class CustomAdapter extends BaseAdapter{
    private ArrayList<String> m_List = new ArrayList<String>();
    private Context mcontext = null;
    private LayoutInflater inflater = null;
    private SharedPreferences prefs ;
    private int size = 0;
    public CustomAdapter(Context c,ArrayList<String> list) {
        this.mcontext = c;
        this.inflater = LayoutInflater.from(c);
        prefs = mcontext.getSharedPreferences("pref", MODE_PRIVATE);
        size = prefs.getInt("size", 0);
        Log.d("test", "size : " + size);
        for (int i = 0; i < size; i++) {
            m_List.add(prefs.getString("list" + i, null));
        }
    }
    @Override
    public int getCount() {
        return m_List.size();
    }

    @Override
    public Object getItem(int i) {
        return m_List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, final ViewGroup viewGroup) {
        View row = inflater.inflate(R.layout.keyword, viewGroup, false);
        TextView text = (TextView) row.findViewById(R.id.keytext);
        text.setText(m_List.get(position));
        ImageButton btn = (ImageButton) row.findViewById(R.id.keybutton);
        btn.setTag(position);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                m_List.remove(pos);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("size",m_List.size());
                Log.d("pos", String.valueOf(m_List.size()));
                Log.d("pos",prefs.getString("list"+pos,null));
                for( int i = 0; i < m_List.size(); i++){
                    editor.putString("list"+i,m_List.get(i));
                }
                CustomAdapter.this.notifyDataSetChanged();
                editor.commit();
            }
        });

        return row;
    }
    public void add(String _msg) {
        SharedPreferences.Editor editor = prefs.edit();
        m_List.add(_msg);
        editor.putInt("size",m_List.size());
        int k = m_List.size()-1;
        editor.putString("list"+k,_msg);
        editor.commit();
        CustomAdapter.this.notifyDataSetChanged();
    }
}
