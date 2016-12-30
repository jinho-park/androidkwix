package com.example.owner.practice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Owner on 2016-12-22.
 */
public class NotificationActivity extends BroadcastReceiver {
    final int QUERY_THREAD_OK = 2;
    private Context mContext;
    private Vector<Notice_List> newlist;
    private DBManager dbManager;
    private SharedPreferences prefs;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        prefs = context.getSharedPreferences("pref", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("alarm", false);
        newlist = new Vector<Notice_List>();
        dbManager = new DBManager(context, "Newnotice.db", null, 1);
        if (switchState == true) {
            Log.d("SERVICE", "Thread start");
            QueryThread thread = new QueryThread("new", this, newlist);
            thread.start();
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int j=0;
            switch (msg.what) {
                case QUERY_THREAD_OK:
                    Log.d("SERVICE", "Message ok");
                    int size = prefs.getInt("size", 0);
                    Log.d("test", "size : " + size);
                    if(prefs.getBoolean("keyword", false) == true && size != 0){
                        Log.d("test","keyword");
                        for(int i=0; i< newlist.size(); i++){
                            Notice_List item = newlist.get(i);
                            Log.d("test",item.getTitles().toString());
                            for (int k = 0; k < size; k++) {
                                String v = prefs.getString("list"+k,null);
                                Log.d("test",v);
                                if(item.getTitles().contains(v)){
                                    j++;
                                }
                            }

                        }
                    }
                    dbManager.deleteTable();
                    for (int i = 0; i< newlist.size(); i++){
                        Notice_List item = newlist.get(i);
                        dbManager.insert(item.getCats(), item.getTitles(), item.getDates(), item.getUrls());
                    }
                    Log.d("test", String.valueOf(j));
                    //if(prefs.getBoolean("keyword"))
                    NotificationManager notifier = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                    Intent intent = new Intent(mContext, MainActivity.class);
                    PendingIntent pender = PendingIntent.getActivity(mContext, 0, intent, 0);
                    Notification.Builder builder = new Notification.Builder(mContext);
                    if(j == 0) {
                        builder.setTicker("새로운 공지사항이 추가되었습니다.");    // 상단바 처음에 뜨는 제목
                        builder.setWhen(System.currentTimeMillis());    // 알림이 표시되는 시간
                        builder.setContentTitle("새로운 공지사항이 추가되었습니다.");  // 상단바를 내렸을 때 나오는 제목
                    }else{
                        builder.setTicker("새로운 공지사항"+j+"개 추가되었습니다.");    // 상단바 처음에 뜨는 제목
                        builder.setWhen(System.currentTimeMillis());    // 알림이 표시되는 시간
                        builder.setContentTitle("새로운 공지사항"+j+"개 추가되었습니다.");  // 상단바를 내렸을 때 나오는 제목
                    }
                    builder.setSmallIcon(R.mipmap.ic_launcher);   // 상단바 아이콘
                    builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);  // 알림이 올 때 진동과 빛과 소리 다 줌
                    builder.setContentIntent(pender);    // 누르게 되면 다음 intent로 넘어감
                    builder.setAutoCancel(true);    // 알림을 누르게 되면 이벤트처리와 비슷하게 그 알림바는 자동으로 지워짐
                    notifier.notify(123456, builder.build()); // 알림 공지
                    Log.d("SERVICE", "Notification finish");
                default:
                    break;
            }
        }
    };
}