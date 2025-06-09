package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityTienRepaymentDisplayItemsBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienRepaymentDisplayItemsContract
import com.tienmoi.vayonline.nhanh.model.data.RepaymentWayData
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.presenter.TienRepaymentDisplayItemsPresenter

class TienRepaymentDisplayItemsActivity :
    TienBaseActivity<TienRepaymentDisplayItemsContract.TienRepaymentDisplayItemsView, TienRepaymentDisplayItemsPresenter>(),
    TienRepaymentDisplayItemsContract.TienRepaymentDisplayItemsView {
    private lateinit var mBinding: ActivityTienRepaymentDisplayItemsBinding
    private var codeValue = ""
    private var typeValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityTienRepaymentDisplayItemsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        typeValue = intent.getIntExtra(TienSystemUtil.TYPE, 0)
        if (typeValue == TienSystemUtil.TWO_VALUE) {
            mBinding.tv7Id.text = getString(R.string.repayment18)
            mBinding.tv7Id.setTextColor(getColor(R.color.black))
        }
        mBinding.apply {
            headId.ivClose.visibility = View.VISIBLE
            headId.ivClose.text = getString(R.string.text_return)
            headId.ivClose.setOnClickListener {
                finish()
            }
            mBinding.tvReturn.setOnClickListener {
                finish()
            }
        }
        }


    override fun initData() {
        codeValue = intent.getStringExtra(TienSystemUtil.ORDER_CODE) ?: ""
        presenter?.getTienRepaymentWay(codeValue)
    }

    override fun initPresenter(): TienRepaymentDisplayItemsPresenter =
        TienRepaymentDisplayItemsPresenter()

    override fun initLayout(): View = mBinding.root

    override fun successTienRepaymentWay(data: RepaymentWayData) {
        mBinding.apply {
            if (typeValue == TienSystemUtil.ONE_VALUE) {
                tv2Id.text = data.wCFv8Hn[1].GCFy3LO
                tv4Id.text = data.wCFv8Hn[1].Rbtxor0
                tv6Id.text = data.wCFv8Hn[1].ZjHxact
            } else if (typeValue == TienSystemUtil.TWO_VALUE) {
                tv2Id.text = data.wCFv8Hn[0].GCFy3LO
                tv4Id.text = data.wCFv8Hn[0].Rbtxor0
                tv6Id.text = data.wCFv8Hn[0].ZjHxact
            }

        }
    }
}