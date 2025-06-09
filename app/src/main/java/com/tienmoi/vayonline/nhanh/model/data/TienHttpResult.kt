package com.tienmoi.vayonline.nhanh.model.data

import android.os.Looper
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable

class TienHttpResult<T>(
    val Zy7TUhY: String,
    val thALJj6: Int,
    val pUoaKh2: Boolean,
    val LrLjFWG: T
) : Serializable {

    fun getResponseData(): T {
        return when (pUoaKh2) {
            true -> {
                LrLjFWG
            }
            else -> {
                GlobalScope.launch{
                    withContext(Dispatchers.Main) {
                        if (Zy7TUhY !=null){
                            TienToastUtil.myToast(Zy7TUhY)
                        }
                    }
                }
                throw Exception(Zy7TUhY)
            }
        }
    }

    fun getResponseDataToastNo(): T {
        return when (pUoaKh2) {
            true -> {
                LrLjFWG
            }
            else -> {
                throw Exception(Zy7TUhY)
            }
        }
    }
}