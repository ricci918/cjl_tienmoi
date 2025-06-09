package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq

interface TienLoanConfirmationContract {
    interface TienLoanConfirmationView {
        fun successTienInfo(data: TienInfoData)
        fun successTienAddVayHub(data: Any)
        fun successTienAcquisition(data: Any)
        fun successTienCreate(data: Any)
        fun error()
    }

    interface TienLoanConfirmationPresenter {
        fun getTienInfo()
        fun getTienAddVayHub()
        fun getTienAcquisition(data: TienAcquisitionReq)
        fun getTienCreate(data: TienOrderCreateReq)
    }
}