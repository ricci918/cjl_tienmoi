package com.tienmoi.vayonline.nhanh.model.contract

import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpTwo
import com.tienmoi.vayonline.nhanh.model.data.TienPostOtpTwo
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData


interface TienOperatorTwoOtpContract {
    interface TienOperatorTwoOtpView {
        fun successTienQueryStatus(data: TienQueryStatusData)
        fun successTienGetOtpTwo(data: TienGetOtpTwo)
        fun successTienPostOtpTwo(data: TienPostOtpTwo)
        fun successTienNotify(data: Any)
        fun error()
    }

    interface TienOperatorTwoOtpPresenter {
        fun getTienQueryStatus(data: String)
        fun getTienGetOtpTwo( cell: String, otp: String, channel: String, company: String)
        fun getTienPostOtpTwo(cell: String, otp: String, channel: String, company: String)
        fun getTienNotify()
    }
}