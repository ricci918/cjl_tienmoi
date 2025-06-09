package com.tienmoi.vayonline.nhanh.ui.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUiUtil

object TienPrivacyDialog {
    @SuppressLint("InflateParams")
    fun showTienPrivacyDialog(activity: Activity, onSelectedListener: () -> Unit) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_privacy_permission_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val webView = view.findViewById<WebView>(R.id.wv_id)
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        TienSharedPreferencesUtil.getSystemInfoData()
            ?.let { webView.loadUrl(it.aYiaBX8) }
        val settings = webView.settings
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        settings.textZoom = 80
        tv2.setOnClickListener {
            dialog.dismiss()
        }
        tv1.setOnClickListener {
            showTienPermissionDialog(activity) {
                onSelectedListener.invoke()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    @SuppressLint("InflateParams")
    private fun showTienPermissionDialog(
        activity: Activity,
        onSelectedListener: () -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_privacy_permission_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val webView = view.findViewById<WebView>(R.id.wv_id)
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        TienSharedPreferencesUtil.getSystemInfoData()
            ?.let { webView.loadUrl(it.kwzXpNJ) }
        val settings = webView.settings
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        settings.textZoom = 80
        tv2.setOnClickListener {
            dialog.dismiss()
        }
        tv1.setOnClickListener {
            dialog.dismiss()
            onSelectedListener.invoke()
        }
        dialog.show()
    }
}