package com.tienmoi.vayonline.nhanh.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tienmoi.vayonline.nhanh.base.TienBaseActivityNoData
import com.tienmoi.vayonline.nhanh.databinding.ActivityMainBinding
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.ui.dialog.TienPositiveReviewGuidance
import com.tienmoi.vayonline.nhanh.ui.dialog.TienUpdateDialog
import com.tienmoi.vayonline.nhanh.ui.fragment.TienInformFragment
import com.tienmoi.vayonline.nhanh.ui.fragment.TienMinFragment
import com.tienmoi.vayonline.nhanh.ui.fragment.TienOrderFragment

class TienMainActivity : TienBaseActivityNoData() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initLayout(): View = mBinding.root

    override fun initView() {
        mBinding.apply {
            val goodReputation = intent.getStringExtra(TienSystemUtil.GOOD)
            if (goodReputation == TienSystemUtil.GOOD) {
                TienPositiveReviewGuidance.showPositiveReviewGuidanceDialog(this@TienMainActivity)
            }
//            if (TienSharedPreferencesUtil.getSystemInfoData()?.S40ZAju!! > TienSystemUtil.getTienVersionCode(
//                    this@TienMainActivity
//                )
//            ) {
//                TienUpdateDialog.showUpDateDialog(
//                    this@TienMainActivity,
//                    TienSharedPreferencesUtil.getSystemInfoData()?.xZv00i0!!
//                )
//            }
            val arrayListOf = arrayListOf<Fragment>()
            arrayListOf.add(TienOrderFragment())
            arrayListOf.add(TienInformFragment())
            arrayListOf.add(TienMinFragment())
            vpId.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
                override fun getCount(): Int = arrayListOf.size

                override fun getItem(position: Int): Fragment =
                    arrayListOf[position]
            }
            vpId.offscreenPageLimit = 2

            rb1Id.setOnClickListener {
                vpId.currentItem = 0
            }
            rb2Id.setOnClickListener {
                vpId.currentItem = 1
            }
            rb3Id.setOnClickListener {
                vpId.currentItem = 2
            }
        }

    }
}
