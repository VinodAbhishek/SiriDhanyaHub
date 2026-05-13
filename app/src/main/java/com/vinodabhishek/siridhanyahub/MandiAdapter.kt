package com.vinodabhishek.siridhanyahub

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MandiAdapter(private val priceList: List<MilletPrice>, private val context: Context) :
    RecyclerView.Adapter<MandiAdapter.MandiViewHolder>() {

    class MandiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_millet_name)
        val city: TextView = itemView.findViewById(R.id.tv_millet_city)
        val price: TextView = itemView.findViewById(R.id.tv_millet_price)
        val high: TextView = itemView.findViewById(R.id.tv_millet_high)
        val low: TextView = itemView.findViewById(R.id.tv_millet_low)
        val trend: TextView = itemView.findViewById(R.id.tv_millet_trend)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MandiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mandi_price, parent, false)
        return MandiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MandiViewHolder, position: Int) {
        val item = priceList[position]
        holder.name.text = item.milletName
        holder.city.text = "📍 ${item.city}"
        holder.price.text = "₹${item.currentPrice}/quintal"
        holder.high.text = "H: ₹${item.highPrice}"
        holder.low.text = "L: ₹${item.lowPrice}"
        holder.trend.text = item.trend
        holder.trend.setTextColor(
            if (item.trend == "↑") Color.parseColor("#2E7D32")
            else Color.parseColor("#C62828")
        )

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MilletDetailActivity::class.java).apply {
                putExtra("millet_name", item.milletName)
                putExtra("millet_city", item.city)
                putExtra("millet_price", item.currentPrice)
                putExtra("millet_high", item.highPrice)
                putExtra("millet_low", item.lowPrice)
                putExtra("millet_trend", item.trend)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = priceList.size
}