package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienInfoData

interface TienInfoDataContract {
    interface TienInfoDataView {
        fun successTienInfo(data: TienInfoData)
    }

    interface TienInfoDataPresenter {
        fun getTienInfo()
    }
}