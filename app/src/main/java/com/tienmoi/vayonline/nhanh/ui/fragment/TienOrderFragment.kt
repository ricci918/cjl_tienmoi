package com.tienmoi.vayonline.nhanh.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.app.TienMyApplication
import com.tienmoi.vayonline.nhanh.base.BaseFragment
import com.tienmoi.vayonline.nhanh.service.TienUpDateService
import com.tienmoi.vayonline.nhanh.databinding.FragmentOrderBinding
import com.tienmoi.vayonline.nhanh.model.contract.TienOrderFragmentContract
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq
import com.tienmoi.vayonline.nhanh.model.data.TienAcquisitionReq.TienUserDeviceInfo
import com.tienmoi.vayonline.nhanh.model.data.TienInfoData
import com.tienmoi.vayonline.nhanh.model.data.TienOrderCreateReq
import com.tienmoi.vayonline.nhanh.model.data.TienOrderData
import com.tienmoi.vayonline.nhanh.model.data.TienRenewReq
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentAccomplishData
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentData
import com.tienmoi.vayonline.nhanh.model.data.TienWithdrawReq
import com.tienmoi.vayonline.nhanh.model.utils.TienDeviceInfoUtils
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserApplicationsUtils
import com.tienmoi.vayonline.nhanh.presenter.TienOrderFragmentPresenter
import com.tienmoi.vayonline.nhanh.ui.adapter.TienOrderAdapter
import com.tienmoi.vayonline.nhanh.ui.adapter.TienRepaymentAccomplishAdapter
import com.tienmoi.vayonline.nhanh.ui.adapter.TienRepaymentAdapter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TienOrderFragment :
    BaseFragment<TienOrderFragmentContract.TienOrderFragmentView, TienOrderFragmentPresenter>(),
    TienOrderFragmentContract.TienOrderFragmentView {
    private lateinit var mBinding: FragmentOrderBinding
    private var tienCheck: Boolean? = null
    private var codeValue = ""
    private var typeValue = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun initView() {
        mBinding.apply {
            headId.tvHead.text = getString(R.string.main1)
            rb1Id.setOnClickListener {
                rv1Id.visibility = View.VISIBLE
                rv2Id.visibility = View.GONE
                rv3Id.visibility = View.GONE
                presenter?.getTienOrder()
            }
            rb2Id.setOnClickListener {
                rv1Id.visibility = View.GONE
                rv2Id.visibility = View.VISIBLE
                rv3Id.visibility = View.GONE
                presenter?.getTienRepayment()
            }
            rb3Id.setOnClickListener {
                rv1Id.visibility = View.GONE
                rv2Id.visibility = View.GONE
                rv3Id.visibility = View.VISIBLE
                presenter?.getTienRepaymentAccomplish()
            }
            sflId.setOnRefreshListener {
                presenter?.getTienOrder()
                presenter?.getTienRepayment()
                presenter?.getTienRepaymentAccomplish()
                sflId.isRefreshing = false
            }
        }

    }

    override fun initData() {
        presenter?.getTienInfo()
        presenter?.getTienCheck()
        presenter?.getTienOrder()
        presenter?.getTienRepayment()
        presenter?.getTienRepaymentAccomplish()
        val intent = Intent(
            activity,
            TienUpDateService::class.java
        )
        activity?.startService(intent)
    }

    override val initPresenter: TienOrderFragmentPresenter = TienOrderFragmentPresenter()

    @OptIn(DelicateCoroutinesApi::class)
    override fun successTienOrder(data: MutableList<TienOrderData>) {
        if (data == null || data.size == 0) {
            mBinding.tvHint.visibility = View.VISIBLE
        } else {
            mBinding.tvHint.visibility = View.GONE
        }
        val manager = LinearLayoutManager(TienMyApplication.application)
        manager.orientation = LinearLayoutManager.VERTICAL
        mBinding.rv1Id.layoutManager = manager
        val adapter = TienOrderAdapter(data, tienCheck!!, requireActivity()) { it1, it2 ->
            typeValue = it2
            codeValue = it1
            showLoading()
            GlobalScope.launch {
                presenter?.getTienAcquisition(
                    TienAcquisitionReq(
                        TienUserDeviceInfo(
                            TienDeviceInfoUtils.getTienDeviceInfo(requireActivity())
                        ), TienUserApplicationsUtils.getTienUserApplications(requireActivity())
                    )
                )
            }
        }
        mBinding.rv1Id.adapter = adapter
    }

    override fun successTienRepayment(data: MutableList<TienRepaymentData>) {
        val manager = LinearLayoutManager(TienMyApplication.application)
        manager.orientation = LinearLayoutManager.VERTICAL
        mBinding.rv2Id.layoutManager = manager
        val adapter = TienRepaymentAdapter(data, requireActivity())
        mBinding.rv2Id.adapter = adapter
    }

    override fun successTienRepaymentAccomplish(data: TienRepaymentAccomplishData) {
        val manager = LinearLayoutManager(TienMyApplication.application)
        manager.orientation = LinearLayoutManager.VERTICAL
        mBinding.rv3Id.layoutManager = manager
        val adapter = TienRepaymentAccomplishAdapter(data.v68J7LH)
        mBinding.rv3Id.adapter = adapter
    }

    override fun successTienCheck(data: Any) {
        if (data != "") {
            tienCheck = data as Boolean
        }
    }

    override fun successTienAcquisition(data: Any) {
        if (typeValue == TienSystemUtil.ONE_VALUE) {
            presenter?.getTienRenew(TienRenewReq(codeValue))
        } else if (typeValue == TienSystemUtil.TWO_VALUE) {
            presenter?.getTienWithdraw(TienWithdrawReq(codeValue))
        } else if (typeValue == TienSystemUtil.THREE_VALUE) {
            presenter?.getTienCreate(TienOrderCreateReq())
        }
    }

    override fun successTienWithdraw(data: Any) {
        dismissLoading()
        TienToastUtil.myToast(getString(R.string.fragment27))
        EventBus.getDefault().post(TienSystemUtil.REFRESH_EVENT)
    }

    override fun successTienRenew(data: Any) {
        dismissLoading()
        EventBus.getDefault().post(TienSystemUtil.REFRESH_EVENT)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun successTienInfo(data: TienInfoData) {
        if (!data.BXkEwmn) {
            GlobalScope.launch {
                showLoadingOne()
                typeValue = TienSystemUtil.THREE_VALUE
                presenter?.getTienAcquisition(
                    TienAcquisitionReq(
                        TienUserDeviceInfo(
                            TienDeviceInfoUtils.getTienDeviceInfo(requireActivity())
                        ), TienUserApplicationsUtils.getTienUserApplications(requireActivity())
                    )
                )
            }
        }
    }

    override fun successTienCreate(data: Any) {
        EventBus.getDefault().post(TienSystemUtil.REFRESH_EVENT)
    }

    override fun error() {
        dismissLoading()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receptionEvent(event: String) {
        if (event == TienSystemUtil.REFRESH_EVENT) {
            presenter?.getTienCheck()
            presenter?.getTienOrder()
            presenter?.getTienRepayment()
            presenter?.getTienRepaymentAccomplish()
        } else if (event == TienSystemUtil.OPERATOR) {
            TienToastUtil.myToast(getString(R.string.fragment26))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}