package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.tienmoi.vayonline.nhanh.base.TienBaseActivityNoData
import com.tienmoi.vayonline.nhanh.databinding.ActivityTienPolicyBinding
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil

class TienPrivacyPolicyActivity : TienBaseActivityNoData() {
    private lateinit var mBinding: ActivityTienPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityTienPolicyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            wvId.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            wvId.webViewClient = WebViewClient()
            TienSharedPreferencesUtil.getSystemInfoData()
                ?.let { wvId.loadUrl(it.aYiaBX8) }
            val settings = wvId.settings
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            settings.textZoom = 80
        }
    }
}