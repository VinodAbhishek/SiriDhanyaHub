package com.vinodabhishek.siridhanyahub

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DirectBuyAdapter(private val products: List<FarmerProduct>) :
    RecyclerView.Adapter<DirectBuyAdapter.DirectBuyViewHolder>() {

    class DirectBuyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emoji: TextView = itemView.findViewById(R.id.tv_product_emoji)
        val milletName: TextView = itemView.findViewById(R.id.tv_product_millet)
        val localName: TextView = itemView.findViewById(R.id.tv_product_local)
        val farmerName: TextView = itemView.findViewById(R.id.tv_farmer_name)
        val location: TextView = itemView.findViewById(R.id.tv_farmer_location)
        val price: TextView = itemView.findViewById(R.id.tv_product_price)
        val quantity: TextView = itemView.findViewById(R.id.tv_product_quantity)
        val quality: TextView = itemView.findViewById(R.id.tv_product_quality)
        val btnWhatsApp: Button = itemView.findViewById(R.id.btn_whatsapp)
        val btnCall: Button = itemView.findViewById(R.id.btn_call)
        val btnSms: Button = itemView.findViewById(R.id.btn_sms)
        val btnEmail: Button = itemView.findViewById(R.id.btn_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectBuyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direct_buy, parent, false)
        return DirectBuyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DirectBuyViewHolder, position: Int) {
        val item = products[position]

        holder.emoji.text = item.emoji
        holder.milletName.text = item.milletName
        holder.localName.text = item.localName
        holder.farmerName.text = "👨‍🌾 ${item.farmerName}"
        holder.location.text = "📍 ${item.location}"
        holder.price.text = "₹${item.pricePerKg}/kg"
        holder.quantity.text = "📦 ${item.quantityKg} kg available"
        holder.quality.text = "⭐ ${item.quality}"

        val context = holder.itemView.context
        val message = "Hello! I'm interested in buying ${item.milletName} (${item.localName}). " +
                "Price: ₹${item.pricePerKg}/kg. Quantity available: ${item.quantityKg}kg. " +
                "Please share more details. - Via SiriDhanyaHub App"

        // WhatsApp
        holder.btnWhatsApp.setOnClickListener {
            try {
                val url = "https://wa.me/91${item.phone}?text=${Uri.encode(message)}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            } catch (e: Exception) {
                val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://wa.me/91${item.phone}"))
                context.startActivity(intent)
            }
        }

        // Call
        holder.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+91${item.phone}"))
            context.startActivity(intent)
        }

        // SMS
        holder.btnSms.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:+91${item.phone}"))
            intent.putExtra("sms_body", message)
            context.startActivity(intent)
        }

        // Email
        holder.btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${item.email}"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry: ${item.milletName} - SiriDhanyaHub")
            intent.putExtra(Intent.EXTRA_TEXT, message)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = products.size
}