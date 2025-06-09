package com.tienmoi.vayonline.nhanh.presenter

import com.tienmoi.vayonline.nhanh.base.TienBasePresenter
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.contract.TienRepaymentDisplayItemsContract
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TienRepaymentDisplayItemsPresenter : TienBasePresenter<TienRepaymentDisplayItemsContract.TienRepaymentDisplayItemsView>(),
    TienRepaymentDisplayItemsContract.TienRepaymentDisplayItemsPresenter {
    @OptIn(DelicateCoroutinesApi::class)
    override fun getTienRepaymentWay(data: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val tienRepaymentWay = TienApiServiceObject.tienRepaymentWay(data)
                withContext(Dispatchers.Main) {
                    mView?.successTienRepaymentWay(
                        tienRepaymentWay
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