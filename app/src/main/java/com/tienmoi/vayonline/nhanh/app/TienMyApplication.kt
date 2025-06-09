package com.tienmoi.vayonline.nhanh.app

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
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
        TienSharedPreferencesUtil.init(application)
        TienInitLicense.license()
        TienDeviceInfoMethod.tienInitAgId()
        TienAFInit.initAF()
        FacebookSdk.setApplicationId(getString(R.string.faceId))
        FacebookSdk.setClientToken(getString(R.string.faceToken))
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
        TienInstallReferrer.initInstallReferrer()
    }
}