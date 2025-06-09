package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData

import com.tienmoi.vayonline.nhanh.model.data.TienSystemInfoData


interface TienWelcomeContract {
    interface TienWelcomeView {
        fun successTienSystemInfo(data: TienSystemInfoData)
        fun successTienQueryStatus(data: TienQueryStatusData)
    }

    interface TienWelcomePresenter {
        fun getTienSystemInfo()
        fun getTienQueryStatus(data: String)
    }
}