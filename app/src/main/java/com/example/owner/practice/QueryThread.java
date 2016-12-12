package com.example.owner.practice;

import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by Owner on 2016-11-18.
 */
public class QueryThread extends Thread {
    final String IP = "192.168.56.1";
    final int PORT = 8000;
    final String TAG = "INTERNET";
    private Socket sock;
    private PrintWriter pw;
    private ObjectInputStream Ois;
    private boolean INTERNET_CONNET = false;
    private String message;
    private Vector<Notice_List> vec;
    private Notice_List nl;

    public QueryThread(Vector<Notice_List> vec, String m){
        this.message = m;
        this.vec = vec;
    }

    @Override
    public void run(){
        try {
            if(!INTERNET_CONNET) {
                sock = new Socket(IP, PORT);
                INTERNET_CONNET = true;
            }
            pw = new PrintWriter(sock.getOutputStream());
            Ois = new ObjectInputStream(sock.getInputStream());
            pw.println(message);
            pw.flush();
            while((nl = (Notice_List)Ois.readObject()) != null){
                vec.add(nl);
            }
            Log.d(TAG, vec.get(1).getUrls());
        }catch (IOException e){
            Log.d(TAG, e.toString());
        }catch (ClassNotFoundException e){
            Log.d(TAG, e.toString());
        }
    }
}
