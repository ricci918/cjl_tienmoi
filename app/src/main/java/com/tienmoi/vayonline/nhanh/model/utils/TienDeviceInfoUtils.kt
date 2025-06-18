package com.tienmoi.vayonline.nhanh.model.utils

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.SystemClock
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import androidx.core.app.ActivityCompat
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import java.util.Locale
import java.util.TimeZone


object TienDeviceInfoUtils {
    suspend fun getTienDeviceInfo(
        context: Activity
    ): TienAcquisitionReq.TienDeviceInfo {
        val deviceInfo = TienAcquisitionReq.TienDeviceInfo()
        deviceInfo.apply {
            val coarseLocation = TienDeviceInfoMethod.tienGetCoarseLocation(context)
            zWqu9ni = coarseLocation.first.toString()
            byuDpp6 = coarseLocation.second.toString()
            j8N7fgI = SystemClock.elapsedRealtime()
            val isOpen = Settings.Secure.getInt(
                context.contentResolver,
                Settings.Secure.ALLOW_MOCK_LOCATION,
                0
            ) != 0
            X7MVJGv = isOpen
            val andId: String =
                Settings.System.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            pEn4dwC = andId
            try {
                val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                val batteryStatusIntent = context.registerReceiver(null, intentFilter)
                val chargeStatus =
                    batteryStatusIntent!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val isCharging = chargeStatus == BatteryManager.BATTERY_STATUS_CHARGING ||
                        chargeStatus == BatteryManager.BATTERY_STATUS_FULL
                val chargePlug = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
                val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
                val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
                val quantity = batteryStatusIntent.getIntExtra("level", 0)
                R9L5hij = quantity
                myZ2fkQ = quantity
                BIlRPPn = isCharging
                Dkw9l8x = usbCharge
                LDcgUAu = acCharge
                BttATtX = isCharging
            } catch (e: Exception) {
                e.printStackTrace()
            }
            LV9i3ho = Build.BRAND
            FtTWVOd = Build.VERSION.SDK_INT.toString()
            v7UymLx = Build.BOARD
            gDOXhO9 = Build.VERSION.RELEASE
            UPovKIM = Build.MODEL
            RYHcCro = TienDeviceInfoMethod.getTienScreenBrightness(context).toString()
            SiKo5ql = if (TienDeviceInfoMethod.isTienRoot()) {
                1
            } else {
                0
            }

            AT3TJi6 = TienDeviceInfoMethod.isTienWifiProxy()
            ngPryDi = TienDeviceInfoMethod.isTienUseVpn()
            val local = Locale.getDefault()
            UYGqGXo = local.language
            val dm = DisplayMetrics()
            KIqWfIk = Build.DISPLAY
            context.windowManager.defaultDisplay.getMetrics(dm)
            nPKfwgF = dm.heightPixels
            n1XKsQH = dm.widthPixels
            val telManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            V36qXyB = telManager.networkOperatorName
            SCmZbjL = if (TienDeviceInfoMethod.isTienSimulator(context)) {
                1
            } else {
                0
            }
            QXsmO3v = TienDeviceInfoMethod.isDeveloperModeEnabled(context)
            val statFs = StatFs(Environment.getExternalStorageDirectory().path)
            val totalSize: Long = statFs.totalBytes
            val availableSize: Long = statFs.availableBytes
            CLYPCmy = totalSize.toString()
            GEDg87r = availableSize.toString()
            kYGm2Qv = TimeZone.getDefault().id

            val mWifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_WIFI_STATE
                )
            ) {
                // 取得WifiInfo对象
                sM4R7qT = TienDeviceInfoMethod.getIp()
                val mWifiInfo = mWifiManager.connectionInfo
                yLQGKKC = mWifiInfo.ssid.replace("\"", "")
                E3uS8KW = mWifiInfo.macAddress
                val wifi = TienAcquisitionReq.Wifi()
                wifi.maWZbWa = mWifiInfo.bssid
                wifi.ieTURqu = yLQGKKC
                wifi.GP6HtEx = E3uS8KW
                wifi.Wz3oomm = mWifiInfo.ssid
                AXegzQv = wifi
//                JOssFl8 = mWifiManager.scanResults.size

            }
            val timeMillis = System.currentTimeMillis()
            LmZegpA = timeMillis
            iAcaOxn = TienSystemUtil.getTienVersionCode(context).toString()
            Gb9ZFe7 = TienDeviceInfoMethod.getTienGid(context)
            ny8OD0E = System.currentTimeMillis()
            try {
                val activityManager: ActivityManager =
                    context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val memoryInfo: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()
                activityManager.getMemoryInfo(memoryInfo)
                val totalMemory: Long = memoryInfo.totalMem
                val availableMemory: Long = memoryInfo.availMem
                EDPl08p = totalMemory
                eQX6tv1 = availableMemory
            } catch (_: Exception) {
            }
            p6FfdLv = Runtime.getRuntime().availableProcessors()
            jiFj718 = Build.HARDWARE
            idcXt2u = TienDeviceInfoMethod.getTienCurrentCpuFreq(0)
            UPM60hE = TienDeviceInfoMethod.getTienMaxCpuFreq(0)
            mHIJDmr = TienDeviceInfoMethod.getTienNetworkType(context)
        }
        return deviceInfo
    }
}