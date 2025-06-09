package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienAddAttachInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAreaListData
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData

interface TienCertificationTwoContract {
    interface TienCertificationTwoView {
        fun successTienUserFieldCode(data: TienUserFieldCodeData)
        fun successTienAreaList(data: MutableList<TienAreaListData>)
        fun successTienAddAttachInfo(data: Any)
        fun error()
    }

    interface TienCertificationTwoPresenter {
        fun getTienUserFieldCode()
        fun getTienAreaList()
        fun getTienAddAttachInfo(data: TienAddAttachInfoReq)
    }
}