package com.example.owner.practice;

import android.util.Log;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class QueryThread extends Thread {
    final String IP = "192.168.0.112";
    final int PORT = 8000;
    final String TAG = "INTERNET";
    private Socket sock;
    private PrintWriter pw;
    private ObjectInputStream Ois;
    private String message;
    private Notice_List nl;
    private String[] value;
    Notice_Fragment mContext;
    NotificationActivity context;
    Searchactivity searchactivity;
    Vector<Notice_List> mNoticelist;

    public QueryThread(String m, Notice_Fragment mContext, Vector<Notice_List> notice){
        this.message = m;
        this.mContext =mContext;
        this.mNoticelist = notice;
    }

    public QueryThread(String m , NotificationActivity mContext, Vector<Notice_List> list){
        this.message = m;
        this.context = mContext;
        mNoticelist = list;
    }

    public QueryThread(String m , Searchactivity context, Vector<Notice_List> notice){
        this.message = m;
        this.searchactivity = context;
        mNoticelist = notice;
    }

    @Override
    public void run(){
        try {
            Log.d(TAG, "Connect");
            sock = new Socket(IP, PORT);
            Log.d(TAG, message);
            pw = new PrintWriter(sock.getOutputStream());
            Ois = new ObjectInputStream(sock.getInputStream());
            value = message.split("/");
            Log.d(TAG, value[0]);
            pw.println(message);
            pw.flush();

            while(!(nl=(Notice_List)Ois.readObject()).getTitles().equals("end")) {
                Log.d(TAG, nl.getTitles());
                mNoticelist.add(nl);
            }

            if(value[0].equals("new")) {
                Log.d(TAG, "Alarm");
                    context.handler.sendEmptyMessage(context.QUERY_THREAD_OK);
            }else if(value[0].equals("sch")){
                searchactivity.handler.sendEmptyMessage(searchactivity.SEARCH_THREAD_OK);
            } else{
                //get data complete and send success message
                if(mContext != null) {
                    mContext.handler.sendEmptyMessage(mContext.THREAD_QUERY_SUCCESS_INFO);
                }
            }
        }catch (IOException e){
            Log.d(TAG, e.toString());
        }catch (ClassNotFoundException e){
            Log.d(TAG, "Class Not Found");
        }
    }
}
