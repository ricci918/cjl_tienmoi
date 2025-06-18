package com.tienmoi.vayonline.nhanh.model.data

data class TienLoginReq(
    val XWf6ePd: String,//phone
    val y3LbVUW: String,//channelCode
    val ZWWYQmx: String,//verifyCode
    val CQZHaUd: String,//smsCode
    val KBQYJ2H: UserGpsInfo? = null,//userGpsInfo
    val EiRalYU: String,//deviceIdentifier
    val eAmu8uh: String,//appsflyerId
    val GXetpp6: String,//campaignId
    val r5HAkEQ: String,//advertisingId
    val KCcUfPO: Long,//installTime
    val Q9y3gBA: String,//fbc
    val h9j7aeQ: String,//fbp
    val LIHwGXw: String,//deviceToken
)

class UserGpsInfo(
    val f7RlSPx: String? = null,//gps_longitude
    val tLxcIye: String? = null,//gps_latitude
    val agfRKCL: String? = null,//gps_address_street
    val N8Kn2aB: String? = null,//gps_address_province
    val HWdkWmV: String? = null,//gps_address_city
    val QeDMOQJ: String? = null,//gps_address_country
    val V8cPiI7: String? = null,//gps_address_countryCode
    val awb3cve: String? = null,//gps_details
)
