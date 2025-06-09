package com.tienmoi.vayonline.nhanh.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

abstract class TienBaseActivityNoData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(initLayout())
        initView()
    }

    abstract fun initLayout(): View

    abstract fun initView()

    fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}