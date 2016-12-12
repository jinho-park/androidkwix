package com.example.owner.practice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

/**
 * Created by Owner on 2016-10-23.
 */
public class Setting_Fragment extends Fragment {
    private Switch newAlarm;
    private Switch keyWord;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstance){
        View view = layoutInflater.inflate(R.layout.setting, container, false);
        newAlarm = (Switch)view.findViewById(R.id.setting_switch_newnotice);
        keyWord = (Switch)view.findViewById(R.id.setting_switch_keyword);

        //UpdataUI();

        return view;
    }

    /*public void UpdataUI(){

    }*/
}
