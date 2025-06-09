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
import com.tienmoi.vayonline.nhanh.model.data.TienBankListData
import com.tienmoi.vayonline.nhanh.model.utils.TienHttpUtil
import com.tienmoi.vayonline.nhanh.model.utils.TienSystemUtil

class TienBankAdapter(
    private val result: MutableList<TienBankListData>,
    private val onSelectedListener: (TienBankListData) -> Unit
) :
    RecyclerView.Adapter<TienBankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.tien_bank_adapter_item, parent, false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        result[position].let { it1 ->
            holder.itemView.apply {
                val logo = holder.itemView.findViewById<ImageView>(R.id.logo_id)
                val name = holder.itemView.findViewById<TextView>(R.id.tv_name)
                val name1 = holder.itemView.findViewById<TextView>(R.id.tv_name1)
                if (TienSystemUtil.isTienNumericRegex(it1.ljTTUfo)) {
                    Glide.with(context)
                        .load(TienHttpUtil.BASE_URL + "/api/app/hSe7zCj?id=" + it1.ljTTUfo)
                        .disallowHardwareConfig()
                        .into(logo)
                } else {
                    Glide.with(context)
                        .load(it1.ljTTUfo)
                        .disallowHardwareConfig()
                        .into(logo)
                }
                name.text = it1.g3jMqJO
                name1.text = it1.dqoVa3H
                setOnClickListener {
                    onSelectedListener.invoke(it1)
                }
            }
        }
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}