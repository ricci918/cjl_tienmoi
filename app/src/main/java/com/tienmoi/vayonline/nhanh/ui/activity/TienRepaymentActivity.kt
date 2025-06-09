package com.tienmoi.vayonline.nhanh.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityTienRepaymentBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienRepaymentContract
import com.tienmoi.vayonline.nhanh.model.data.TienOrderDetailData
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.presenter.TienRepaymentPresenter

class TienRepaymentActivity :
    TienBaseActivity<TienRepaymentContract.TienRepaymentView, TienRepaymentPresenter>(),
    TienRepaymentContract.TienRepaymentView {
    private lateinit var mBinding: ActivityTienRepaymentBinding
    private var codeValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityTienRepaymentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        mBinding.apply {
            headId.ivClose.visibility = View.VISIBLE
            headId.ivClose.text = getString(R.string.text_return)
            headId.ivClose.setOnClickListener {
                finish()
            }
            tvReturn.setOnClickListener {
                finish()
            }
            tvNextStep.setOnClickListener {
                val intent =
                    Intent(this@TienRepaymentActivity, TienRepaymentAccountActivity::class.java)
                intent.putExtra(TienSystemUtil.ORDER_CODE, codeValue)
                startActivity(intent)
            }
        }

    }

    override fun initData() {
        codeValue = intent.getStringExtra(TienSystemUtil.ORDER_CODE) ?: ""
        presenter?.getTienOrderDetail(codeValue)
    }

    override fun initPresenter(): TienRepaymentPresenter = TienRepaymentPresenter()

    override fun initLayout(): View = mBinding.root

    override fun successTienOrderDetail(data: TienOrderDetailData) {
        mBinding.apply {
            tv2Id.text = data.ShX3hSP.toString()
            tv4Id.text = data.VgIdg26.toString()
            tv6Id.text = data.Jpn0nzx.toString()
            tv8Id.text = data.Y1TRXzD.toString()
            tv10Id.text = data.oFhz7mB.toString()
            tv12Id.text = data.TzsqKhH.toString()
            tv14Id.text = data.KRaaVjJ.toString()
            tv16Id.text = data.dm1O7NY.toString()
        }
    }
}