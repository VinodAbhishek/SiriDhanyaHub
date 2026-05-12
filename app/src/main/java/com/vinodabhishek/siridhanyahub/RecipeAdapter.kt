package com.vinodabhishek.siridhanyahub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeList: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emoji: TextView = itemView.findViewById(R.id.tv_recipe_emoji)
        val name: TextView = itemView.findViewById(R.id.tv_recipe_name)
        val millet: TextView = itemView.findViewById(R.id.tv_recipe_millet)
        val time: TextView = itemView.findViewById(R.id.tv_recipe_time)
        val difficulty: TextView = itemView.findViewById(R.id.tv_recipe_difficulty)
        val calories: TextView = itemView.findViewById(R.id.tv_recipe_calories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = recipeList[position]
        holder.emoji.text = item.emoji
        holder.name.text = item.name
        holder.millet.text = "🌾 ${item.millet}"
        holder.time.text = "⏱ ${item.time}"
        holder.difficulty.text = "📊 ${item.difficulty}"
        holder.calories.text = "🔥 ${item.calories}"
    }

    override fun getItemCount() = recipeList.size
}