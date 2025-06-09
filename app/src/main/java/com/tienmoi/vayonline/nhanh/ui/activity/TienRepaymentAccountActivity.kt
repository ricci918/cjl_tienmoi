package com.tienmoi.vayonline.nhanh.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivityNoData
import com.tienmoi.vayonline.nhanh.databinding.ActivityRepaymentAccountBinding
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil

class TienRepaymentAccountActivity : TienBaseActivityNoData() {
    private lateinit var mBinding: ActivityRepaymentAccountBinding
    private var codeValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityRepaymentAccountBinding.inflate(layoutInflater)
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
            codeValue = intent.getStringExtra(TienSystemUtil.ORDER_CODE) ?: ""
            tvReturn.setOnClickListener {
                finish()
            }
            if (TienSharedPreferencesUtil.getSystemInfoData()?.xZYUYiy == true) {
                ll2Id.visibility = View.VISIBLE
            }
            ll1Id.setOnClickListener {
                val intent = Intent(this@TienRepaymentAccountActivity, TienRepaymentDisplayItemsActivity::class.java)
                intent.putExtra(TienSystemUtil.ORDER_CODE, codeValue)
                intent.putExtra(TienSystemUtil.TYPE, TienSystemUtil.ONE_VALUE)
                startActivity(intent)
            }
            ll2Id.setOnClickListener {
                val intent = Intent(this@TienRepaymentAccountActivity, TienRepaymentDisplayItemsActivity::class.java)
                intent.putExtra(TienSystemUtil.ORDER_CODE, codeValue)
                intent.putExtra(TienSystemUtil.TYPE, TienSystemUtil.TWO_VALUE)
                startActivity(intent)
            }
        }

    }
}