package com.example.owner.practice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by HATA on 2016-12-17.
 */

public class Testactivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Date","Receive");
        NotificationManager notifier = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,MainActivity.class);
        PendingIntent pender = PendingIntent.getActivity(context,0,intent1,0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setTicker("새로운 공지사항이 추가되었습니다.");    // 상단바 처음에 뜨는 제목
        builder.setWhen(System.currentTimeMillis());    // 알림이 표시되는 시간
        builder.setContentTitle("새로운 공지사항이 추가되었습니다.");  // 상단바를 내렸을 때 나오는 제목
        builder.setSmallIcon(R.drawable.ic_search_notice);   // 상단바 아이콘
        builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);  // 알림이 올 때 진동과 빛과 소리 다 줌
        builder.setContentIntent(pender);    // 누르게 되면 다음 intent로 넘어감
        builder.setAutoCancel(true);    // 알림을 누르게 되면 이벤트처리와 비슷하게 그 알림바는 자동으로 지워짐
        notifier.notify(123456, builder.build()); // 알림 공지
    }
}
