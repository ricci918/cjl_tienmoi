package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddBankInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienBankListData
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.data.TienResultReq
import java.io.File

interface TienCertificationThreeContract {
    interface TienCertificationThreeView {
        fun successTienBankList(data: MutableList<TienBankListData>)
        fun successTienInfo(data: TienInfoData)
        fun successTienAddBankInfo(data: Any)
        fun successTienIdentity(data: Any)
        fun successTienResult(data: Any)
        fun successTienAddVayHub(data: Any)
        fun successTienAcquisition(data: Any)
        fun successTienCreate(data: Any)
        fun error()

    }

    interface TienCertificationThreePresenter {
        fun getTienBankList()
        fun getTienInfo()
        fun getTienAddBankInfo(data: TienAddBankInfoReq)
        fun getTienIdentity(data: File)
        fun getTienResult(data: TienResultReq)
        fun getTienAddVayHub()
        fun getTienAcquisition(data: TienAcquisitionReq)
        fun getTienCreate(data: TienOrderCreateReq)
    }
}