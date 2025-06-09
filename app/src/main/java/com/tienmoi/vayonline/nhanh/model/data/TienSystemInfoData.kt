package com.tienmoi.vayonline.nhanh.model.data

data class TienSystemInfoData(
    val aYiaBX8: String,//privacyPolicyUrl
    val xZYUYiy: Boolean,//useOfflineRepay
    val t3ayAYz: String,//appDefaultChannelCode
    val GuE7zXx: String,//consumerHotline
    val x9Hy2Dy: TienAppPortendProductData,//appPortendProduct
    val ythTb7E: String,//email
    val x5fVU3B: String,//companyName
    val rZPrEqC: TienAppTesterPortendProduct,//appTesterPortendProduct
    val kwzXpNJ: String,//permissionPolicyUrl
    val XKruWxQ: Boolean,//skipOperateVerify
    val hGkzoMf: Boolean,//skipCheckProduct
    val xZv00i0: Boolean,//useForceUpgrade
    val S40ZAju: Int,//targetVersionCode
)

class TienAppPortendProductData(
    val WIytgTO: String,//interest
    val vzLNQxd: String,//loanAmount
    val qVeZhSO: Int,//portend
    val I5TlLfY: String,//repaymentAmount
)
class TienAppTesterPortendProduct(
    val oEV6w7z: String,//interest
    val fTL5Eje: String,//loanAmount
    val cVRbNR1: Int,//portend
    val GhJsfKG: String,//repaymentAmount
)


