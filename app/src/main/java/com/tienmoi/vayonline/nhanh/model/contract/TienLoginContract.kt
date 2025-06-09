package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienLoginData
import com.tienmoi.vayonline.nhanh.model.data.TienLoginReq
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData


interface TienLoginContract {
    interface TienLoginView {
        fun successTienSendCode(data: Any)
        fun successTienLogin(data: TienLoginData)
        fun successTienQueryStatus(data: TienQueryStatusData)
        fun error()
    }

    interface TienLoginPresenter {
        fun getTienSendCode(phone: String, channelCode: String)
        fun getTienLogin(data: TienLoginReq)
        fun getTienQueryStatus(data: String)
    }
}