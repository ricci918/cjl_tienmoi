package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.view.View
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.base.TienBaseActivity
import com.tienmoi.vayonline.nhanh.databinding.ActivityCertificationTwoBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienCertificationTwoContract
import com.tienmoi.vayonline.nhanh.model.data.TienAddAttachInfoReq
import com.tienmoi.vayonline.nhanh.model.data.TienAreaList
import com.tienmoi.vayonline.nhanh.model.data.TienAreaListData
import com.tienmoi.vayonline.nhanh.model.data.TienAttach
import com.tienmoi.vayonline.nhanh.model.data.TienUserFieldCodeData
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.presenter.TienCertificationTwoPresenter
import com.tienmoi.vayonline.nhanh.ui.dialog.TienKycDialog

class TienCertificationTwoActivity :
    TienBaseActivity<TienCertificationTwoContract.TienCertificationTwoView, TienCertificationTwoPresenter>(),
    TienCertificationTwoContract.TienCertificationTwoView {
    private lateinit var mBinding: ActivityCertificationTwoBinding
    private var jobValue = -1
    private var salaryValue = -1
    private var relation1Value = -1
    private var relation2Value = -1
    private var tienAreaListData: MutableList<TienAreaListData>? = null
    private var tienAreaList: MutableList<TienAreaList>? = null
    private var tienProvince = ""
    private var tienCity = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityCertificationTwoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            ll2Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.TdQFxgy?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationTwoActivity,
                        tv2Id.text.toString(), it1
                    ) {
                        jobValue = it.value
                        et1Id.text = it.name
                    }
                }
            }
            ll3Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.MNlZhKN?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationTwoActivity,
                        tv3Id.text.toString(), it1
                    ) {
                        salaryValue = it.value
                        et2Id.text = it.name
                    }
                }
            }
            ll4Id.setOnClickListener {
                tienAreaListData?.let { it1 ->
                    TienKycDialog.showTienProvinceDialog(
                        this@TienCertificationTwoActivity, tv7Id.text.toString(),
                        it1
                    ) { it2, it3 ->
                        tienAreaList = it3
                        et6Id.text = it2.lCTsCt4
                        tienCity = ""
                        et7Id.text = ""
                        tienProvince = it2.QCxS882
                    }
                }
            }
            ll5Id.setOnClickListener {
                tienAreaList?.let { it1 ->
                    TienKycDialog.showTienCityDialog(
                        this@TienCertificationTwoActivity, tv8Id.text.toString(),
                        it1
                    ) {
                        et7Id.text = it.name
                        tienCity = it.value
                    }
                }
            }
            ll6Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.txexK1x?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationTwoActivity,
                        tv13Id.text.toString(), it1
                    ) {
                        relation1Value = it.value
                        et11Id.text = it.name
                    }
                }
            }
            ll7Id.setOnClickListener {
                TienSharedPreferencesUtil.getUserFieldCode()?.EY5jSyv?.txexK1x?.let { it1 ->
                    TienKycDialog.showTienKycDialog(
                        this@TienCertificationTwoActivity,
                        tv16Id.text.toString(), it1
                    ) {
                        relation2Value = it.value
                        et14Id.text = it.name
                    }
                }
            }
            tvNextStep.setOnClickListener {
                if (TienSystemUtil.isFastClick(800)) {
                    return@setOnClickListener
                }
                val arrayListOf = arrayListOf<TienAttach>()
                arrayListOf.add(
                    TienAttach(
                        et9Id.text.toString(),
                        et10Id.text.toString(),
                        relation1Value, 0
                    )
                )
                arrayListOf.add(
                    TienAttach(
                        et12Id.text.toString(),
                        et13Id.text.toString(),
                        relation2Value, 1
                    )
                )
                if (jobValue != -1 && salaryValue != -1 && relation1Value != -1 && relation2Value != -1 && et3Id.text.toString() != "" && et5Id.text.toString() != "" && tienProvince != "" && tienCity != "" && et8Id.text.toString() != "" && et9Id.text.toString() != "" && et10Id.text.toString() != "" && et12Id.text.toString() != "" && et13Id.text.toString() != "") {
                    showLoading()
                    presenter?.getTienAddAttachInfo(
                        TienAddAttachInfoReq(
                            jobValue,
                            salaryValue,
                            et3Id.text.toString(),
                            et5Id.text.toString(),
                            tienProvince,
                            tienCity,
                            et8Id.text.toString(),
                            arrayListOf
                        )
                    )
                } else {
                    TienToastUtil.myToast(getString(R.string.certificationOne17))
                }

            }
            tvReturn.setOnClickListener {
                finish()
            }
        }
    }

    override fun initData() {
        presenter?.getTienUserFieldCode()
        presenter?.getTienAreaList()
    }

    override fun initPresenter(): TienCertificationTwoPresenter = TienCertificationTwoPresenter()
    override fun successTienUserFieldCode(data: TienUserFieldCodeData) {
        TienSharedPreferencesUtil.putUserFieldCode(data)
    }

    override fun successTienAreaList(data: MutableList<TienAreaListData>) {
        tienAreaListData = data
    }

    override fun successTienAddAttachInfo(data: Any) {
        dismissLoading()
        startActivity(TienCertificationThreeActivity::class.java)
    }

    override fun error() {
        dismissLoading()
    }
}