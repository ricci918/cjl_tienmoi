package com.tienmoi.vayonline.nhanh.model.utils

import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.tienmoi.vayonline.nhanh.app.TienMyApplication

object TienInstallReferrer {
    private lateinit var referrerClient: InstallReferrerClient
    fun initInstallReferrer() {
        referrerClient = InstallReferrerClient.newBuilder(TienMyApplication.application).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> {
                        // Connection established.
                        val response = referrerClient.installReferrer
                        referrerClient.endConnection()
                    }

                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                        // API not available on the current Play Store app.
                    }

                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                        // Connection couldn't be established.
                    }
                }
            }

            override fun onInstallReferrerServiceDisconnected() {

            }
        })
    }
}