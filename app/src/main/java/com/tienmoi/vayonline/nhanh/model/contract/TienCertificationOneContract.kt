package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienAddBasicInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData

interface TienCertificationOneContract {
    interface TienCertificationOneView{
        fun successTienUserFieldCode(data: TienUserFieldCodeData)
        fun successTienAddBasicInfo(data: Any)
        fun error()
    }

    interface TienCertificationOnePresenter{
        fun getTienUserFieldCode()
        fun getTienAddBasicInfo(data: TienAddBasicInfoReq)
    }
}