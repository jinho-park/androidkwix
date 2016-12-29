package com.example.owner.practice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentActivity extends AppCompatActivity {
    final int MYPAGE = 1;
    final int SETTING = 2;
    final int NOTICE = 3;
    final int HOME = 4;

    public void fragmentReplace(int index){
        Fragment newfragment = null;

        newfragment= getFragment(index);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newfragment);
        transaction.commit();
    }

    private Fragment getFragment(int idx) {
        Fragment newfragment = null;
        switch (idx){
            case MYPAGE:
                newfragment = new Mypage_Fragment();
                break;
            case SETTING:
                newfragment = new Setting_Fragment();
                break;
            case NOTICE:
                newfragment = new Notice_Fragment();
                break;
            case HOME:
                newfragment = new NewNotice_Fragment();
                break;
        }
        return newfragment;
    }
}
