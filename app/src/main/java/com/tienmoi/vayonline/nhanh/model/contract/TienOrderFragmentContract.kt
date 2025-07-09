package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.data.TienOrderData
import com.tienmoi.vayonline.nhanh.model.data.TienRenewReq
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentAccomplishData
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentData
import com.tienmoi.vayonline.nhanh.model.data.TienWithdrawReq

interface TienOrderFragmentContract {
    interface TienOrderFragmentView {
        fun successTienOrder(data: MutableList<TienOrderData>)
        fun successTienRepayment(data: MutableList<TienRepaymentData>)
        fun successTienRepaymentAccomplish(data: TienRepaymentAccomplishData)
        fun successTienCheck(data: Any)
        fun successTienAcquisition(data: Any)
        fun successTienWithdraw(data: Any)
        fun successTienRenew(data: Any)
        fun successTienInfo(data: TienInfoData)
        fun successTienCreate(data: Any)
        fun error()
    }

    interface TienOrderFragmentPresenter {
        fun getTienOrder()
        fun getTienRepayment()
        fun getTienRepaymentAccomplish()
        fun getTienCheck()
        fun getTienAcquisition(data: TienAcquisitionReq)
        fun getTienWithdraw(data: TienWithdrawReq)
        fun getTienRenew(data: TienRenewReq)
        fun getTienInfo()
        fun getTienCreate(data: TienOrderCreateReq)
    }
}