package com.tienmoi.vayonline.nhanh.model.data

data class TienLicenseSuccessData(
    val code : Int,
    val message : String,
    val sequence_id : String,
    val liveness_id : String,
    val image : String,
    val score : Double,
    val device_risk_tag : Array<String>,
    val device_risk_level : Int,
)
