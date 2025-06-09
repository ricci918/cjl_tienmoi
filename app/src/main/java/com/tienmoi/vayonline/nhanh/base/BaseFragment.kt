package com.tienmoi.vayonline.nhanh.base

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tienmoi.vayonline.nhanh.ui.dialog.TienLoadingDialog

abstract class BaseFragment<V, P : TienBasePresenter<V>> : Fragment() {
    var presenter: P? = null
    private var isShowLoading = false
    private var dialog: Dialog? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter
        presenter?.mView = this as V
        initData()
        initView()
    }

    abstract fun initView()

    abstract fun initData()

    abstract val initPresenter: P?
    override fun onDestroy() {
        super.onDestroy()
        presenter?.mView = null
        presenter = null
    }


    open fun showLoading() {
        activity?.let {
            if (it.isFinishing || isShowLoading) {
                return
            }
            if (dialog == null) {
                dialog = TienLoadingDialog.showLoadingDialog(it)
            }
            dialog?.show()
            isShowLoading = true
        }
    }

    open fun dismissLoading() {
        activity?.let {
            if (it.isFinishing || !isShowLoading || dialog == null) {
                return
            }
            if (dialog != null) {
                TienLoadingDialog.showCloseDialog(dialog)
            }
            isShowLoading = false
        }
    }
}