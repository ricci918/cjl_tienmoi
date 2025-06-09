package com.tienmoi.vayonline.nhanh.model.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils
import androidx.core.net.toUri
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.tienmoi.vayonline.nhanh.app.TienMyApplication
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.URL
import java.util.Collections

object TienDeviceInfoMethod {
    suspend fun getIp(): String {
        return withContext(Dispatchers.IO) {
            val buff: BufferedReader?
            val urlConnection: HttpURLConnection?
            try {
                val url = URL("http://pv.sohu.com/cityjson")
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 5000 //读取超时
                urlConnection.connectTimeout = 5000 //连接超时
                urlConnection.doInput = true
                urlConnection.useCaches = false
                val responseCode = urlConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) { //找到服务器的情况下,可能还会找到别的网站返回html格式的数据
                    val `is` = urlConnection.inputStream
                    buff = BufferedReader(InputStreamReader(`is`, "UTF-8")) //注意编码，会出现乱码
                    val builder = StringBuilder()
                    var line: String? = null
                    while (buff.readLine().also { line = it } != null) {
                        builder.append(line)
                    }
                    buff.close() //内部会关闭 InputStream
                    urlConnection.disconnect()
                    val startIndex = builder.indexOf("{") //包含[
                    val endIndex = builder.indexOf("}") //包含]
                    val json = builder.substring(startIndex, endIndex + 1) //包含[satrtIndex,endIndex)
                    val jo = JSONObject(json)
                    return@withContext jo.getString("cip")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@withContext ""
        }
    }

    fun getTienNetworkType(context: Context): String {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager?.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            val networkType = networkInfo.type
            if (networkType == ConnectivityManager.TYPE_WIFI) {
                // 当前网络为WiFi网络
                // ...
                return "wifi"
            } else if (networkType == ConnectivityManager.TYPE_MOBILE) {
                return "mobile"
            }
        } else {
            return "none"
        }
        return ""
    }

    /*
     * 判断设备 是否使用代理上网
     * */
    fun isTienWifiProxy(): Boolean {
        val proxyAddress = System.getProperty("http.proxyHost")
        val portStr = System.getProperty("http.proxyPort")
        val proxyPort = (portStr ?: "-1").toInt()
        return !TextUtils.isEmpty(proxyAddress) && proxyPort != -1
    }

    fun isTienUseVpn(): Boolean {
        try {
            val all: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (nif.name.equals("tun0") || nif.name.equals("ppp0")) {
                    return true
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun isTienSimulator(context: Context): Boolean {
        val url = "tel:" + "123456"
        val intent = Intent()
        intent.data = url.toUri()
        intent.action = Intent.ACTION_DIAL
        // 是否可以处理跳转到拨号的 Intent
        val canResolveIntent = intent.resolveActivity(context.packageManager) != null
        return !canResolveIntent
    }

    fun isDeveloperModeEnabled(context: Context): Boolean {
        return Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
        ) != 0
    }

    /**
     * 获取app屏幕亮度
     *
     * @param activity
     * @return
     */
    fun getTienScreenBrightness(activity: Activity): Int {
        var value = 0
        val cr = activity.contentResolver
        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: SettingNotFoundException) {
        }
        return value
    }

    fun isTienRoot(): Boolean {
        val binPath = "/system/bin/su"
        val xBinPath = "/system/xbin/su"
        if (File(binPath).exists() && isTienExecutable(binPath)) return true
        return File(xBinPath).exists() && isTienExecutable(xBinPath)
    }

    private fun isTienExecutable(filePath: String): Boolean {
        var p: Process? = null
        try {
            p = Runtime.getRuntime().exec("ls -l $filePath")
            // 获取返回内容
            val ins = BufferedReader(InputStreamReader(p.inputStream))
            val str: String = ins.readLine()
            if (str.length >= 4) {
                val flag = str[3]
                if (flag == 's' || flag == 'x') return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            p?.destroy()
        }
        return false
    }

    fun getTienCurrentCpuFreq(coreIndex: Int): Long {
        val path = "/sys/devices/system/cpu/cpu$coreIndex/cpufreq/scaling_cur_freq"
        try {
            val br = BufferedReader(FileReader(path))
            val freq = br.readLine()
            br.close()
            return freq.toLong()
        } catch (e: java.lang.Exception) {
            return -1 // 文件不存在或读取失败
        }
    }

    fun getTienMaxCpuFreq(coreIndex: Int): Long {
        val path = "/sys/devices/system/cpu/cpu$coreIndex/cpufreq/cpuinfo_max_freq"
        try {
            val br = BufferedReader(FileReader(path))
            val freq = br.readLine()
            br.close()
            return freq.toLong()
        } catch (e: java.lang.Exception) {
            return -1
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getTienGid(context: Context): String {
        if (TienSharedPreferencesUtil.getTienGaId() != "") {
            return TienSharedPreferencesUtil.getTienGaId()
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val idInfo: AdvertisingIdClient.Info
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
                    withContext(Dispatchers.Main) {
                        return@withContext idInfo.id ?: ""
                    }
                    //do sth with the id
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        return@withContext ""
                    }
                }
            }
        }
        return ""
    }

     @OptIn(DelicateCoroutinesApi::class)
     fun tienInitAgId() {
        if (TienSharedPreferencesUtil.getTienGaId() == "") {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val idInfo: AdvertisingIdClient.Info =
                        AdvertisingIdClient.getAdvertisingIdInfo(TienMyApplication.application)
                    TienSharedPreferencesUtil.putTienGaId(idInfo.id ?: "")
                    //do sth with the id
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}