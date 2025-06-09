package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderDetailData

interface TienOrderDetailsContract {
    interface TienOrderDetailsView {
        fun successTienOrderDetail(data: TienOrderDetailData)
        fun successTienInfo(data: TienInfoData)
    }

    interface TienOrderDetailsPresenter {
        fun getTienOrderDetail(data: String)
        fun getTienInfo()
    }
}