package com.tienmoi.vayonline.nhanh.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tienmoi.vayonline.nhanh.R
import com.tienmoi.vayonline.nhanh.model.data.TienContent
import com.tienmoi.vayonline.nhanh.model.utils.TienHttpUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil

class TienRepaymentAccomplishAdapter(
    private val result: MutableList<TienContent>
) :
    RecyclerView.Adapter<TienRepaymentAccomplishAdapter.ViewHolder>() {
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
                val hint1 = holder.itemView.findViewById<TextView>(R.id.tv_hint1)
                if (TienSystemUtil.isTienNumericRegex(it1.Cr73o8v)) {
                    Glide.with(context)
                        .load(TienHttpUtil.BASE_URL + "/Xqm5ED7/I4Tnli4?hY2WwpV=" + it1.Cr73o8v)
                        .disallowHardwareConfig()
                        .into(icon)
                } else {
                    Glide.with(context)
                        .load(it1.Cr73o8v)
                        .disallowHardwareConfig()
                        .into(icon)
                }
                name.text = it1.EF02RZA
                type.text =
                    context.getString(R.string.fragment16) + TienSystemUtil.getTienDateToString(
                        it1.UhIB77e.toString(),
                        "yyyy-MM-dd"
                    )
                hint.text =
                    context.getString(R.string.fragment17)
                money.text = context.getString(R.string.fragment9) + it1.kHy37Km
                hint1.visibility = View.GONE
                zalo.visibility = View.GONE
                phone.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}