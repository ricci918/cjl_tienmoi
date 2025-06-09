package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityTienOrderDetailsBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienOrderDetailsContract
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderDetailData
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.presenter.TienOrderDetailsPresenter

class TienOrderDetailsActivity :
    TienBaseActivity<TienOrderDetailsContract.TienOrderDetailsView, TienOrderDetailsPresenter>(),
    TienOrderDetailsContract.TienOrderDetailsView {
    private lateinit var mBinding: ActivityTienOrderDetailsBinding
    private var codeValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityTienOrderDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        mBinding.apply {
            headId.tvHead.text = getString(R.string.orderDetails10)
            headId.ivClose.visibility = View.VISIBLE
            headId.ivClose.text = getString(R.string.text_return)
            headId.ivClose.setOnClickListener {
                finish()
            }
        }
    }

    override fun initData() {
        codeValue = intent.getStringExtra(TienSystemUtil.ORDER_CODE) ?: ""
        presenter?.getTienOrderDetail(codeValue)
        presenter?.getTienInfo()
    }

    override fun initPresenter(): TienOrderDetailsPresenter = TienOrderDetailsPresenter()

    override fun initLayout(): View = mBinding.root

    override fun successTienOrderDetail(data: TienOrderDetailData) {
        mBinding.apply {
            tv2Id.text = data.AkHWMZJ.toString()
            tv4Id.text = data.VgIdg26.toString()
            tv6Id.text = data.Jpn0nzx.toString()
            tv8Id.text = data.Y1TRXzD.toString()
            tv10Id.text = data.oFhz7mB.toString()
            tv12Id.text = data.TzsqKhH.toString()
            tv14Id.text = data.KRaaVjJ.toString()
            tv16Id.text = data.dm1O7NY.toString()
            tv18Id.text = TienSystemUtil.getTienDateToString(
                data.tpcTrKS.toString(),
                "yyyy-MM-dd"
            )
            tv20Id.text = data.sd8HKZu.toString() + getString(R.string.orderDetails13)
            tv22Id.text = data.QYFOMsi.toString()
            when (data.XgC3trl) {
                "PAYING" -> {
                    rl2Id.visibility = View.VISIBLE
                }

                "TO_REPAYMENT", "OVERDUE" -> {
                    rl1Id.visibility = View.VISIBLE
                    tv19Id.visibility = View.GONE
                    tv20Id.visibility = View.GONE
                }

                "WAIT_TO_WITHDRAW" -> {
                    rl1Id.visibility = View.VISIBLE
                    tv15Id.visibility = View.GONE
                    tv16Id.visibility = View.GONE
                    tv17Id.visibility = View.GONE
                    tv18Id.visibility = View.GONE
                }

                else -> {
                    rl1Id.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun successTienInfo(data: TienInfoData) {
        if (data.iaqWeRm != "" && data.iaqWeRm != null) {
            mBinding.tv25Id.text =
                String.format(
                    getString(R.string.orderDetails16),
                    data.iaqWeRm.substring(data.iaqWeRm.length - 4)
                )
        }
    }
}