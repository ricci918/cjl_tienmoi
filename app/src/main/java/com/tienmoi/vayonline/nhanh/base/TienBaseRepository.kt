package com.tienmoi.vayonline.nhanh.base

import com.tienmoi.vayonline.nhanh.model.utils.TienHttpUtil
import okhttp3.MultipartBody

open class TienBaseRepository {
    inline fun <reified T> create(): T {
        return TienHttpUtil.getService().create(T::class.java)
    }
    fun getFormBuilder(): MultipartBody.Builder {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
    }
}