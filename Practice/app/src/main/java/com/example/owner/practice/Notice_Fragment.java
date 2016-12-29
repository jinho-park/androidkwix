package com.example.owner.practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class Notice_Fragment extends Fragment {
    final int THREAD_QUERY_SUCCESS_INFO = 1;
    final int SPINNER_THREAD_QUERY_SUCCESS = 6;
    private RecyclerView mNoticeRecyclerView;
    private NoticeAdapter mAdapter;
    private Spinner spinner;
    private Notice_Fragment mClass;
    static QueryThread queryThread;
    DBManager dbManager;
    Vector<Notice_List> notice_lists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.notice, container, false);
        notice_lists = new Vector<Notice_List>();
        dbManager = new DBManager(getContext(), "Mynotice.db", null , 1);
        mClass = this;
        queryThread = new QueryThread("all", mClass, notice_lists);
        queryThread.start();
        mNoticeRecyclerView = (RecyclerView)view.findViewById(R.id.notice_fragment_view);
        mNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        spinner = (Spinner)view.findViewById(R.id.notice_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                notice_lists.clear();
                String message;
                switch(i){
                    case 0:
                        message = "all";
                        break;
                    case 1:
                        message = "nor";
                        break;
                    case 2:
                        message = "stu";
                        break;
                    case 3:
                        message = "hak";
                        break;
                    case 4:
                        message = "vol";
                        break;
                    case 5:
                        message = "jan";
                        break;
                    case 6:
                        message = "ent";
                        break;
                    case 7:
                        message = "sul";
                        break;
                    case 8:
                        message = "mil";
                        break;
                    case 9:
                        message = "out";
                        break;
                    default:
                        message = "all";
                        break;
                }
                queryThread = new QueryThread(message, mClass , notice_lists);
                queryThread.start();
                Log.d("SPINNER", "Position : " + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void updateUI(){
        NoticeLab noticeLab = NoticeLab.get(getActivity());

        if(mAdapter == null){
            mAdapter = new NoticeAdapter(notice_lists);
            mNoticeRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private class NoticeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Notice_List mNotice;
        private TextView mNoticetitle;
        private TextView mCategory;
        private TextView mDate;
        private ImageButton button;

        public NoticeHolder(View view){
            super(view);
            view.setOnClickListener(this);
            mNoticetitle = (TextView)view.findViewById(R.id.notice_id);
            mCategory = (TextView)view.findViewById(R.id.category);
            mDate = (TextView)view.findViewById(R.id.date);
            button = (ImageButton)view.findViewById(R.id.imageButton);
        }

        public void bindnotice(Notice_List list){
            mNotice = list;
            mNoticetitle.setText(mNotice.getTitles());
            mCategory.setText(mNotice.getCats());
            mDate.setText(mNotice.getDates());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbManager.insert(mNotice.getCats(), mNotice.getTitles(), mNotice.getDates(), mNotice.getUrls());
                    Toast.makeText(getContext(), "추가되었습니다", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onClick(View view){
            String url = mNotice.getUrls();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            /*Intent intent = MainActivity.newIntent(getActivity());
            startActivity(intent);*/
        }
    }

    private class NoticeAdapter extends RecyclerView.Adapter<NoticeHolder>{
        private Vector<Notice_List> mNotice;

        public NoticeAdapter(Vector<Notice_List> notice){
            mNotice = notice;
        }

        @Override
        public NoticeHolder onCreateViewHolder(ViewGroup parent, int ViewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.notice_list, parent, false);

            return new NoticeHolder(view);
        }

        @Override
        public void onBindViewHolder(NoticeHolder holder, int position){
            Notice_List notice_list = mNotice.get(position);
            holder.bindnotice(notice_list);
        }

        @Override
        public int getItemCount() {
            return mNotice.size();
        }
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case THREAD_QUERY_SUCCESS_INFO:
                    Log.d("NOTICE_HANDLER", "update UI");
                    updateUI();
                    break;
                case SPINNER_THREAD_QUERY_SUCCESS:
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
}
