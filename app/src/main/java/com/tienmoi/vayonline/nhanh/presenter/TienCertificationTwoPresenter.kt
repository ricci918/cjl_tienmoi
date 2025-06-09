package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienCertificationTwoContract
import com.tienmoi.vayonline.nhanh.model.data.TienAddAttachInfoReq
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienCertificationTwoPresenter :
    TienBasePresenter<TienCertificationTwoContract.TienCertificationTwoView>(),
    TienCertificationTwoContract.TienCertificationTwoPresenter {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienUserFieldCode() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienUserFieldCode = TienApiServiceObject.tienUserFieldCode()
                withContext(Dispatchers.Main) {
                    mView?.successTienUserFieldCode(
                        tienUserFieldCode
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
    override fun getTienAreaList() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienAreaList = TienApiServiceObject.tienAreaList()
                withContext(Dispatchers.Main) {
                    mView?.successTienAreaList(
                        tienAreaList
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
    override fun getTienAddAttachInfo(data: TienAddAttachInfoReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienAddAttachInfo = TienApiServiceObject.tienAddAttachInfo(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienAddAttachInfo(
                        tienAddAttachInfo
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
}