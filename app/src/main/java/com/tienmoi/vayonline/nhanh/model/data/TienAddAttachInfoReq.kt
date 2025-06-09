package com.tienmoi.vayonline.nhanh.model.data

data class TienAddAttachInfoReq(
    val ejSM2L4: Int, //job
    val LSfcdva: Int, //salary
    val NshOjwC: String, //companyName
    val OE9KdFE: String, //companyPhone
    val Kg7QQBD: String, //livingProvince
    val aNiLxYj: String, //livingCity
    val LyKO6sg: String, //livingAddress
    val Ov9fecG: List<TienAttach>
)

class TienAttach(
    val vVQ39HY: String,//name
    val MRexkX4: String,//phone
    val ucEB2YB: Int,//relation
    val yt9YESt: Int//order
)