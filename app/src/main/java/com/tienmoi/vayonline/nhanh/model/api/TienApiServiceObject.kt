package com.tienmoi.vayonline.nhanh.model.api

import com.tienmoi.vayonline.nhanh.app.TienMyApplication
import com.tienmoi.vayonline.nhanh.base.TienBaseRepository
import com.tienmoi.vayonline.nhanh.model.data.RepaymentWayData
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddAttachInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddBankInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddBasicInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAreaListData
import com.tienmoi.vayonline.nhanh.model.data.TienBankListData
import com.tienmoi.vayonline.nhanh.model.data.TienCommitReq
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienLicenseData
import com.tienmoi.vayonline.nhanh.model.data.TienLoginData
import com.tienmoi.vayonline.nhanh.model.data.TienLoginReq
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.data.TienOrderData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderDetailData
import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpTwo
import com.tienmoi.vayonline.nhanh.model.data.TienPostOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienPostOtpTwo
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData
import com.tienmoi.vayonline.nhanh.model.data.TienRenewReq
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentAccomplishData
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentData
import com.tienmoi.vayonline.nhanh.model.data.TienResultReq
import com.tienmoi.vayonline.nhanh.model.data.TienSendSMSCodeReq
import com.tienmoi.vayonline.nhanh.model.data.TienSystemInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData
import com.tienmoi.vayonline.nhanh.model.data.TienWithdrawReq
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.File

object TienApiServiceObject : TienBaseRepository() {
    private val service by lazy { create<TienApiService>() }
    private val versionName = TienSystemUtil.getTienVersionName(TienMyApplication.application)
    private val packageName = TienSystemUtil.getTienPageName(TienMyApplication.application)
    suspend fun tienSystemInfo(
    ): TienSystemInfoData {
        return service.tienSystemInfo().getResponseDataToastNo()
    }

    suspend fun tienQueryStatus(
        data: String
    ): TienQueryStatusData {
        return service.tienQueryStatus(data, versionName, packageName).getResponseDataToastNo()
    }

    suspend fun tienSendSMSCode(
        data: TienSendSMSCodeReq
    ): Any {
        return service.tienSendSMSCode(data).getResponseData()
    }

    suspend fun tienLogin(
        data: TienLoginReq
    ): TienLoginData {
        return service.tienLogin(data).getResponseData()
    }

    suspend fun tienUserFieldCode(): TienUserFieldCodeData {
        return service.tienUserFieldCode().getResponseDataToastNo()
    }

    suspend fun tienAddBasicInfo(
        data: TienAddBasicInfoReq
    ): Any {
        return service.tienAddBasicInfo(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienAreaList(): MutableList<TienAreaListData> {
        return service.tienAreaList().getResponseDataToastNo()
    }

    suspend fun tienAddAttachInfo(
        data: TienAddAttachInfoReq
    ): Any {
        return service.tienAddAttachInfo(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienBankList(): MutableList<TienBankListData> {
        return service.tienBankList().getResponseData()
    }

    suspend fun tienLicense(): TienLicenseData {
        return service.tienLicense().getResponseData()
    }

    suspend fun tienInfo(): TienInfoData {
        return service.tienInfo(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienAddBankInfo(
        data: TienAddBankInfoReq
    ): Any {
        return service.tienAddBankInfo(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienIdentity(
        data: File
    ): Any {
        val fileBody: RequestBody =
            RequestBody.create("image/png".toMediaTypeOrNull(), data)
        val requestBody = getFormBuilder()
            .addFormDataPart("rXJxHE6", data.name, fileBody)
            .build()
        return service.tienIdentity(
            requestBody,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienResult(
        data: TienResultReq
    ): Any {
        return service.tienResult(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienAddVayHub(
    ): Any {
        return service.tienAddVayHub(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienAcquisition(
        data: TienAcquisitionReq
    ): Any {
        return service.tienAcquisition(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienCreate(
        data: TienOrderCreateReq
    ): Any {
        return service.tienCreate(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }

    suspend fun tienOrder(): MutableList<TienOrderData> {
        return service.tienOrder(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienRepayment(): MutableList<TienRepaymentData> {
        return service.tienRepayment(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienRepaymentAccomplish(): TienRepaymentAccomplishData {
        return service.tienRepaymentAccomplish(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienCheck(): Any {
        return service.tienCheck(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienWithdraw(
        data: TienWithdrawReq
    ): Any {
        return service.tienWithdraw(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }


    suspend fun tienRenew(
        data: TienRenewReq
    ): Any {
        return service.tienRenew(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseData()
    }


    suspend fun tienOrderDetail(
        data: String
    ): TienOrderDetailData {
        return service.tienOrderDetail(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienRepaymentWay(
        data: String
    ): RepaymentWayData {
        return service.tienRepaymentWay(
            data,
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }


    suspend fun tienGetOtpOne(
        cell: String, channel: String, company: String
    ): TienGetOtpOne {
        val requestBody = getFormBuilder()
            .addFormDataPart("fjIod4r", cell)
            .addFormDataPart("kOkRBkR", channel)
            .addFormDataPart("fXgD7e4", company)
            .build()
        return service.tienGetOtpOne(requestBody)
    }

    suspend fun tienGetOtpTwo(
        cell: String, otp: String, channel: String, company: String
    ): TienGetOtpTwo {
        val requestBody = getFormBuilder()
            .addFormDataPart("pQcOqSL", cell)
            .addFormDataPart("Qndo9WS", otp)
            .addFormDataPart("rMlhjkt", channel)
            .addFormDataPart("PCVAaZA", company)
            .build()
        return service.tienGetOtpTwo(requestBody)
    }

    suspend fun tienPostOtpOne(
        cell: String, otp: String, channel: String, company: String
    ): TienPostOtpOne {
        val requestBody = getFormBuilder()
            .addFormDataPart("UvvR2m4", cell)
            .addFormDataPart("GTb60JJ", otp)
            .addFormDataPart("N1PB7Ti", channel)
            .addFormDataPart("dcFt0pY", company)
            .build()
        return service.tienPostOtpOne(requestBody)
    }

    suspend fun tienPostOtpTwo(
        cell: String, otp: String, channel: String, company: String
    ): TienPostOtpTwo {
        val requestBody = getFormBuilder()
            .addFormDataPart("zQFdMu2", cell)
            .addFormDataPart("JxEvqoy", otp)
            .addFormDataPart("XSX2Mw4", channel)
            .addFormDataPart("orQBAV2", company)
            .build()
        return service.tienPostOtpTwo(requestBody)
    }

    suspend fun tienNotify(
    ): Any {
        return service.tienNotify(
            TienSharedPreferencesUtil.getUser()?.Jn4mzjv ?: "",
            versionName,
            packageName
        ).getResponseDataToastNo()
    }

    suspend fun tienCommit(data: TienCommitReq): Any {
        return service.tienCommit(data).getResponseData()
    }
}