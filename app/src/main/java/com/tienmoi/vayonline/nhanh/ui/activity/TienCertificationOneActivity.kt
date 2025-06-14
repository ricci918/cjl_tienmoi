package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityCertificationOneBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienCertificationOneContract
import com.tienmoi.vayonline.nhanh.model.data.TienAddBasicInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.presenter.TienCertificationOnePresenter
import com.tienmoi.vayonline.nhanh.ui.dialog.TienKycDialog
import java.util.regex.Pattern

class TienCertificationOneActivity :
    TienBaseActivity<TienCertificationOneContract.TienCertificationOneView, TienCertificationOnePresenter>(),
    TienCertificationOneContract.TienCertificationOneView {
    private lateinit var mBinding: ActivityCertificationOneBinding
    private var sexValue = -1
    private var educationValue = -1
    private var marriageValue = -1
    private var isLocalEmail = false
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityCertificationOneBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            ll3Id.setOnClickListener {
                TienKycDialog.showTienKycDataDialog(
                    this@TienCertificationOneActivity,
                    tv4Id.text.toString()
                ) {
                    et3Id.text = it
                }
            }
            ll4Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.IgCmMzZ?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationOneActivity,
                        tv5Id.text.toString(), it1
                    ) {
                        sexValue = it.value
                        et4Id.text = it.name
                    }
                }
            }
            ll5Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.l0WqmlZ?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationOneActivity,
                        tv6Id.text.toString(), it1
                    ) {
                        educationValue = it.value
                        et5Id.text = it.name
                    }
                }
            }
            ll6Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.OthrTqQ?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationOneActivity,
                        tv7Id.text.toString(), it1
                    ) {
                        marriageValue = it.value
                        et6Id.text = it.name
                    }
                }
            }
            tvReturn.setOnClickListener { finish() }
            tvNextStep.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                if (et1Id.text.toString() != "" && et2Id.text.toString() != "" && et3Id.text.toString() != "" && sexValue != -1 && educationValue != -1 && marriageValue != -1 && et7Id.text.toString() != "" && et8Id.text.toString() != "" && isLocalEmail) {
                    showLoading()
                    presenter?.getTienAddBasicInfo(
                        TienAddBasicInfoReq(
                            et1Id.text.toString(),
                            et2Id.text.toString(),
                            et3Id.text.toString(),
                            sexValue,
                            educationValue,
                            marriageValue,
                            et7Id.text.toString(),
                            et8Id.text.toString(), et9Id.text.toString()
                        )
                    )
                } else {
                    TienToastUtil.myToast(getString(R.string.certificationOne17))
                }

            }
            tvReturn.setOnClickListener {
                finish()
            }
            et9Id.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    isLocalEmail =
                        Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
                            .matcher(et9Id.text.toString())
                            .matches()
                    if (isLocalEmail) {
                        tv11Id.visibility = View.GONE
                    } else {
                        tv11Id.visibility = View.VISIBLE
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
        }
    }

    override fun initData() {
        presenter?.getTienUserFieldCode()
    }

    override fun initPresenter(): TienCertificationOnePresenter = TienCertificationOnePresenter()

    override fun successTienUserFieldCode(data: TienUserFieldCodeData) {
        TienSharedPreferencesUtil.putUserFieldCode(data)
    }

    override fun successTienAddBasicInfo(data: Any) {
        dismissLoading()
        TienSharedPreferencesUtil.putTienName(mBinding.et1Id.text.toString())
        startActivity(TienCertificationTwoActivity::class.java)
    }

    override fun error() {
        dismissLoading()
    }
}