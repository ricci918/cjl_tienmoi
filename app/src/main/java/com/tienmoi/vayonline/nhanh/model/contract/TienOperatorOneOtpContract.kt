package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienPostOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData


interface TienOperatorOneOtpContract {
    interface TienOperatorOneOtpView {
        fun successTienQueryStatus(data: TienQueryStatusData)
        fun successTienGetOtpOne(data: TienGetOtpOne)
        fun successTienPostOtpOne(data: TienPostOtpOne)
        fun successTienNotify(data: Any)
        fun error()
    }

    interface TienOperatorOneOtpPresenter {
        fun getTienQueryStatus(data: String)
        fun getTienGetOtpOne(cell: String, channel: String, company: String)
        fun getTienPostOtpOne(cell: String, otp: String, channel: String, company: String)
        fun getTienNotify()
    }
}