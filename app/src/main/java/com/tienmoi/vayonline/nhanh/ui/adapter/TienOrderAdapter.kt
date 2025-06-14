package com.tienmoi.vayonline.nhanh.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.data.TienOrderData
import com.tienmoi.vayonline.nhanh.model.utils.TienHttpUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.ui.activity.TienOperatorSelectActivity
import com.tienmoi.vayonline.nhanh.ui.activity.TienOrderDetailsActivity
import com.tienmoi.vayonline.nhanh.ui.activity.TienRepaymentActivity
import com.tienmoi.vayonline.nhanh.ui.dialog.TienZaloDialog

class TienOrderAdapter(
    private val data: MutableList<TienOrderData>,
    private val check: Boolean,
    private val activity: Activity,
    private val onSelectedListener: (String, Int) -> Unit
) :
    RecyclerView.Adapter<TienOrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.tien_order_adapter_item, parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].let { it1 ->
            holder.itemView.apply {
                val icon = holder.itemView.findViewById<ImageView>(R.id.iv_icon)
                val phone = holder.itemView.findViewById<ImageView>(R.id.iv_phone)
                val zalo = holder.itemView.findViewById<ImageView>(R.id.iv_zalo)
                val name = holder.itemView.findViewById<TextView>(R.id.tv_name)
                val type = holder.itemView.findViewById<TextView>(R.id.tv_type)
                val money = holder.itemView.findViewById<TextView>(R.id.tv_money)
                val hint = holder.itemView.findViewById<TextView>(R.id.tv_hint)
                val button1 = holder.itemView.findViewById<TextView>(R.id.tv_button1)
                val button2 = holder.itemView.findViewById<TextView>(R.id.tv_button2)
                val operator = holder.itemView.findViewById<TextView>(R.id.tv_operator)
                val hint1 = holder.itemView.findViewById<TextView>(R.id.tv_hint1)

                if (TienSystemUtil.isTienNumericRegex(it1.WIfYjCc)) {
                    Glide.with(context)
                        .load((TienHttpUtil.BASE_URL + "/Xqm5ED7/I4Tnli4?hY2WwpV=" + it1.WIfYjCc))
                        .disallowHardwareConfig()
                        .into(icon)
                } else {
                    Glide.with(context)
                        .load(it1.WIfYjCc)
                        .disallowHardwareConfig()
                        .into(icon)
                }
                name.text = it1.W08TE1A
                when (it1.NdF7dMC) {
                    "AUDITING" -> { //审核中
                        if (check) {
                            operator.visibility = View.VISIBLE
                            hint1.visibility = View.VISIBLE
                        }
                        money.visibility = View.GONE
                        type.setTextColor(context.getColor(R.color.color_0063d8))
                        type.text = context.getString(R.string.fragment3)
                        hint.text = context.getString(R.string.fragment2)

                    }

                    "REJECT" -> { //审核被拒绝
                        money.visibility = View.GONE
                        type.text = context.getString(R.string.fragment6)
                        if (it1.SKu2316 != 0) {
                            hint.text =
                                String.format(context.getString(R.string.fragment4), it1.SKu2316)
                            type.setTextColor(context.getColor(R.color.color_0063d8))
                        } else {
                            button1.text = context.getString(R.string.fragment22)
                            button1.visibility = View.VISIBLE
                            hint.text = context.getString(R.string.fragment5)
                            type.setTextColor(context.getColor(R.color.color_60a7fe))
                        }
                        if (check) {
                            operator.visibility = View.VISIBLE
                            hint1.visibility = View.VISIBLE
                        }
                    }

                    "WAIT_TO_WITHDRAW" -> { //待提现
                        type.setTextColor(context.getColor(R.color.color_0063d8))
                        type.text = context.getString(R.string.fragment7)
                        hint.text = context.getString(R.string.fragment8)
                        money.text = context.getString(R.string.fragment9) + it1.NgL6l7J
                        button2.visibility = View.VISIBLE
                        button2.text = context.getString(R.string.fragment24)
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                        hint1.visibility = View.VISIBLE
                        hint1.text = context.getString(R.string.fragment29)
                    }

                    "PAYING" -> { //打款中
                        type.setTextColor(context.getColor(R.color.color_0063d8))
                        type.text = context.getString(R.string.fragment10)
                        hint.text = context.getString(R.string.fragment11)
                        money.text = context.getString(R.string.fragment9) + it1.NgL6l7J
                        hint1.visibility = View.GONE
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                    }

                    "PAY_ERROR" -> { //打款出错
                        type.setTextColor(context.getColor(R.color.color_0063d8))
                        type.text = context.getString(R.string.fragment10)
                        hint.text = context.getString(R.string.fragment11)
                        money.text = context.getString(R.string.fragment9) + it1.NgL6l7J
                        hint1.visibility = View.GONE
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                    }

                    "OVERDUE" -> { //已逾期
                        type.setTextColor(context.getColor(R.color.color_fa2209))
                        money.setTextColor(context.getColor(R.color.color_fa2209))
                        hint.setTextColor(context.getColor(R.color.color_fa2209))
                        type.text =
                            context.getString(R.string.fragment12) + TienSystemUtil.getTienDateToString(
                                it1.IB3fW4Q,
                                "yyyy-MM-dd"
                            )
                        hint.text =
                            String.format(context.getString(R.string.fragment13), it1.qs63gHd)
                        money.text = context.getString(R.string.fragment14) + it1.Z44cWo1
                        hint1.visibility = View.GONE
                        button2.visibility = View.VISIBLE
                        button2.text = context.getString(R.string.fragment25)
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                    }

                    "TO_REPAYMENT" -> { //待还款
                        type.text =
                            context.getString(R.string.fragment12) + TienSystemUtil.getTienDateToString(
                                it1.IB3fW4Q,
                                "yyyy-MM-dd"
                            )
                        hint.text =
                            context.getString(R.string.fragment15)
                        money.text = context.getString(R.string.fragment14) + it1.Z44cWo1
                        hint1.visibility = View.GONE
                        button2.visibility = View.VISIBLE
                        button2.text = context.getString(R.string.fragment25)
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                    }

                    "CANCELED" -> { //订单被取消
                        if (check) {
                            operator.visibility = View.VISIBLE
                            hint1.visibility = View.VISIBLE
                        }
                        money.visibility = View.GONE
                        type.text = context.getString(R.string.fragment6)
                        if (it1.SKu2316 != 0) {
                            hint.text =
                                String.format(context.getString(R.string.fragment4), it1.SKu2316)
                            type.setTextColor(context.getColor(R.color.color_0063d8))
                        } else {
                            button1.text = context.getString(R.string.fragment22)
                            button1.visibility = View.VISIBLE
                            hint.text = context.getString(R.string.fragment5)
                            type.setTextColor(context.getColor(R.color.color_60a7fe))
                        }
                    }

                    "END" -> { //已完成
                        type.text =
                            context.getString(R.string.fragment16) + TienSystemUtil.getTienDateToString(
                                it1.IB3fW4Q,
                                "yyyy-MM-dd"
                            )
                        hint.text =
                            context.getString(R.string.fragment17)
                        money.text = context.getString(R.string.fragment9) + it1.NgL6l7J
                        hint1.visibility = View.GONE
                    }

                    "OTHER_ERROR" -> { //未知错误
                        hint1.visibility = View.GONE
                    }
                }
                button2.setOnClickListener {
                    if (TienSystemUtil.isFastClick(800)) {
                        return@setOnClickListener
                    }
                    if (button2.text.toString() == context.getString(R.string.fragment24)) { //提现
                        onSelectedListener.invoke(it1.Ihr8GkY, TienSystemUtil.TWO_VALUE)
                    }
                    if (button2.text.toString() == context.getString(R.string.fragment25)) {//还款
                        val intent = Intent(context, TienRepaymentActivity::class.java)
                        intent.putExtra(TienSystemUtil.ORDER_CODE, it1.Ihr8GkY)
                        context.startActivity(intent)
                    }
                }
                button1.setOnClickListener {
                    if (TienSystemUtil.isFastClick(800)) {
                        return@setOnClickListener
                    }
                    if (button1.text.toString() == context.getString(R.string.fragment23)) {
                        val intent = Intent(context, TienOrderDetailsActivity::class.java)
                        intent.putExtra(TienSystemUtil.ORDER_CODE, it1.Ihr8GkY)
                        context.startActivity(intent)
                    } else if (button1.text.toString() == context.getString(R.string.fragment22)) {//重新申请
                        onSelectedListener.invoke(it1.lYk2Ii4, TienSystemUtil.ONE_VALUE)
                    }
                }

                operator.setOnClickListener {
                    if (TienSystemUtil.isFastClick(800)) {
                        return@setOnClickListener
                    }
                    val intent = Intent(context, TienOperatorSelectActivity::class.java)
                    context.startActivity(intent)
                }
                zalo.setOnClickListener {
                    if (TienSystemUtil.isFastClick(800)) {
                        return@setOnClickListener
                    }
                    TienZaloDialog.showZaloDialog(activity, it1.Zy5DsZG)
                }
                phone.setOnClickListener {
                    if (TienSystemUtil.isFastClick(800)) {
                        return@setOnClickListener
                    }
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        ("tel:" + it1.Zy5DsZG).toUri()
                    )
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}