package com.tienmoi.vayonline.nhanh.app

import android.app.Application
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.utils.TienAFInit
import com.tienmoi.vayonline.nhanh.model.utils.TienDeviceInfoMethod
import com.tienmoi.vayonline.nhanh.model.utils.TienInitLicense
import com.tienmoi.vayonline.nhanh.model.utils.TienInstallReferrer
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil

class TienMyApplication : Application() {
    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        FacebookSdk.setApplicationId(getString(R.string.faceId))
        FacebookSdk.setClientToken(getString(R.string.faceToken))
        FacebookSdk.sdkInitialize(this)
        TienSharedPreferencesUtil.init(application)
        TienInitLicense.license()
        TienDeviceInfoMethod.tienInitAgId()
        TienAFInit.initAF()
        AppEventsLogger.activateApp(this)
        TienInstallReferrer.initInstallReferrer()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.i("1111111111", "initView: "+token)
        })
    }
}