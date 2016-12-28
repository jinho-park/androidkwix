package com.example.owner.practice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Owner on 2016-12-22.
 */
public class DBManager extends SQLiteOpenHelper {
    final String TAG = "DATABASE";
    private ArrayList<Notice_List> list;
    private String DBname;

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        if(name == "Mynotice.db"){
            DBname = "MY_NOTICE_LIST";
        }else if(name == "Newnotice.db"){
            DBname = "NEW_NOTICE_LIST";
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE MY_NOTICE_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, title TEXT, date TEXT, url TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE NEW_NOTICE_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, title TEXT, date TEXT, url TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void insert(String cate, String title, String date, String url) {
        Log.d(TAG, "Insert");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into "+DBname+" values(null, '" + cate + "','" + title + "','"+date+"','"+url+"');");
        db.close();
    }

    public void update(String cate, String title, String date, String url) {
        Log.d(TAG, "Update");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update "+DBname+" set category = '"+cate+"', date = '"+date+"', url = '"+url+"' where title = '"+title+"';");
        db.close();
    }

    public void delete(String cate, String title, String date, String url) {
        Log.d(TAG, "Delete");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+DBname+" where title = '"+title+"';");
        db.close();
    }

    public ArrayList<Notice_List> getData() {
        Log.d(TAG, "getData");
        SQLiteDatabase db = getReadableDatabase();
        list = new ArrayList<Notice_List>();

        Cursor cursor = db.rawQuery("select * from MY_NOTICE_LIST", null);
        while(cursor.moveToNext()) {
            Notice_List item = new Notice_List();
            item.setCat(cursor.getString(1));
            item.setTitle(cursor.getString(2));
            item.setDate(cursor.getString(3));
            item.setUrls(cursor.getString(4));
            list.add(item);
        }
        Log.d(TAG, "get Data size" + list.size());

        return list;
    }
}
