package com.example.owner.practice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Vector;

public class Notice_Fragment extends Fragment {
    final static int THREAD_QUERY_SUCCESS_INFO = 1;
    private RecyclerView mNoticeRecyclerView;
    private NoticeAdapter mAdapter;
    static QueryThread queryThread;
    DBManager dbManager;
    Vector<Notice_List> notice_lists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.notice, container, false);
        notice_lists = new Vector<Notice_List>();
        dbManager = new DBManager(getContext(), "Mynotice.db", null , 1);
        queryThread = new QueryThread("all", this, notice_lists);
        queryThread.start();
        mNoticeRecyclerView = (RecyclerView)view.findViewById(R.id.notice_fragment_view);
        mNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
                    updateUI();
                    break;
                default:
                    break;
            }
        }
    };
}