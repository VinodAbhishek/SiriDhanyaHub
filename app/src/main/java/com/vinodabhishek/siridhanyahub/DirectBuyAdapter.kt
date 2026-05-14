package com.vinodabhishek.siridhanyahub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
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
        val btnCall: LinearLayout = itemView.findViewById(R.id.btn_call)
        val btnSms: LinearLayout = itemView.findViewById(R.id.btn_sms)
        val btnWhatsapp: LinearLayout = itemView.findViewById(R.id.btn_whatsapp)
        val btnEmail: LinearLayout = itemView.findViewById(R.id.btn_email)
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

        holder.btnCall.setOnClickListener { dialPhone(item.phone) }
        holder.btnSms.setOnClickListener { sendSms(item) }
        holder.btnWhatsapp.setOnClickListener { openWhatsApp(item) }
        holder.btnEmail.setOnClickListener { sendEmail(item) }
    }

    private fun dialPhone(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        context.startActivity(intent)
    }

    private fun sendSms(item: FarmerProduct) {
        val message = "Hello ${item.farmerName}, I am interested in buying your ${item.milletType} at ₹${item.price}/kg. Please let me know the availability."
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:${item.phone}")
            putExtra("sms_body", message)
        }
        context.startActivity(intent)
    }

    private fun openWhatsApp(item: FarmerProduct) {
        val message = "Hello ${item.farmerName}! 👋\n\nI found your listing on SiriDhanyaHub and I'm interested in buying:\n🌾 ${item.milletType}\n💰 ₹${item.price}/kg\n📍 ${item.location}\n\nIs it still available?"
        val phone = "91${item.phone}"
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://wa.me/$phone?text=${Uri.encode(message)}")
                setPackage("com.whatsapp")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://wa.me/$phone?text=${Uri.encode(message)}")
            }
            context.startActivity(intent)
        }
    }

    private fun sendEmail(item: FarmerProduct) {
        val subject = "Interested in ${item.milletType} - SiriDhanyaHub"
        val body = "Dear ${item.farmerName},\n\nI came across your listing on SiriDhanyaHub and I am interested in purchasing your ${item.milletType} at ₹${item.price}/kg.\n\nKindly let me know the availability and how we can proceed.\n\nThank you!"
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        context.startActivity(Intent.createChooser(intent, "Send Email"))
    }

    override fun getItemCount() = products.size
}