package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienWelcomeContract
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienWelcomePagePresenter : TienBasePresenter<TienWelcomeContract.TienWelcomeView>(),
    TienWelcomeContract.TienWelcomePresenter {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienSystemInfo() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienSystemInfo = TienApiServiceObject.tienSystemInfo()
                withContext(Dispatchers.Main) {
                    mView?.successTienSystemInfo(
                        tienSystemInfo
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        TienToastUtil.myToast(e.message!!)
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienQueryStatus(data :String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienQueryStatus = TienApiServiceObject.tienQueryStatus(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienQueryStatus(
                        tienQueryStatus
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        TienToastUtil.myToast(e.message!!)
                    }
                }
            }
        }
    }
}