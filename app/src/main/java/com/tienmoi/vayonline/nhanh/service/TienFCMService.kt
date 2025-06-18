package com.tienmoi.vayonline.nhanh.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil

class TienFCMService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        TienSharedPreferencesUtil.putTienFCM(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}