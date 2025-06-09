package com.tienmoi.vayonline.nhanh.model.data

data class TienRepaymentAccomplishData(
    val GmhmJvi: Int,//current
    val zXmyIvj: Int,//pageTotal
    val Yb22hKt: Int,//total
    val B8LM4V8: Int,//pageSize
    val v68J7LH: MutableList<TienContent>,//content
)

class TienContent(
    val a0ZuG3l: String,//agentCode
    val Cr73o8v: String,//agentLogo
    val cBCN8Vt: String,//agentTwitter
    val EF02RZA: String,//agentName
    val NonEI3C: String,//agentHotline
    val hGrvCF5: String,//orderCode
    val oFJSzN1: Int,//repaymentAmount
    val UhIB77e: Long,//settlementTime
    val nCN1pQ4: String,//status
    val gRaBVmO: String,//productName
    val BFRsVWx: Int,//commission
    val kHy37Km: Int,//contractAmount
)