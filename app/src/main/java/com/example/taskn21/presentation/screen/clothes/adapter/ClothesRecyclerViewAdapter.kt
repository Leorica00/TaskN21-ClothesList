package com.example.taskn21.presentation.screen.clothes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn21.R
import com.example.taskn21.databinding.ClothesItemRecyclerviewBinding
import com.example.taskn21.presentation.extension.loadImage
import com.example.taskn21.presentation.model.Clothes

class ClothesRecyclerViewAdapter :
    ListAdapter<Clothes, ClothesRecyclerViewAdapter.ClothesViewHolder>(ClothesDiffCallback) {

    var onClick: ((Clothes) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        return ClothesViewHolder(ClothesItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        holder.bind()
    }

    inner class ClothesViewHolder(private val binding: ClothesItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val clothes = currentList[adapterPosition]
            with(binding) {
                imageViewClothes.loadImage(clothes.cover)
                tvClothesTitle.text = clothes.title
                tvClothesPrice.text = clothes.price

                with(imageButtonFavourite) {
                    if (clothes.favorite) {
                        setImageResource(R.drawable.ic_heart)
                    } else {
                        setImageResource(R.drawable.ic_heart_empty)
                    }

                    setOnClickListener {
                        onClick?.invoke(clothes)
                    }
                }
            }
        }
    }

    companion object {
        private val ClothesDiffCallback = object : DiffUtil.ItemCallback<Clothes>() {

            override fun areItemsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
                return oldItem == newItem
            }
        }
    }
}