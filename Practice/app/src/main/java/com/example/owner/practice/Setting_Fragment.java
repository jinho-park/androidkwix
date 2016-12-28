package com.example.owner.practice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Setting_Fragment extends Fragment implements AdapterView.OnItemClickListener {
    private Switch newAlarm;
    private Switch keyWord;
    private ListView keyword_settinglist;
    public ArrayList<String> data_list = new ArrayList<String>();
    private ArrayAdapter<String> m_Adapter;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstance) {
        View view = layoutInflater.inflate(R.layout.setting, container, false);
        newAlarm = (Switch) view.findViewById(R.id.setting_switch_newnotice);
        keyWord = (Switch) view.findViewById(R.id.setting_switch_keyword);
        keyword_settinglist = (ListView)view.findViewById(R.id.keyword_settinglist);
        keyword_settinglist.setOnItemClickListener(this);
        data_list.add("키워드 편집");
        m_Adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,data_list);
        keyword_settinglist.setAdapter(m_Adapter);
        m_Adapter.notifyDataSetChanged();
        SharedPreferences prefs = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("alarm", false);
        newAlarm.setChecked(switchState);
        switchState = prefs.getBoolean("keyword", false);
        keyWord.setChecked(switchState);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        SharedPreferences.Editor editor = getContext().getSharedPreferences("pref",
                MODE_PRIVATE).edit();
        editor.putBoolean("alarm", newAlarm.isChecked());
        editor.putBoolean("keyword", keyWord.isChecked());
        editor.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), Keywordactivity.class);
        startActivity(intent);
    }
}
