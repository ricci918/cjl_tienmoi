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
import com.tienmoi.vayonline.nhanh.model.data.TienRepaymentData
import com.tienmoi.vayonline.nhanh.model.utils.TienHttpUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil
import com.tienmoi.vayonline.nhanh.ui.activity.TienOperatorSelectActivity
import com.tienmoi.vayonline.nhanh.ui.activity.TienOrderDetailsActivity
import com.tienmoi.vayonline.nhanh.ui.activity.TienRepaymentAccountActivity
import com.tienmoi.vayonline.nhanh.ui.dialog.TienZaloDialog

class TienRepaymentAdapter(
    private val result: MutableList<TienRepaymentData>, private val activity: Activity,
) :
    RecyclerView.Adapter<TienRepaymentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.tien_order_adapter_item, parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        result[position].let { it1 ->
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
                val hint1 = holder.itemView.findViewById<TextView>(R.id.tv_hint1)
                if (TienSystemUtil.isTienNumericRegex(it1.Xi3fkJQ)) {
                    Glide.with(context)
                        .load(TienHttpUtil.BASE_URL + "/Xqm5ED7/I4Tnli4?hY2WwpV=" + it1.Xi3fkJQ)
                        .disallowHardwareConfig()
                        .into(icon)
                } else {
                    Glide.with(context)
                        .load(it1.Xi3fkJQ)
                        .disallowHardwareConfig()
                        .into(icon)
                }
                name.text = it1.vRCK8PE
                when (it1.OJDCyYH) {
                    "OVERDUE" -> { //已逾期
                        type.setTextColor(context.getColor(R.color.color_fa2209))
                        money.setTextColor(context.getColor(R.color.color_fa2209))
                        hint.setTextColor(context.getColor(R.color.color_fa2209))
                        type.text =
                            context.getString(R.string.fragment12) + TienSystemUtil.getTienDateToString(
                                it1.m2f1OnN.toString(),
                                "yyyy-MM-dd"
                            )
                        hint.text =
                            String.format(context.getString(R.string.fragment13), it1.hRIukEO)
                        money.text = context.getString(R.string.fragment14) + it1.aY06zTe
                        hint1.visibility = View.GONE
                        button2.visibility = View.VISIBLE
                        button2.text = context.getString(R.string.fragment25)
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                    }


                    "TO_REPAYMENT" -> { //待还款
                        type.text =
                            context.getString(R.string.fragment12) + TienSystemUtil.getTienDateToString(
                                it1.m2f1OnN.toString(),
                                "yyyy-MM-dd"
                            )
                        hint.text =
                            context.getString(R.string.fragment15)
                        money.text = context.getString(R.string.fragment14) + it1.aY06zTe
                        hint1.visibility = View.GONE
                        button2.visibility = View.VISIBLE
                        button2.text = context.getString(R.string.fragment25)
                        button1.visibility = View.VISIBLE
                        button1.text = context.getString(R.string.fragment23)
                    }
                }

                button1.setOnClickListener {
                    if (button1.text.toString() == context.getString(R.string.fragment25)) {//还款
                        val intent = Intent(context, TienRepaymentAccountActivity::class.java)
                        intent.putExtra(TienSystemUtil.ORDER_CODE, it1.MICYPp9)
                        context.startActivity(intent)
                    }
                }
                button2.setOnClickListener {
                    if (button2.text.toString() == context.getString(R.string.fragment21)) {//运行商
                        val intent = Intent(context, TienOperatorSelectActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        val intent = Intent(context, TienOrderDetailsActivity::class.java)
                        intent.putExtra(TienSystemUtil.ORDER_CODE, it1.MICYPp9)
                        context.startActivity(intent)
                    }
                }
                zalo.setOnClickListener {
                    TienZaloDialog.showZaloDialog(activity, it1.U4ssv5u)
                }
                phone.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        ("tel:" + it1.U4ssv5u).toUri()
                    )
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}