package com.vinodabhishek.siridhanyahub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MandiAdapter(private val priceList: List<MilletPrice>) :
    RecyclerView.Adapter<MandiAdapter.MandiViewHolder>() {

    class MandiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val milletName: TextView = itemView.findViewById(R.id.tv_millet_name)
        val city: TextView = itemView.findViewById(R.id.tv_city)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val trend: TextView = itemView.findViewById(R.id.tv_trend)
        val highLow: TextView = itemView.findViewById(R.id.tv_high_low)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MandiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mandi_price, parent, false)
        return MandiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MandiViewHolder, position: Int) {
        val item = priceList[position]
        holder.milletName.text = item.milletName
        holder.city.text = "📍 ${item.city}"
        holder.price.text = "₹${item.currentPrice}/quintal"
        holder.trend.text = item.trend
        holder.trend.setTextColor(
            if (item.trend == "↑") 0xFF2E7D32.toInt() else 0xFFB71C1C.toInt()
        )
        holder.highLow.text = "H: ₹${item.highPrice}  L: ₹${item.lowPrice}"
    }

    override fun getItemCount() = priceList.size
}