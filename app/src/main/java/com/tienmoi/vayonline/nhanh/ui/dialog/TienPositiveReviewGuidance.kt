package com.tienmoi.vayonline.nhanh.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.api.TienApiServiceObject
import com.tienmoi.vayonline.nhanh.model.data.TienCommitReq
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUiUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object TienPositiveReviewGuidance {

    fun showPositiveReviewGuidanceDialog(activity: Activity) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_positive_review_guidance_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val rb = view.findViewById<RatingBar>(R.id.rb_id)
        val tv = view.findViewById<TextView>(R.id.tv2_id)
        rb.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                if (rating >= 4.5) {
                    showPositiveReviewGuidanceDialog1(activity)
                    dialog.dismiss()
                } else {
                    showPositiveReviewGuidanceDialog2(activity, rating.toString())
                    dialog.dismiss()
                }
            }
        tv.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showPositiveReviewGuidanceDialog1(activity: Activity) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_positive_review_guidance_dialog1, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        tv2.setOnClickListener {
            TienSystemUtil.tienLaunchAppDetail(activity, TienSystemUtil.getTienPageName(activity))
            dialog.dismiss()
        }
        tv3.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showPositiveReviewGuidanceDialog2(activity: Activity, rating: String) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_positive_review_guidance_dialog2, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        tv2.setOnClickListener {
            showPositiveReviewGuidanceDialog3(activity, rating)
            dialog.dismiss()
        }
        tv3.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun showPositiveReviewGuidanceDialog3(activity: Activity, rating: String) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.good_positive_review_guidance_dialog3, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val tv3 = view.findViewById<TextView>(R.id.tv3_id)
        val et = view.findViewById<TextView>(R.id.et1_id)
        tv2.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val tienCommit =
                        TienApiServiceObject.tienCommit(TienCommitReq(rating, et.text.toString()))
                    withContext(Dispatchers.Main) {
                        TienToastUtil.myToast(activity.getString(R.string.dialog10))
                        dialog.dismiss()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        if (e.message != "" && e.message != null) {
                            TienToastUtil.myToast(e.message!!)
                        }
                    }
                }
            }

        }
        tv3.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}