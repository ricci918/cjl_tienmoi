package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienCertificationOneContract
import com.tienmoi.vayonline.nhanh.model.data.TienAddBasicInfoReq
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TienCertificationOnePresenter :
    TienBasePresenter<TienCertificationOneContract.TienCertificationOneView>(),
    TienCertificationOneContract.TienCertificationOnePresenter {
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
    override fun getTienAddBasicInfo(data: TienAddBasicInfoReq) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienAddBasicInfo = TienApiServiceObject.tienAddBasicInfo(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienAddBasicInfo(
                        tienAddBasicInfo
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