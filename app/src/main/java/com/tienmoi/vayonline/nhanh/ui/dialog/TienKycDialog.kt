package com.tienmoi.vayonline.nhanh.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.gzuliyujiang.wheelview.widget.WheelView
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.data.TienAreaList
import com.tienmoi.vayonline.nhanh.model.data.TienAreaListData
import com.tienmoi.vayonline.nhanh.model.data.TienBankListData
import com.tienmoi.vayonline.nhanh.model.data.TienContentData
import com.tienmoi.vayonline.nhanh.model.utils.TienUiUtil
import com.tienmoi.vayonline.nhanh.ui.adapter.TienBankAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object TienKycDialog {
    fun showTienKycDataDialog(
        activity: Activity,
        text: String,
        onSelectedListener: (String) -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_kyc_data_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val date = view.findViewById<DatePicker>(R.id.date_id)
        tv1.text = text
        val format = SimpleDateFormat("2000-01-01")
        var da = format.format(Date())
        date.init(2000, 0, 1, null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                val month = (monthOfYear + 1).toString().padStart(2, '0')
                da = "$year-$month-${dayOfMonth.toString().padStart(2, '0')}"
            }
        }
        tv2.setOnClickListener {
            onSelectedListener.invoke(da)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun showTienKycDialog(
        activity: Activity,
        text: String,
        tienContentData: MutableList<TienContentData>,
        onSelectedListener: (TienContentData) -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_kyc_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        tv1.text = text
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val list = arrayListOf<String>()
        tienContentData.forEach {
            list.add(it.name)
        }
        wv.data = list
        wv.setDefaultValue(nowYear)
        tv2.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            tienContentData.forEach {
                if (it.name == currentItem) {
                    onSelectedListener.invoke(it)
                }
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    fun showTienProvinceDialog(
        activity: Activity,
        text: String,
        tienAreaListData: MutableList<TienAreaListData>,
        onSelectedListener: (TienAreaListData, MutableList<TienAreaList>) -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_kyc_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        tv1.text = text
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val list = arrayListOf<String>()
        tienAreaListData.forEach {
            list.add(it.lCTsCt4)
        }
        wv.data = list
        wv.setDefaultValue(nowYear)
        tv2.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            tienAreaListData.forEach {
                if (it.lCTsCt4 == currentItem) {
                    onSelectedListener.invoke(it, it.f3SSikB)
                }
            }
            dialog.dismiss()
        }
        dialog.show()
    }


    fun showTienCityDialog(
        activity: Activity,
        text: String,
        tienAreaListData: MutableList<TienAreaList>,
        onSelectedListener: (TienAreaList) -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_kyc_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val tv2 = view.findViewById<TextView>(R.id.tv2_id)
        val wv = view.findViewById<WheelView>(R.id.wv_id)
        tv1.text = text
        val calendar: Calendar = Calendar.getInstance()
        val nowYear: Int = calendar.get(Calendar.YEAR)
        val list = arrayListOf<String>()
        tienAreaListData.forEach {
            list.add(it.name)
        }
        wv.data = list
        wv.setDefaultValue(nowYear)
        tv2.setOnClickListener {
            val currentItem = wv.getCurrentItem<String>()
            tienAreaListData.forEach {
                if (it.name == currentItem) {
                    onSelectedListener.invoke(it)
                }
            }
            dialog.dismiss()
        }
        dialog.show()
    }


    fun showTienBankDialog(
        activity: Activity,
        text: String,
        tienAreaListData: MutableList<TienBankListData>,
        onSelectedListener: (TienBankListData) -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_kyc1_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv1 = view.findViewById<TextView>(R.id.tv1_id)
        val rv = view.findViewById<RecyclerView>(R.id.rv_id)
        val et = view.findViewById<EditText>(R.id.et1_id)
        tv1.text = text
        val arrayListOf1 = arrayListOf<TienBankListData>()
        val manager = LinearLayoutManager(activity)
        rv.layoutManager = manager
        val adapter = TienBankAdapter(tienAreaListData) {
            onSelectedListener.invoke(it)
            dialog.dismiss()
        }
        rv.adapter = adapter
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                arrayListOf1.clear()
                if (et.text.toString() == "") {
                    val manager1 = LinearLayoutManager(activity)
                    rv.layoutManager = manager1
                    val adapter1 = TienBankAdapter(tienAreaListData) {
                        onSelectedListener.invoke(it)
                        dialog.dismiss()
                    }
                    rv.adapter = adapter1
                } else {
                    tienAreaListData.forEach {
                        if (it.g3jMqJO.contains(et.text.toString().uppercase())) {
                            arrayListOf1.add(it)
                        }
                    }
                    val manager2 = LinearLayoutManager(activity)
                    rv.layoutManager = manager2
                    val adapter2 = TienBankAdapter(arrayListOf1) {
                        onSelectedListener.invoke(it)
                        dialog.dismiss()
                    }
                    rv.adapter = adapter2

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        dialog.show()
    }

    fun showTienCameraDialog(
        activity: Activity,
        onSelectedListener: () -> Unit
    ) {
        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.tien_camera_dialog, null)
        val dialog = Dialog(activity, R.style.MyDialogStyle)
        val dialogWindow = dialog.window
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        val lp = dialogWindow!!.attributes
        val mScreenWidth: Int = TienUiUtil.getScreenWidthPixels(activity)
        lp.width = (0.9 * mScreenWidth).toInt()
        dialogWindow.attributes = lp
        val tv4 = view.findViewById<TextView>(R.id.tv4_id)
        tv4.setOnClickListener {
            onSelectedListener.invoke()
            dialog.dismiss()
        }
        dialog.show()
    }
}