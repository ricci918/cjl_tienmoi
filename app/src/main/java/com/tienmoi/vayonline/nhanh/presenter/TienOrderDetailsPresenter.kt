package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienOrderDetailsContract
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TienOrderDetailsPresenter : TienBasePresenter<TienOrderDetailsContract.TienOrderDetailsView>(),
    TienOrderDetailsContract.TienOrderDetailsPresenter {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienOrderDetail(data: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienOrderDetail = TienApiServiceObject.tienOrderDetail(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienOrderDetail(
                        tienOrderDetail
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
    override fun getTienInfo() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienInfo = TienApiServiceObject.tienInfo()
                withContext(Dispatchers.Main) {
                    mView?.successTienInfo(
                        tienInfo
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