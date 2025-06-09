package com.tienmoi.vayonline.nhanh.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.databinding.FragmentInformBinding

class TienInformFragment : Fragment() {
    private lateinit var mBinding: FragmentInformBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentInformBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        mBinding.headId.tvHead.text = getString(R.string.main2)
    }
}