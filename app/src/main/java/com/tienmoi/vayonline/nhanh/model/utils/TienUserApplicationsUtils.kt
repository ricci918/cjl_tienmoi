package com.tienmoi.vayonline.nhanh.model.utils

import android.app.Activity
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq.*


object TienUserApplicationsUtils {
    fun getTienUserApplications(
        context: Activity
    ): List<TienUserApplications> {
        val listOf = arrayListOf<TienUserApplications>()
        val uniqueAppList = mutableListOf<TienUserApplications>()
        val packageManager: PackageManager = context.packageManager
        packageManager.getInstalledPackages(0)
            .forEach { resolve ->
                val tienUserApplications = TienUserApplications()
                tienUserApplications.apply {
                    // 处理用户安装的应用
                    val packageInfo = packageManager.getPackageInfo(resolve.packageName, 0)
                    KbJ0sVp = resolve.applicationInfo?.loadLabel(packageManager).toString()
                    zfquUR7 = resolve.packageName
                    NSKc5xu = packageInfo.firstInstallTime
                    gWi2QLg = packageInfo.lastUpdateTime
                    JtFgGMN = packageInfo.versionName
                    HNz5DNA = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        packageInfo.longVersionCode
                    } else {
                        0
                    }
                    val appInfo: ApplicationInfo =
                        context.packageManager.getApplicationInfo(resolve.packageName, 0)
                    val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                    OzhnGES = if (isSystemApp) {
                        1
                    } else {
                        0
                    }
                    listOf.add(tienUserApplications)

                }
            }
        uniqueAppList.addAll(listOf.distinctBy { it.zfquUR7 })
        return uniqueAppList
    }
}