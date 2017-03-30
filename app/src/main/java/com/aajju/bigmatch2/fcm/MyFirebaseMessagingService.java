package com.aajju.bigmatch2.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.ArrayMap;

import com.aajju.bigmatch2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by aajju on 2017-02-16.
 * 콜백 :  앱에서 푸시 메시지 수신시 자동으로 호출되는 콜백 메소드
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //파이어베이스 notification 페이지 또는 자체 관리자 페이지에서 발송한 메시지 객체
        System.out.println(remoteMessage.getData());

        //1. 파이어베이스 notificaiton 페이지 > 고급옵션 > 맞춤 데이터의 key, value
        ArrayMap<String, String> customData = (ArrayMap<String, String>) remoteMessage.getData();

        //2. 파이어베이스 notification 페이지 > 메시지 내용
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        // 고급옵션 > 제목
        String label = notification.getTitle();
        // 메시지 내용
        String message = notification.getBody();
        showNotification(label, message);

        System.out.println(remoteMessage.getNotification().getBody());
        //여기서 notification 을 띄웁니다.
    }

    private void showNotification(String label, String body) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(label)
                .setContentText(body)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
