package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.text.Html
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityTienLoanContractBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienInfoDataContract
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.presenter.TienInfoDataPresenter
import java.text.SimpleDateFormat
import java.util.Date

class TienLoanContractActivity :
    TienBaseActivity<TienInfoDataContract.TienInfoDataView, TienInfoDataPresenter>(),
    TienInfoDataContract.TienInfoDataView {
    private lateinit var mBinding: ActivityTienLoanContractBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityTienLoanContractBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            headId.ivClose.visibility = View.VISIBLE
            headId.ivClose.text = getString(R.string.text_return)
            headId.ivClose.setOnClickListener {
                finish()
            }
        }
    }

    override fun initData() {
        presenter?.getTienInfo()
    }

    override fun initPresenter(): TienInfoDataPresenter = TienInfoDataPresenter()

    override fun successTienInfo(data: TienInfoData) {
        val money = if (data.WcrIiHm) {
            TienSharedPreferencesUtil.getSystemInfoData()!!.rZPrEqC.fTL5Eje
        } else {
            TienSharedPreferencesUtil.getSystemInfoData()!!.x9Hy2Dy.vzLNQxd
        }
        val interest = if (data.WcrIiHm) {
            TienSharedPreferencesUtil.getSystemInfoData()!!.rZPrEqC.oEV6w7z
        } else {
            TienSharedPreferencesUtil.getSystemInfoData()!!.x9Hy2Dy.WIytgTO
        }
        val portend = if (data.WcrIiHm) {
            TienSharedPreferencesUtil.getSystemInfoData()!!.rZPrEqC.cVRbNR1.toString()
        } else {
            TienSharedPreferencesUtil.getSystemInfoData()!!.x9Hy2Dy.qVeZhSO.toString()
        }
        val simpleDateFormat =
            SimpleDateFormat("yyyy-MM-dd")
        val date: Date = Date(System.currentTimeMillis())
        val s = String.format(
            getString(R.string.loanContract),
            simpleDateFormat.format(date),
            data.QaA0CmZ,
            data.QLuTvkJ, money, interest, portend, simpleDateFormat.format(date), data.QaA0CmZ
        )
        val fromHtml = Html.fromHtml(s)

        mBinding.tv1Id.text = fromHtml
    }
}