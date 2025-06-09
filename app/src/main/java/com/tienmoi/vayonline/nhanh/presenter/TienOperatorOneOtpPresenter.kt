package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienOperatorOneOtpContract
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienOperatorOneOtpPresenter :
    TienBasePresenter<TienOperatorOneOtpContract.TienOperatorOneOtpView>(),
    TienOperatorOneOtpContract.TienOperatorOneOtpPresenter {

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

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienGetOtpOne(cell: String, channel: String, company: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienGetOtpOne = TienApiServiceObject.tienGetOtpOne(cell, channel, company)
                withContext(Dispatchers.Main) {
                    mView?.successTienGetOtpOne(
                        tienGetOtpOne
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    mView?.error()
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienPostOtpOne(cell: String, otp: String, channel: String, company: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienPostOtpOne =
                    TienApiServiceObject.tienPostOtpOne(cell, otp, channel, company)
                withContext(Dispatchers.Main) {
                    mView?.successTienPostOtpOne(
                        tienPostOtpOne
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    mView?.error()
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienNotify() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienNotify = TienApiServiceObject.tienNotify()
                withContext(Dispatchers.Main) {
                    mView?.successTienNotify(
                        tienNotify
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