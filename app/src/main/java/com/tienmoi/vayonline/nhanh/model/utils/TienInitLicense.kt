package com.tienmoi.vayonline.nhanh.model.utils

import com.tienmoi.vayonline.nhanh.app.TienMyApplication
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.trustdecision.mobrisk.TDRisk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object TienInitLicense {
    @OptIn(DelicateCoroutinesApi::class)
    fun license(){
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val license = TienApiServiceObject.tienLicense()
                TienSharedPreferencesUtil.putTienLicence(license.VVoA34L)
                val builder = TDRisk.Builder()
                    .partnerCode(license.KABZZ0r)
                    .appKey(license.dt1WutS)
                    .country(TDRisk.COUNTRY_SG).language("vi")
                TDRisk.initWithOptions(TienMyApplication.application, builder)
                TDRisk.getBlackBox {
                }
            } catch (_: Exception) {

            }
        }
    }
}