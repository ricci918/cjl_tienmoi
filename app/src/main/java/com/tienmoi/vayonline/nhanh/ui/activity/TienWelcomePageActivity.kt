package com.tienmoi.vayonline.nhanh.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityWelcomePageBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienWelcomeContract
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData
import com.tienmoi.vayonline.nhanh.model.data.TienSystemInfoData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserUtil
import com.tienmoi.vayonline.nhanh.presenter.TienWelcomePagePresenter

class TienWelcomePageActivity :
    TienBaseActivity<TienWelcomeContract.TienWelcomeView, TienWelcomePagePresenter>(),
    TienWelcomeContract.TienWelcomeView {
    private lateinit var mBinding: ActivityWelcomePageBinding
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityWelcomePageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initPresenter(): TienWelcomePagePresenter = TienWelcomePagePresenter()

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.tv1Id.text = Html.fromHtml(getString(R.string.welcome_text2))
    }

    override fun initData() {
        presenter?.getTienSystemInfo()
        if (TienUserUtil.isTienLogin()) {
            presenter?.getTienQueryStatus(TienUserUtil.getTienUser()?.Jn4mzjv ?: "")
        } else {
            countDown(-1)
        }

    }

    override fun successTienSystemInfo(data: TienSystemInfoData) {
        TienSharedPreferencesUtil.putTienSystemInfoData(data)
    }

    override fun successTienQueryStatus(data: TienQueryStatusData) {
        countDown(data.w9WsW5o)
    }


    private fun countDown(step: Int) {
        countDownTimer = object : CountDownTimer(2000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                when (step) {
                    -1 -> {
                        startActivity(TienConfirmCertificationActivity::class.java)
                    }

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
                                        this@TienWelcomePageActivity,
                                        TienMainActivity::class.java
                                    )
                                startActivity(intent)
                            } else {
                                startActivity(TienLoanConfirmationActivity::class.java)
                            }
                        } else {
                            val intent =
                                Intent(
                                    this@TienWelcomePageActivity,
                                    TienOperatorSelectActivity::class.java
                                )
                            intent.putExtra(TienSystemUtil.IS_AUTH, true)
                            startActivity(intent)
                        }
                    }

                    4 -> {
                        val intent =
                            Intent(this@TienWelcomePageActivity, TienMainActivity::class.java)
                        startActivity(intent)
                    }
                }
                finish()
            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {

            }
        }.start()
    }
}