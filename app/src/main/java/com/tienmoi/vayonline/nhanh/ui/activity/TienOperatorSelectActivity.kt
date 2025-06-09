package com.tienmoi.vayonline.nhanh.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivityNoData
import com.tienmoi.vayonline.nhanh.databinding.ActivityOperatorSelectBinding
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil

class TienOperatorSelectActivity : TienBaseActivityNoData(), View.OnClickListener {
    private lateinit var mBinding: ActivityOperatorSelectBinding
    private var auth = false
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityOperatorSelectBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            val isAuth = intent.getBooleanExtra(TienSystemUtil.IS_AUTH, false)
            auth = isAuth
            if (!isAuth){
                headId.ivClose.visibility = View.VISIBLE
            }
            headId.ivClose.text = getString(R.string.text_return)
            headId.ivClose.setOnClickListener {
                finish()
            }
            viewOnClick(rl1Id, rl2Id, rl3Id, rl4Id, rl5Id)
        }
    }

    override fun onClick(v: View?) {
        v?.apply {
            when (id) {
                R.id.rl1_id -> {
                    val intent = Intent(this@TienOperatorSelectActivity, TienOperatorOneOtpActivity::class.java)
                    intent.putExtra(TienSystemUtil.IS_AUTH,auth)
                    intent.putExtra(TienSystemUtil.NAME, TienSystemUtil.VIET)
                    startActivity(intent)
                }
                R.id.rl2_id -> {
                    val intent = Intent(this@TienOperatorSelectActivity, TienOperatorOneOtpActivity::class.java)
                    intent.putExtra(TienSystemUtil.IS_AUTH,auth)
                    intent.putExtra(TienSystemUtil.NAME, TienSystemUtil.MOBI)
                    startActivity(intent)
                }
                R.id.rl3_id -> {
                    val intent = Intent(this@TienOperatorSelectActivity, TienOperatorOneOtpActivity::class.java)
                    intent.putExtra(TienSystemUtil.IS_AUTH,auth)
                    intent.putExtra(TienSystemUtil.NAME, TienSystemUtil.VINA)
                    startActivity(intent)
                }
                R.id.rl4_id -> {
                    val intent = Intent(this@TienOperatorSelectActivity, TienOperatorOneOtpActivity::class.java)
                    intent.putExtra(TienSystemUtil.IS_AUTH,auth)
                    intent.putExtra(TienSystemUtil.NAME, TienSystemUtil.VIETNAMOBILE)
                    startActivity(intent)
                }
                R.id.rl5_id -> {
                    val intent = Intent(this@TienOperatorSelectActivity, TienOperatorOneOtpActivity::class.java)
                    intent.putExtra(TienSystemUtil.IS_AUTH,auth)
                    intent.putExtra(TienSystemUtil.NAME, TienSystemUtil.SAYMEE)
                    startActivity(intent)
                }
            }
        }
    }

    private fun viewOnClick(vararg views: View) {
        views.forEach {
            it.setOnClickListener(this)
        }
    }
}