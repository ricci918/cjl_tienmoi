package com.tienmoi.vayonline.nhanh.model.data

data class TienInfoData(
    val QaA0CmZ: String,//name
    val iaqWeRm: String,//bankCardNo
    val JtMhKBI: DefaultPaymentMethod, //defaultPaymentMethod
    val QLuTvkJ: String,//idCardNumber
    val BXkEwmn: Boolean,//orderCreated
    val WcrIiHm: Boolean,//tester
)

class DefaultPaymentMethod(
    val GS4VVmR: String,
    val bR76Dxu: String,
    val FTgRcOG: String,
    val hDkDi29: Boolean
)