package com.tienmoi.vayonline.nhanh.model.utils
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.tienmoi.vayonline.nhanh.app.TienMyApplication

object TienAFInit {
    fun initAF(){
        val appsFlyerConversion = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                val campaignId: String = p0?.get("campaign_id").toString()
                TienSharedPreferencesUtil.putTienCampaignId(campaignId)
            }

            override fun onConversionDataFail(p0: String?) {
            }

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(p0: String?) {
            }
        }
        AppsFlyerLib.getInstance().init("vZeRKNxNbsjZzZ7GttgbJZ", appsFlyerConversion, TienMyApplication.application)
        AppsFlyerLib.getInstance().start(TienMyApplication.application)
    }
}