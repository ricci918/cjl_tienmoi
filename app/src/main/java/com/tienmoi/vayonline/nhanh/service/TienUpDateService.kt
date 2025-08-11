package com.tienmoi.vayonline.nhanh.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.greenrobot.eventbus.EventBus
import java.util.Timer
import java.util.TimerTask

class TienUpDateService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val task: TimerTask = object : TimerTask() {
            override fun run() {
                EventBus.getDefault().post("refresh")
            }
        }
        Timer().schedule(task, 0, (120 * 1000).toLong())
        return START_STICKY
    }

}