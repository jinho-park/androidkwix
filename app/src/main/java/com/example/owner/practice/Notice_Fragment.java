package com.example.owner.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

/**
 * Created by Owner on 2016-11-11.
 */
public class Notice_Fragment extends Fragment {
    private RecyclerView mNoticeRecyclerView;
    private NoticeAdapter mAdapter;
    private static Vector<Notice_List> vec;
    static QueryThread queryThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.notice, container, false);
        queryThread = new QueryThread(vec, "all");
        queryThread.start();
        mNoticeRecyclerView = (RecyclerView)view.findViewById(R.id.notice_fragment_view);
        mNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        NoticeLab noticeLab = NoticeLab.get(getActivity(), vec);
        Vector<Notice_List> lists = noticeLab.getNotices();

        if(mAdapter == null){
            mAdapter = new NoticeAdapter(lists);
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

        public NoticeHolder(View view){
            super(view);
            view.setOnClickListener(this);
            mNoticetitle = (TextView)view.findViewById(R.id.notice_id);
        }

        public void bindnotice(Notice_List list){
            mNotice = list;
            mNoticetitle.setText(mNotice.getTitles());
        }

        @Override
        public void onClick(View view){
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
}
