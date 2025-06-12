package com.tienmoi.vayonline.nhanh.ui.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.tienmoi.vayonline.nhanh.base.TienBaseActivityNoData
import com.tienmoi.vayonline.nhanh.databinding.ActivityConfirmCertificationBinding
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.ui.dialog.TienPrivacyDialog
import com.yanzhenjie.permission.AndPermission


class TienConfirmCertificationActivity : TienBaseActivityNoData() {
    private lateinit var mBinding: ActivityConfirmCertificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityConfirmCertificationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            if (TienSharedPreferencesUtil.isTienFirstInstall()) {
                TienPrivacyDialog.showTienPrivacyDialog(this@TienConfirmCertificationActivity) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        AndPermission.with(this@TienConfirmCertificationActivity)
                            .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                            .permission(Manifest.permission.POST_NOTIFICATIONS)
                            .onGranted {
                                TienSharedPreferencesUtil.putTienIsFirstInstall(false)
                            }
                            .onDenied {
                                TienSharedPreferencesUtil.putTienIsFirstInstall(false)
                            }
                            .start()
                    } else {
                        AndPermission.with(this@TienConfirmCertificationActivity)
                            .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                            .onGranted {
                                TienSharedPreferencesUtil.putTienIsFirstInstall(false)
                            }
                            .onDenied {
                                TienSharedPreferencesUtil.putTienIsFirstInstall(false)
                            }
                            .start()
                    }
                }

            }
            tv7Id.setOnClickListener {
                startActivity(TienUserLoginActivity::class.java)
                finish()
            }
            srId.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        val stepSize = 500000
                        val adjustedProgress = Math.round(progress.toFloat() / stepSize) * stepSize
                        seekBar.progress = adjustedProgress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    tv2Id.text = "₫ " + TienSystemUtil.addTienComma(seekBar.progress.toString())
                }
            })

        }
    }
}