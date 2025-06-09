package com.tienmoi.vayonline.nhanh.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.tienmoi.vayonline.nhanh.R

object TienLoadingDialog {
    fun showLoadingDialog(context: Context?): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_loading, null)
        val layout = v.findViewById<View>(R.id.rl1_id) as RelativeLayout
        val loadingDialog = context?.let { Dialog(it, R.style.MyDialogStyle) }
        loadingDialog?.setCancelable(true)
        loadingDialog?.setCanceledOnTouchOutside(false)
        loadingDialog?.setContentView(
            layout, RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        )
        val window = loadingDialog?.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        return loadingDialog
    }

    fun showCloseDialog(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    private lateinit var countDownTimer: CountDownTimer
    fun showLoadingDialogOne(context: Context?): Dialog? {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.dialog_one_loading, null)
        val layout = v.findViewById<View>(R.id.rl1_id) as RelativeLayout
        val tipTextView = v.findViewById<View>(R.id.tipTextView) as TextView
        val loadingDialog = context?.let { Dialog(it, R.style.MyDialogStyle) }
        loadingDialog?.setCancelable(true)
        loadingDialog?.setCanceledOnTouchOutside(false)
        loadingDialog?.setContentView(
            layout, RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        )
        var countTime = 30
        countDownTimer = object : CountDownTimer(30000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {

            }

            @SuppressLint("SimpleDateFormat", "SetTextI18n")
            override fun onTick(time: Long) {
                countTime--
                tipTextView.text = String.format(
                    context?.getString(R.string.dialog23).toString(), countTime
                )
            }
        }.start()
        val window = loadingDialog?.window
        val lp = window!!.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        return loadingDialog
    }

    fun showCloseDialogOne(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            countDownTimer.cancel()
            dialog.dismiss()
        }
    }

}