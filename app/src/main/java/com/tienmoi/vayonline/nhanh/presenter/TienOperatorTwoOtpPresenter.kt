package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienOperatorTwoOtpContract
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienOperatorTwoOtpPresenter :
    TienBasePresenter<TienOperatorTwoOtpContract.TienOperatorTwoOtpView>(),
    TienOperatorTwoOtpContract.TienOperatorTwoOtpPresenter {

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
    override fun getTienGetOtpTwo(cell: String, otp: String, channel: String, company: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienGetOtpTwo = TienApiServiceObject.tienGetOtpTwo(cell, otp, channel, company)
                withContext(Dispatchers.Main) {
                    mView?.successTienGetOtpTwo(
                        tienGetOtpTwo
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
    override fun getTienPostOtpTwo(cell: String, otp: String, channel: String, company: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienPostOtpTwo =
                    TienApiServiceObject.tienPostOtpTwo(cell, otp, channel, company)
                withContext(Dispatchers.Main) {
                    mView?.successTienPostOtpTwo(
                        tienPostOtpTwo
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