package com.tienmoi.vayonline.nhanh.model.data

data class TienAcquisitionReq(
    var VsOrYVb: TienUserDeviceInfo?,//一般
    var NjNurhK: List<TienUserApplications>?,//应用
) {
    class TienUserDeviceInfo(
        var IkeKNkH: TienDeviceInfo? = null
    )

    class TienDeviceInfo {
        var zWqu9ni //经度
                : String? = null
        var byuDpp6 //纬度
                : String? = null
        var j8N7fgI //开机时间到现在的毫秒数（包括睡眠时间）
                : Long? = null
        var X7MVJGv //允许模拟位置
                : Boolean? = null
        var pEn4dwC //安卓id
                : String? = null
        var R9L5hij //电池电量
                : Int? = null
        var myZ2fkQ //电池百分比
                : Int? = null
        var v7UymLx //主板名称
                : String? = null
        var ny8OD0E //最后一次启动时间
                : Long? = null
        var LV9i3ho //设备品牌
                : String? = null
        var RYHcCro //屏幕亮度
                : String? = null
        var iAcaOxn //APP版本号
                : String? = null
        var p6FfdLv //设备内核
                : Int? = null
        var jiFj718 //cpu名称
                : String? = null
        var idcXt2u //cpu当前频率
                : Long? = null
        var UPM60hE //cpu最大频率
                : Long? = null
        var LmZegpA //设备当前时间
                : Long? = null
        var Gb9ZFe7 //谷歌广告id
                : String? = null
        var sM4R7qT //ip
                : String? = null
        var SCmZbjL //是否为模拟器
                : Int? = null
        var SiKo5ql //是否root
                : Int? = null
        var LDcgUAu //是否交流充电
                : Boolean? = null
        var BIlRPPn //是否正在充电
                : Boolean? = null
        var Dkw9l8x //是否USB充电
                : Boolean? = null
        var QXsmO3v //是否为开发者模式
                : Boolean? = null
        var BttATtX //充电状态
                : Boolean? = null
        var AT3TJi6 //是否使用代理
                : Boolean? = null
        var ngPryDi //是否使用vpn
                : Boolean? = null
        var UYGqGXo //语言
                : String? = null
        var CLYPCmy //机身存储大小
                : String? = null
        var GEDg87r //机身可用存储大小
                : String? = null
        var UPovKIM //设备型号
                : String? = null
        var mHIJDmr //网络类型 2G、3G、4G、5G、wifi、other、none
                : String? = null
        var V36qXyB //网络运营商名称
                : String? = null
        var EDPl08p //总内存大小
                : Long? = null
        var eQX6tv1 //可用内存大小
                : Long? = null
        var gDOXhO9 //系统版本
                : String? = null
        var KIqWfIk //屏幕分辨率
                : String? = null
        var nPKfwgF //分辨率高
                : Int? = null
        var n1XKsQH //分辨率宽
                : Int? = null
        var FtTWVOd //SDK版本
                : String? = null
        var kYGm2Qv //时区
                : String? = null
        var JOssFl8 //wifi的个数
                : Int? = null
        var yLQGKKC //当前wifi名称
                : String? = null
        var E3uS8KW //当前wifi mac地址
                : String? = null
        var AXegzQv
                : Wifi? = null
    }

    class Wifi {
        var maWZbWa
                : String? = null
        var ieTURqu
                : String? = null
        var GP6HtEx
                : String? = null
        var Wz3oomm
                : String? = null
    }


    data class TienUserApplications(
        var WTXxPAS //用户id
        : Int? = null,
        var zfIn7jr//订单id
        : Int? = null,
        var u2JS7iF//数据源
        : Int? = null,
        var KbJ0sVp//App名
        : String? = null,
        var zfquUR7//包名
        : String? = null,
        var NSKc5xu//安装时间（13位时间戳）
        : Long? = null,
        var gWi2QLg//更新时间（13位时间戳）
        : Long? = null,
        var JtFgGMN//版本
        : String? = null,
        var HNz5DNA//版本号
        : Long? = null,
        var OzhnGES//是否为系统应用
        : Int? = null

    ) {
        override fun equals(other: Any?): Boolean {
            return other is TienUserApplications && this.zfquUR7 == other.zfquUR7
        }

        override fun hashCode(): Int = zfquUR7.hashCode()
    }
}

