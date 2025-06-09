package com.tienmoi.vayonline.nhanh.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tienmoi.vayonline.nhanh.ui.dialog.TienLoadingDialog

abstract class TienBaseActivity<V, P : TienBasePresenter<V>> : AppCompatActivity() {
    var presenter: P? = null
    private var dialog: Dialog? = null
    private var dialogOne: Dialog? = null
    private var isShowLoading = false
    private var isShowLoadingOne = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(initLayout())
        presenter = initPresenter()
        presenter?.mView = this as V
        initData()
        initView()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initPresenter(): P?

    abstract fun initLayout(): View

    fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    open fun showLoading() {
        if (isFinishing || isShowLoading) {
            return
        }
        if (dialog == null) {
            dialog = TienLoadingDialog.showLoadingDialog(this)
        }
        dialog?.show()
        isShowLoading = true
    }

    open fun dismissLoading() {
        if (isFinishing || !isShowLoading || dialog == null) {
            return
        }
        TienLoadingDialog.showCloseDialog(dialog)
        isShowLoading = false
    }

    open fun showLoadingOne() {
        if (isFinishing || isShowLoading) {
            return
        }
        if (dialogOne == null) {
            dialogOne = TienLoadingDialog.showLoadingDialogOne(this)
        }
        dialogOne?.show()
        isShowLoadingOne = true
    }

    open fun dismissLoadingOne() {
        if (isFinishing || !isShowLoadingOne || dialogOne == null) {
            return
        }
        TienLoadingDialog.showCloseDialogOne(dialogOne)
        isShowLoadingOne = false
    }
}