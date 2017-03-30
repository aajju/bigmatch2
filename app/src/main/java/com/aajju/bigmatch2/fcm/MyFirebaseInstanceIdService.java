package com.aajju.bigmatch2.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by aajju on 2017-02-16.
 *
 *  일반적으로는 딱히 신경 쓸 필요가 없으나,
 *
 *  Firebase Notification 페이지에서 푸시를 안쏘고,
 *  자체 개발한 관리자 페이지에서 푸시를 쓸 때는 토큰이 서버측 db에 저장되어있어야 함
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        //서버에 보관해야할 토큰
        String refreshToken = FirebaseInstanceId.getInstance().getToken();


    }
}
