package com.tienmoi.vayonline.nhanh.presenter

import android.util.Log
import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienLoginContract
import com.tienmoi.vayonline.nhanh.model.data.TienLoginReq
import com.tienmoi.vayonline.nhanh.model.data.TienSendSMSCodeReq
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienLoginPresenter : TienBasePresenter<TienLoginContract.TienLoginView>(),
    TienLoginContract.TienLoginPresenter {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienSendCode(phone: String, channelCode: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienSendSMSCode =
                    TienApiServiceObject.tienSendSMSCode(TienSendSMSCodeReq(phone, channelCode))
                withContext(Dispatchers.Main) {
                    mView?.successTienSendCode(
                        tienSendSMSCode
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        mView?.error()
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienLogin(data: TienLoginReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienLogin = TienApiServiceObject.tienLogin(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienLogin(
                        tienLogin
                    )
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e.message != "" && e.message != null) {
                        mView?.error()
                    }
                }
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienQueryStatus(data: String) {
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