package com.example.owner.practice;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Keywordactivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private ListView  m_ListView;
    private CustomAdapter m_Adapter;
    private ArrayList<String> mlist = new ArrayList<String>();
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywordactivity);
        button = (Button)findViewById(R.id.button);
        et = (EditText)findViewById(R.id.editText);
        m_Adapter = new CustomAdapter(this,mlist);
        m_ListView = (ListView) findViewById(R.id.listview);
        m_ListView.setAdapter(m_Adapter);
        if(!et.getText().equals("")) {
            button.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        Log.d("test","enter");
        String value = et.getText().toString();
        m_Adapter.add(value);
        et.setText("");
    }
}
