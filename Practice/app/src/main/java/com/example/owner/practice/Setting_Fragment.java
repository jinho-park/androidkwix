package com.example.owner.practice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import static android.content.Context.MODE_PRIVATE;

public class Setting_Fragment extends Fragment {
    private Switch newAlarm;
    private Switch keyWord;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstance) {
        View view = layoutInflater.inflate(R.layout.setting, container, false);
        newAlarm = (Switch) view.findViewById(R.id.setting_switch_newnotice);
        keyWord = (Switch) view.findViewById(R.id.setting_switch_keyword);
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
}
