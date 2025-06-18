package com.tienmoi.vayonline.nhanh.model.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq.TienUserApplications


object TienUserApplicationsUtils {
    //    fun getTienUserApplications(
//        context: Activity
//    ): List<TienUserApplications> {
//        val listOf = arrayListOf<TienUserApplications>()
//        val uniqueAppList = mutableListOf<TienUserApplications>()
//        val packageManager: PackageManager = context.packageManager
//        packageManager.getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES or PackageManager.MATCH_DISABLED_COMPONENTS)
//            .forEach { resolve ->
//                val tienUserApplications = TienUserApplications()
//                tienUserApplications.apply {
//                    // 处理用户安装的应用
//                    val packageInfo = packageManager.getPackageInfo(resolve.packageName, 0)
//                    KbJ0sVp = resolve.applicationInfo?.loadLabel(packageManager).toString()
//                    zfquUR7 = resolve.packageName
//                    NSKc5xu = packageInfo.firstInstallTime
//                    gWi2QLg = packageInfo.lastUpdateTime
//                    JtFgGMN = packageInfo.versionName
//                    HNz5DNA = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                        packageInfo.longVersionCode
//                    } else {
//                        0
//                    }
//                    val appInfo: ApplicationInfo =
//                        context.packageManager.getApplicationInfo(resolve.packageName, 0)
//                    val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
//                    OzhnGES = if (isSystemApp) {
//                        1
//                    } else {
//                        0
//                    }
//                    listOf.add(tienUserApplications)
//
//                }
//            }
//        uniqueAppList.addAll(listOf.distinctBy { it.zfquUR7 })
//        return uniqueAppList
//    }
    fun getTienUserApplications(
        context: Activity
    ): List<TienUserApplications> {
        val intent = Intent().apply {
            action = Intent.ACTION_MAIN
        }
        val listOf = arrayListOf<TienUserApplications>()
        val uniqueAppList = mutableListOf<TienUserApplications>()
        val pm: PackageManager = context.packageManager
        val resolveInfos =
            pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        resolveInfos.forEach { resolve ->
            val userApplications = TienUserApplications()
            userApplications.apply {
                // 处理用户安装的应用
                val packageInfo = pm.getPackageInfo(resolve.activityInfo.packageName, 0)
                KbJ0sVp = resolve.loadLabel(pm).toString()
                zfquUR7 = resolve.activityInfo.packageName
                NSKc5xu = packageInfo.firstInstallTime
                gWi2QLg = packageInfo.lastUpdateTime
                JtFgGMN = packageInfo.versionName
                HNz5DNA = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    0
                }
                val appInfo: ApplicationInfo =
                    context.packageManager.getApplicationInfo(
                        resolve.activityInfo.packageName,
                        0
                    )
                val isSystemApp = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                OzhnGES = if (isSystemApp) {
                    1
                } else {
                    0
                }
                listOf.add(userApplications)
            }
        }
        uniqueAppList.addAll(listOf.distinctBy { it.zfquUR7 })
        return uniqueAppList
    }
}