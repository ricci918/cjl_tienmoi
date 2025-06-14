package com.tienmoi.vayonline.nhanh.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.AnimationUtils
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityLoanConfirmationBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienLoanConfirmationContract
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq.TienUserDeviceInfo
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.utils.TienDeviceInfoUtils
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserApplicationsUtils
import com.tienmoi.vayonline.nhanh.presenter.TienLoanCertificationPresenter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TienLoanConfirmationActivity :
    TienBaseActivity<TienLoanConfirmationContract.TienLoanConfirmationView, TienLoanCertificationPresenter>(),
    TienLoanConfirmationContract.TienLoanConfirmationView {
    private lateinit var mBinding: ActivityLoanConfirmationBinding
    private var tester: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityLoanConfirmationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            vfTv1.text = Html.fromHtml(getString(R.string.LoanConfirmation1))
            vfTv2.text = Html.fromHtml(getString(R.string.LoanConfirmation1))
            vfTv3.text = Html.fromHtml(getString(R.string.LoanConfirmation1))
            vfTv4.text = Html.fromHtml(getString(R.string.LoanConfirmation13))
            vfId.inAnimation =
                AnimationUtils.loadAnimation(this@TienLoanConfirmationActivity, R.anim.push_up_in)
            vfId.outAnimation =
                AnimationUtils.loadAnimation(this@TienLoanConfirmationActivity, R.anim.push_up_out)
            vfId.startFlipping()
            tv12Id.text = Html.fromHtml(getString(R.string.LoanConfirmation10))
            tvNextStep.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                showLoadingOne()
                presenter?.getTienAddVayHub()
            }
            tv12Id.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                startActivity(TienLoanContractActivity::class.java)
            }
        }
    }

    override fun initData() {
        presenter?.getTienInfo()
    }

    override fun initPresenter(): TienLoanCertificationPresenter = TienLoanCertificationPresenter()

    override fun successTienInfo(data: TienInfoData) {
        mBinding.apply {
            tester = data.WcrIiHm
            tv9Id.text = data.JtMhKBI.GS4VVmR
            tv11Id.text = data.JtMhKBI.bR76Dxu
            if (data.WcrIiHm) {
                tv2Id.text = TienSharedPreferencesUtil.getSystemInfoData()!!.rZPrEqC.fTL5Eje
                tv5Id.text =
                    TienSharedPreferencesUtil.getSystemInfoData()!!.rZPrEqC.cVRbNR1.toString() + getString(
                        R.string.LoanConfirmation12
                    )
                tv7Id.text =
                    TienSharedPreferencesUtil.getSystemInfoData()!!.rZPrEqC.oEV6w7z
            } else {
                tv2Id.text = TienSharedPreferencesUtil.getSystemInfoData()!!.x9Hy2Dy.vzLNQxd
                tv5Id.text =
                    TienSharedPreferencesUtil.getSystemInfoData()!!.x9Hy2Dy.qVeZhSO.toString() + getString(
                        R.string.LoanConfirmation12
                    )
                tv7Id.text =
                    TienSharedPreferencesUtil.getSystemInfoData()!!.x9Hy2Dy.WIytgTO
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun successTienAddVayHub(data: Any) {
        GlobalScope.launch {
            presenter?.getTienAcquisition(
                TienAcquisitionReq(
                    TienUserDeviceInfo(
                        TienDeviceInfoUtils.getTienDeviceInfo(this@TienLoanConfirmationActivity)
                    ),
                    TienUserApplicationsUtils.getTienUserApplications(this@TienLoanConfirmationActivity)
                )
            )
        }
    }

    override fun successTienAcquisition(data: Any) {
        presenter?.getTienCreate(TienOrderCreateReq())
    }

    override fun successTienCreate(data: Any) {
        dismissLoading()
        val intent = Intent(this, TienMainActivity::class.java)
        intent.putExtra(TienSystemUtil.GOOD, TienSystemUtil.GOOD)
        startActivity(intent)
        finish()
    }

    override fun error() {
        dismissLoadingOne()
    }
}