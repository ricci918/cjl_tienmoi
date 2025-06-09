package com.tienmoi.vayonline.nhanh.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUiUtil

object TienZaloDialog {
    fun showZaloDialog(
        activity: Activity,
        phoneNumber: String
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.zalo_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val phone = view.findViewById<TextView>(R.id.phone_id)
        val copy = view.findViewById<TextView>(R.id.copy_id)
        phone.text = phoneNumber ?: ""
        copy.setOnClickListener {
            val clipboard: ClipboardManager =
                activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("simple text", phoneNumber)
            clipboard.setPrimaryClip(clip)
            TienToastUtil.myToast(activity.getString(R.string.dialog9))
            dialog.dismiss()
        }

        dialog.show()
    }
}