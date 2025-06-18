package com.tienmoi.vayonline.nhanh.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import android.view.View
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityUserLoginBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienLoginContract
import com.tienmoi.vayonline.nhanh.model.data.TienLoginData
import com.tienmoi.vayonline.nhanh.model.data.TienLoginReq
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserUtil
import com.tienmoi.vayonline.nhanh.presenter.TienLoginPresenter

class TienUserLoginActivity :
    TienBaseActivity<TienLoginContract.TienLoginView, TienLoginPresenter>(),
    TienLoginContract.TienLoginView {
    private lateinit var mBinding: ActivityUserLoginBinding
    private var codeData: String = ""
    private lateinit var countDownTimer: CountDownTimer
    private var fbc = ""
    private var fbp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityUserLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            if (intent != null && intent.extras != null) {
                val extras = intent.extras
                // 获取_fbc参数
                val _fbc = extras!!.getString("_fbc")
                if (_fbc != null) {
                    fbc = _fbc
                }
                // 获取_fbp参数
                val _fbp = extras.getString("_fbp")
                if (_fbp != null) {
                    fbp = _fbp
                }

            }
            val packageManager: PackageManager = packageManager
            val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
            val firstInstallTime = packageInfo.firstInstallTime
            tv3Id.setOnClickListener {
                showLoading()
                presenter?.getTienSendCode(
                    etPhone.text.toString(),
                    TienSharedPreferencesUtil.getSystemInfoData()?.t3ayAYz ?: ""
                )
            }
            tv4Id.setOnClickListener {
                showLoading()
                presenter?.getTienLogin(
                    TienLoginReq(
                        etPhone.text.toString(),
                        TienSharedPreferencesUtil.getSystemInfoData()?.t3ayAYz ?: "",
                        codeData,
                        etOtp.text.toString(),
                        null,
                        TienSystemUtil.getTienAndroidId(this@TienUserLoginActivity),
                        AppsFlyerLib.getInstance().getAppsFlyerUID(this@TienUserLoginActivity)
                            ?: "",
                        TienSharedPreferencesUtil.getTienCampaignId(),
                        TienSharedPreferencesUtil.getTienGaId(),
                        firstInstallTime, fbc, fbp, TienSharedPreferencesUtil.getTienFCM()
                    )
                )
            }
            tv8Id.setOnClickListener {
                startActivity(TienPrivacyPolicyActivity::class.java)
            }
            tv6Id.text = TienSharedPreferencesUtil.getSystemInfoData()?.GuE7zXx ?: ""
//            tv7Id.text = TienSharedPreferencesUtil.getSystemInfoData()?.ythTb7E ?: ""
            tv7Id.text = "support@tienmoivayonline.com"
            tv8Id.text = Html.fromHtml(getString(R.string.login_text10))
        }
    }

    override fun initData() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            TienSharedPreferencesUtil.putTienFCM(token)
        })
    }

    override fun initPresenter(): TienLoginPresenter = TienLoginPresenter()

    override fun successTienSendCode(data: Any) {
        dismissLoading()
        countDown()
        if (data != "") {
            codeData = data as String
        }
    }

    override fun successTienLogin(data: TienLoginData) {
        dismissLoading()
        TienUserUtil.putTienUser(data)
        TienSharedPreferencesUtil.putTienPhone(mBinding.etPhone.text.toString())
        presenter?.getTienQueryStatus(data.Jn4mzjv)
    }

    override fun successTienQueryStatus(data: TienQueryStatusData) {
        when (data.w9WsW5o) {
            0 -> {
                startActivity(TienCertificationOneActivity::class.java)
            }

            1 -> {
                startActivity(TienCertificationTwoActivity::class.java)
            }

            2 -> {
                startActivity(TienCertificationThreeActivity::class.java)
            }

            3 -> {
                if (TienSharedPreferencesUtil.getSystemInfoData()?.XKruWxQ == true) {
                    if (TienSharedPreferencesUtil.getSystemInfoData()?.hGkzoMf == true) {
                        val intent =
                            Intent(
                                this,
                                TienMainActivity::class.java
                            )
                        startActivity(intent)
                    } else {
                        startActivity(TienLoanConfirmationActivity::class.java)
                    }
                } else {
                    val intent =
                        Intent(
                            this,
                            TienOperatorSelectActivity::class.java
                        )
                    intent.putExtra(TienSystemUtil.IS_AUTH, true)
                    startActivity(intent)
                }
            }

            4 -> {
                startActivity(TienMainActivity::class.java)
            }
        }
        finish()
    }

    override fun error() {
        dismissLoading()
    }

    private fun countDown() {
        var countTime = 60
        countDownTimer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                mBinding.tv3Id.text = getString(R.string.login_text7)
                mBinding.tv3Id.isEnabled = true
            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {
                countTime--
                mBinding.tv3Id.text = "" + countTime + "s"
                mBinding.tv3Id.isEnabled = false
            }
        }.start()
    }
}