package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienOrderDetailData

interface TienRepaymentContract {
    interface TienRepaymentView {
        fun successTienOrderDetail(data: TienOrderDetailData)
    }

    interface TienRepaymentPresenter {
        fun getTienOrderDetail(data: String)
    }
}