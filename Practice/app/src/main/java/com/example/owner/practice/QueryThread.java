package com.example.owner.practice;

import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class QueryThread extends Thread {
    final String IP = "192.168.0.101";
    final int PORT = 8000;
    final String TAG = "INTERNET";
    private Socket sock;
    private PrintWriter pw;
    private ObjectInputStream Ois;
    private boolean INTERNET_CONNET = false;
    private String message;
    private Notice_List nl;
    Notice_Fragment mContext;
    Vector<Notice_List> mNoticelist;

    public QueryThread(String m, Notice_Fragment mContext, Vector<Notice_List> notice){
        this.message = m;
        this.mContext =mContext;
        this.mNoticelist = notice;
    }

    @Override
    public void run(){
        try {
            if(!INTERNET_CONNET) {
                sock = new Socket(IP, PORT);
                INTERNET_CONNET = true;
            }
            Log.d(TAG, "Connect");
            pw = new PrintWriter(sock.getOutputStream());
            Ois = new ObjectInputStream(sock.getInputStream());
            pw.println(message);
            pw.flush();
            nl=(Notice_List)Ois.readObject();
            Log.d(TAG, nl.getTitles());
            mNoticelist.add(nl);

            //get data complete and send success message
            mContext.handler.sendEmptyMessage(mContext.THREAD_QUERY_SUCCESS_INFO);
        }catch (IOException e){
            Log.d(TAG, e.toString());
        }catch (ClassNotFoundException e){
            Log.d(TAG, "Class Not Found");
        }
    }
}