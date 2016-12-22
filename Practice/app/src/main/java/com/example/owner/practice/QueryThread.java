package com.example.owner.practice;

import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class QueryThread extends Thread {
<<<<<<< HEAD
    final String IP = "192.168.0.103";
=======
    final String IP = "192.168.0.101";
>>>>>>> 582e594002adf1f123f4f98fdcf5ca68e162dc81
    final int PORT = 8000;
    final String TAG = "INTERNET";
    private Socket sock;
    private PrintWriter pw;
    private ObjectInputStream Ois;
    private String message;
    private Notice_List nl;
    Notice_Fragment mContext;
    NotificationActivity context;
    Vector<Notice_List> mNoticelist;

    public QueryThread(String m, Notice_Fragment mContext, Vector<Notice_List> notice){
        this.message = m;
        this.mContext =mContext;
        this.mNoticelist = notice;
    }

    public QueryThread(String m , NotificationActivity mContext){
        this.message = m;
        this.context = mContext;
    }

    @Override
    public void run(){
        try {
            Log.d(TAG, "Connect");
            sock = new Socket(IP, PORT);
            pw = new PrintWriter(sock.getOutputStream());
            Ois = new ObjectInputStream(sock.getInputStream());
            pw.println(message);
            pw.flush();
<<<<<<< HEAD

            while(!(nl=(Notice_List)Ois.readObject()).getTitles().equals("end")) {
                Log.d(TAG, nl.getTitles());
                mNoticelist.add(nl);
            }
            //get data complete and send success message
            mContext.handler.sendEmptyMessage(mContext.THREAD_QUERY_SUCCESS_INFO);

=======
            nl=(Notice_List)Ois.readObject();
            Log.d(TAG, nl.getTitles());
            if(message.equals("new")){
                Log.d(TAG, "Alarm");
                context.handler.sendEmptyMessage(context.QUERY_THREAD_OK);
            }else {
                mNoticelist.add(nl);

                //get data complete and send success message
                mContext.handler.sendEmptyMessage(mContext.THREAD_QUERY_SUCCESS_INFO);
            }
>>>>>>> 582e594002adf1f123f4f98fdcf5ca68e162dc81
        }catch (IOException e){
            Log.d(TAG, e.toString());
        }catch (ClassNotFoundException e){
            Log.d(TAG, "Class Not Found");
        }
    }
}
