package com.tienmoi.vayonline.nhanh.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityOperatorOneOtpBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienOperatorOneOtpContract
import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienPostOtpOne
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserUtil
import com.tienmoi.vayonline.nhanh.presenter.TienOperatorOneOtpPresenter
import org.greenrobot.eventbus.EventBus

class TienOperatorOneOtpActivity :
    TienBaseActivity<TienOperatorOneOtpContract.TienOperatorOneOtpView, TienOperatorOneOtpPresenter>(),
    TienOperatorOneOtpContract.TienOperatorOneOtpView {
    private lateinit var mBinding: ActivityOperatorOneOtpBinding
    private lateinit var countDownTimer: CountDownTimer
    private var channelValue = ""
    private var otp1 = false
    private var isAuth = false
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityOperatorOneOtpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            headId.ivClose.visibility = View.VISIBLE
            headId.ivClose.text = getString(R.string.text_return)
            et1Id.setText(TienSharedPreferencesUtil.getTienPhone())
            val name = intent.getStringExtra(TienSystemUtil.NAME) ?: ""
            isAuth = intent.getBooleanExtra(TienSystemUtil.IS_AUTH, false)
            when (name) {
                TienSystemUtil.VIET -> {
                    iv1Id.setImageResource(R.mipmap.operator1)
                }

                TienSystemUtil.VINA -> {
                    iv1Id.setImageResource(R.mipmap.operator2)
                }

                TienSystemUtil.MOBI -> {
                    iv1Id.setImageResource(R.mipmap.operator3)
                }

                TienSystemUtil.VIETNAMOBILE -> {
                    iv1Id.setImageResource(R.mipmap.operator4)
                }

                TienSystemUtil.SAYMEE -> {
                    iv1Id.setImageResource(R.mipmap.operator5)
                }
            }
            tv2Id.setOnClickListener {
                showLoading()
                presenter?.getTienGetOtpOne(et1Id.text.toString(), channelValue, name)
            }
            tvNextStep.setOnClickListener {
                if (otp1 && et2Id.text.toString() != "") {
                    if (name == TienSystemUtil.VIETNAMOBILE) {
                        showLoading()
                        presenter?.getTienPostOtpOne(
                            et1Id.text.toString(),
                            et2Id.text.toString(),
                            channelValue,
                            name
                        )
                    } else {
                        val intent =
                            Intent(
                                this@TienOperatorOneOtpActivity,
                                TienOperatorTwoOtpActivity::class.java
                            )
                        intent.putExtra(TienSystemUtil.NAME, name)
                        intent.putExtra(TienSystemUtil.OTP, mBinding.et2Id.text.toString())
                        intent.putExtra(TienSystemUtil.IS_AUTH, isAuth)
                        startActivity(intent)
                        finish()
                    }
                }

            }
            headId.ivClose.setOnClickListener {
                finish()
            }
        }
    }

    override fun initData() {
        presenter?.getTienQueryStatus(TienUserUtil.getTienUser()?.Jn4mzjv ?: "")
    }

    override fun initPresenter(): TienOperatorOneOtpPresenter = TienOperatorOneOtpPresenter()

    private fun countDown() {
        var countTime = 60
        countDownTimer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                mBinding.tv2Id.isEnabled = true
                mBinding.tv2Id.text = getString(R.string.operator11)
            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {
                countTime--
                mBinding.tv2Id.text = "" + countTime + "s"
                mBinding.tv2Id.isEnabled = false
            }
        }.start()
    }

    override fun successTienQueryStatus(data: TienQueryStatusData) {
        channelValue = data.juDS7ma
    }

    override fun successTienGetOtpOne(data: TienGetOtpOne) {
        dismissLoading()
        if (data.uLWKnX7) {
            countDown()
            otp1 = true
        }
    }

    override fun successTienPostOtpOne(data: TienPostOtpOne) {
        dismissLoading()
        if (data.M7PTH7C) {
            if (isAuth) {
                startActivity(TienLoanConfirmationActivity::class.java)
            } else {
                presenter?.getTienNotify()
                EventBus.getDefault().post(TienSystemUtil.OPERATOR)
                startActivity(TienMainActivity::class.java)
            }
            finish()
        } else {
            TienToastUtil.myToast(getString(R.string.operator14))
        }
    }

    override fun successTienNotify(data: Any) {

    }

    override fun error() {
        dismissLoading()
    }
}