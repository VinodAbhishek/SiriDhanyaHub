package com.vinodabhishek.siridhanyahub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HealthAdapter(private val list: List<HealthBenefit>) :
    RecyclerView.Adapter<HealthAdapter.HealthViewHolder>() {

    class HealthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emoji: TextView = itemView.findViewById(R.id.tv_health_emoji)
        val milletName: TextView = itemView.findViewById(R.id.tv_health_millet_name)
        val localName: TextView = itemView.findViewById(R.id.tv_health_local_name)
        val benefit1: TextView = itemView.findViewById(R.id.tv_benefit_1)
        val benefit2: TextView = itemView.findViewById(R.id.tv_benefit_2)
        val benefit3: TextView = itemView.findViewById(R.id.tv_benefit_3)
        val gi: TextView = itemView.findViewById(R.id.tv_glycemic_index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_health, parent, false)
        return HealthViewHolder(view)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val item = list[position]
        holder.emoji.text = item.emoji
        holder.milletName.text = item.milletName
        holder.localName.text = item.localName
        holder.benefit1.text = "✅ ${item.benefit1}"
        holder.benefit2.text = "✅ ${item.benefit2}"
        holder.benefit3.text = "✅ ${item.benefit3}"
        holder.gi.text = "Glycemic Index: ${item.glycemicIndex}"
    }

    override fun getItemCount() = list.size
}