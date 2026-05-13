package com.vinodabhishek.siridhanyahub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DirectBuyAdapter(private val products: List<FarmerProduct>, private val context: Context) :
    RecyclerView.Adapter<DirectBuyAdapter.BuyViewHolder>() {

    class BuyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val farmerName: TextView = itemView.findViewById(R.id.tv_farmer_name)
        val location: TextView = itemView.findViewById(R.id.tv_farmer_location)
        val organicBadge: TextView = itemView.findViewById(R.id.tv_organic_badge)
        val milletType: TextView = itemView.findViewById(R.id.tv_millet_type)
        val price: TextView = itemView.findViewById(R.id.tv_farmer_price)
        val quantity: TextView = itemView.findViewById(R.id.tv_farmer_quantity)
        val contactBtn: Button = itemView.findViewById(R.id.btn_contact_farmer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direct_buy, parent, false)
        return BuyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        val item = products[position]
        holder.farmerName.text = "👨‍🌾 ${item.farmerName}"
        holder.location.text = "📍 ${item.location}"
        holder.milletType.text = "🌾 ${item.milletType}"
        holder.price.text = "₹${item.price}/kg"
        holder.quantity.text = "📦 ${item.quantity} available"
        holder.organicBadge.visibility = if (item.organic) View.VISIBLE else View.GONE

        holder.contactBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${item.phone}")
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = products.size
}