package com.tienmoi.vayonline.nhanh.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUiUtil

object TienUpdateDialog {
    fun showUpDateDialog(activity: Activity, isConstraint: Boolean){
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.update_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        if (isConstraint) {
            tv1.text = activity.getString(R.string.dialog21)
        } else {
            tv1.text = activity.getString(R.string.dialog20)
        }
        if (isConstraint){
            tv3.visibility = View.GONE
        }
        tv3.setOnClickListener {
            if (isConstraint) {
                activity.finishAffinity()
            } else {
                dialog.dismiss()
            }
        }
        tv2.setOnClickListener {
            TienSystemUtil.tienLaunchAppDetail(activity, TienSystemUtil.getTienPageName(activity))
        }
        dialog.show()
    }
}