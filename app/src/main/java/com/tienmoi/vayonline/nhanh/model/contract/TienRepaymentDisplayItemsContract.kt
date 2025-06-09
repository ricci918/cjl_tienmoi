package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.RepaymentWayData

interface TienRepaymentDisplayItemsContract {
    interface TienRepaymentDisplayItemsView {
        fun successTienRepaymentWay(data: RepaymentWayData)
    }

    interface TienRepaymentDisplayItemsPresenter {
        fun getTienRepaymentWay(data: String)
    }
}