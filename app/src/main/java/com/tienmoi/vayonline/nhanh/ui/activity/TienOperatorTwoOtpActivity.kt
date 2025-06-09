package com.tienmoi.vayonline.nhanh.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityOperatorTwoOtpBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienOperatorTwoOtpContract
import com.tienmoi.vayonline.nhanh.model.data.TienGetOtpTwo
import com.tienmoi.vayonline.nhanh.model.data.TienPostOtpTwo
import com.tienmoi.vayonline.nhanh.model.data.TienQueryStatusData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserUtil
import com.tienmoi.vayonline.nhanh.presenter.TienOperatorTwoOtpPresenter
import org.greenrobot.eventbus.EventBus

class TienOperatorTwoOtpActivity :
    TienBaseActivity<TienOperatorTwoOtpContract.TienOperatorTwoOtpView, TienOperatorTwoOtpPresenter>(),
    TienOperatorTwoOtpContract.TienOperatorTwoOtpView {
    private lateinit var mBinding: ActivityOperatorTwoOtpBinding
    private var isAuth = false
    private var channelValue = ""
    private var otp2 = false
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityOperatorTwoOtpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            headId.ivClose.visibility = View.VISIBLE
            headId.ivClose.text = getString(R.string.text_return)
            et1Id.setText(TienSharedPreferencesUtil.getTienPhone())
            val name = intent.getStringExtra(TienSystemUtil.NAME) ?: ""
            val otp1 = intent.getStringExtra(TienSystemUtil.OTP) ?: ""
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
                presenter?.getTienGetOtpTwo(et1Id.text.toString(), otp1, channelValue, name)
            }
            tvNextStep.setOnClickListener {
                if (otp2 && et2Id.text.toString() != "") {
                    showLoading()
                    presenter?.getTienPostOtpTwo(
                        et1Id.text.toString(),
                        et2Id.text.toString(),
                        channelValue,
                        name
                    )
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

    override fun initPresenter(): TienOperatorTwoOtpPresenter = TienOperatorTwoOtpPresenter()

    override fun successTienQueryStatus(data: TienQueryStatusData) {
        channelValue = data.juDS7ma
    }

    override fun successTienGetOtpTwo(data: TienGetOtpTwo) {
        dismissLoading()
        if (data.JnCOgXl) {
            countDown()
            otp2 = true
        }

    }

    override fun successTienPostOtpTwo(data: TienPostOtpTwo) {
        dismissLoading()
        if (data.emiOStr) {
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
}