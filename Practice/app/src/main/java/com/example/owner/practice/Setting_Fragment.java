package com.example.owner.practice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Setting_Fragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Switch newAlarm;
    private Switch keyWord;
    private TextView text_keyword;
    private ListView keyword_settinglist;
    public ArrayList<String> data_list = new ArrayList<String>();
    private ArrayAdapter<String> m_Adapter;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstance) {
        View view = layoutInflater.inflate(R.layout.setting, container, false);
        newAlarm = (Switch) view.findViewById(R.id.setting_switch_newnotice);
        keyWord = (Switch) view.findViewById(R.id.setting_switch_keyword);
<<<<<<< HEAD
        keyword_settinglist = (ListView)view.findViewById(R.id.keyword_settinglist);
        keyword_settinglist.setOnItemClickListener(this);
        data_list.add("키워드 편집") ;
        m_Adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,data_list);
        keyword_settinglist.setAdapter(m_Adapter);
        m_Adapter.notifyDataSetChanged();
=======
        text_keyword = (TextView)view.findViewById(R.id.setting_text_keword);
        text_keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Keywordactivity.class);
                startActivity(intent);
            }
        });
>>>>>>> b14b29aec92e2a19f2542fa5089115e358b16885
        SharedPreferences prefs = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("alarm", false);
        newAlarm.setChecked(switchState);
        switchState = prefs.getBoolean("keyword", false);
        keyWord.setChecked(switchState);
        newAlarm.setOnCheckedChangeListener(this);
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
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b == false) keyWord.setEnabled(false);
        else keyWord.setEnabled(true);
    }
}
