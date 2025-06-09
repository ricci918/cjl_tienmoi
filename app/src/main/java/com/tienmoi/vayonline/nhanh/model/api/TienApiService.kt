package com.tienmoi.vayonline.nhanh.model.api

import com.tienmoi.vayonline.nhanh.model.data.RepaymentWayData
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddAttachInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddBankInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAddBasicInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAreaListData
import com.tienmoi.vayonline.nhanh.model.data.TienBankListData
import com.tienmoi.vayonline.nhanh.model.data.TienCommitReq
import com.tienmoi.vayonline.nhanh.model.data.TienHttpResult
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienLicenseData
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentData
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
import com.tienmoi.vayonline.nhanh.model.data.TienResultReq
import com.tienmoi.vayonline.nhanh.model.data.TienSendSMSCodeReq
import com.tienmoi.vayonline.nhanh.model.data.TienSystemInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData
import com.tienmoi.vayonline.nhanh.model.data.TienWithdrawReq
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TienApiService {

    @GET("/Xqm5ED7/P27lq5T")
    suspend fun tienSystemInfo(): TienHttpResult<TienSystemInfoData>

    @GET("/Xqm5ED7/Nb5oEGw")
    suspend fun tienQueryStatus(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<TienQueryStatusData>

    @POST("/Xqm5ED7/Ka3FXDY")
    suspend fun tienSendSMSCode(
        @Body data: TienSendSMSCodeReq
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/lCu5lwI")
    suspend fun tienLogin(
        @Body data: TienLoginReq
    ): TienHttpResult<TienLoginData>

    @GET("/Xqm5ED7/vHuIOl3")
    suspend fun tienUserFieldCode(): TienHttpResult<TienUserFieldCodeData>


    @POST("/Xqm5ED7/cWbaGCy")
    suspend fun tienAddBasicInfo(
        @Body data: TienAddBasicInfoReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @GET("/Xqm5ED7/qEoQKJl")
    suspend fun tienAreaList(): TienHttpResult<MutableList<TienAreaListData>>

    @POST("/Xqm5ED7/B2hhKu0")
    suspend fun tienAddAttachInfo(
        @Body data: TienAddAttachInfoReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @GET("/Xqm5ED7/X7cgnHA")
    suspend fun tienBankList(): TienHttpResult<MutableList<TienBankListData>>

    @GET("/Xqm5ED7/jkyGyXK")
    suspend fun tienLicense(): TienHttpResult<TienLicenseData>

    @GET("/Xqm5ED7/tvoVXX5")
    suspend fun tienInfo(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<TienInfoData>

    @POST("/Xqm5ED7/qH4O7Tb")
    suspend fun tienAddBankInfo(
        @Body data: TienAddBankInfoReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/Nk8wil7")
    suspend fun tienIdentity(
        @Body body: RequestBody,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/y8ArNr6")
    suspend fun tienResult(
        @Body data: TienResultReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/V4OrQBI")
    suspend fun tienAddVayHub(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/FZ14bdQ")
    suspend fun tienAcquisition(
        @Body data: TienAcquisitionReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/yVoR95d")
    suspend fun tienCreate(
        @Body data: TienOrderCreateReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @GET("/Xqm5ED7/vAGpmKo")
    suspend fun tienOrder(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<MutableList<TienOrderData>>

    @GET("/Xqm5ED7/xBCbSwf")
    suspend fun tienRepayment(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<MutableList<TienRepaymentData>>

    @GET("/Xqm5ED7/s8OnUkg")
    suspend fun tienRepaymentAccomplish(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<TienRepaymentAccomplishData>

    @GET("/Xqm5ED7/ZboehiK")
    suspend fun tienCheck(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/Z489Swa")
    suspend fun tienWithdraw(
        @Body data: TienWithdrawReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/th0OLzo")
    suspend fun tienRenew(
        @Body data: TienRenewReq,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @GET("/Xqm5ED7/RFuMrnS")
    suspend fun tienOrderDetail(
        @Query("hFAHb1R") orderCode: String,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<TienOrderDetailData>

    @GET("/Xqm5ED7/ez2hvnH")
    suspend fun tienRepaymentWay(
        @Query("i5bNlqF") orderCode: String,
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<RepaymentWayData>

    @POST("/Xqm5ED7/jbaIJSR")
    suspend fun tienGetOtpOne(@Body body: RequestBody): TienGetOtpOne

    @POST("/Xqm5ED7/AMyjPzX")
    suspend fun tienGetOtpTwo(@Body body: RequestBody): TienGetOtpTwo

    @POST("/Xqm5ED7/Wpa50V5")
    suspend fun tienPostOtpOne(@Body body: RequestBody): TienPostOtpOne

    @POST("/Xqm5ED7/nPejtoW")
    suspend fun tienPostOtpTwo(@Body body: RequestBody): TienPostOtpTwo

    @GET("/Xqm5ED7/tRgCVij")
    suspend fun tienNotify(
        @Query("vEFqqPJ") token: String,
        @Query("aVVyzFc") versionName: String,
        @Query("KrwaVbW") packageName: String
    ): TienHttpResult<Any>

    @POST("/Xqm5ED7/Np22ECM")
    suspend fun tienCommit(@Body data: TienCommitReq): TienHttpResult<Any>
}
