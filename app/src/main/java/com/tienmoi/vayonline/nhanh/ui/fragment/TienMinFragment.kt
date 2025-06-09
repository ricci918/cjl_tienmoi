package com.tienmoi.vayonline.nhanh.ui.fragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.databinding.FragmentMinBinding
import com.tienmoi.vayonline.nhanh.model.utils.TienSharedPreferencesUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienToastUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienUserUtil

class TienMinFragment : Fragment(), View.OnClickListener {
    private lateinit var mBinding: FragmentMinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMinBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        mBinding.apply {
            viewOnClick(tv3Id, tv4Id, tv5Id, tv6Id, tv7Id)
            headId.tvHead.text = getString(R.string.main3)
            tv1Id.text = TienSharedPreferencesUtil.getTienName()
            tv2Id.text = TienSharedPreferencesUtil.getTienPhone()
            tv5Id.text =
                getString(R.string.min3) + TienSharedPreferencesUtil.getSystemInfoData()?.GuE7zXx

//                getString(R.string.min4) + TienSharedPreferencesUtil.getSystemInfoData()?.ythTb7E

            tv6Id.text = getString(R.string.min3) + "support@tienmoivayonline.com"
        }
    }

    override fun onClick(v: View?) {
        v?.apply {
            when (id) {
                R.id.tv3_id -> {
                    TienToastUtil.myToast(getString(R.string.min6) + "(" + TienSharedPreferencesUtil.getSystemInfoData()?.GuE7zXx + ")")
                }

                R.id.tv4_id -> {
                    TienToastUtil.myToast(getString(R.string.min7))
                }

                R.id.tv5_id -> {
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        ("tel:" + TienSharedPreferencesUtil.getSystemInfoData()?.GuE7zXx).toUri()
                    )
                    startActivity(intent)
                }

                R.id.tv6_id -> {
                    val clipboard: ClipboardManager =
                        activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText(
                        "simple text",
                        TienSharedPreferencesUtil.getSystemInfoData()?.ythTb7E
                    )
                    clipboard.setPrimaryClip(clip)
                    TienToastUtil.myToast(getString(R.string.min8))
                }

                R.id.tv7_id -> {
                    TienUserUtil.tienLogout()
                    activity?.finish()
                }
            }
        }
    }

    private fun viewOnClick(vararg views: View) {
        views.forEach {
            it.setOnClickListener(this)
        }
    }
}