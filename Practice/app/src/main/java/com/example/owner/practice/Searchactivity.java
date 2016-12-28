package com.example.owner.practice;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class Searchactivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    final int SEARCH_THREAD_OK = 5;
    private EditText et;
    private Toolbar toolbar;
    private SearchView mSearchView;
    Vector<Notice_List> search_lists;
    static QueryThread queryThread;
    String search = "sch/";
    private ListView listView;
    private Search_listviewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchactivity);

        getSupportActionBar().hide();
        toolbar = (Toolbar)findViewById(R.id.toolbar_search);
        toolbar.inflateMenu(R.menu.search);
        listView = (ListView)findViewById(R.id.search_listview);
        listViewAdapter = new Search_listviewAdapter(search_lists);
        mSearchView = (SearchView)toolbar.getMenu().findItem(R.id.menu_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("TOOLBAR", "submit");
                if(!query.equals("")) {
                    String message = search +""+ query;
                    Log.d("MESSAGE", message);
                    search_lists = new Vector<Notice_List>();
                    QueryThread queryThread = new QueryThread(message, Searchactivity.this, search_lists);
                    queryThread.start();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        et = (EditText)findViewById(R.id.edittext);
        et.setOnEditorActionListener(this);
        et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        et.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch (i) {
            case EditorInfo.IME_ACTION_SEARCH:
                final String value = et.getText().toString();
                search += value;
                search_lists = new Vector<Notice_List>();
                Log.d("test",value);
                Log.d("test","search keyboard execute");
                queryThread = new QueryThread(search, this, search_lists);
                queryThread.start();
                break;
            default:
                return false;
        }
        return true;
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case SEARCH_THREAD_OK:
                    Log.d("SEARCH", "size : " + search_lists.size());
                    listViewAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
}