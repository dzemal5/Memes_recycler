package com.crnkic.memes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.databinding.FragmentMemeListItemBinding

class MemesListAdapter() : RecyclerView.Adapter<MemesListAdapter.MemeViewHolder>() {

    private var memes = listOf<Memes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        return MemeViewHolder(
                FragmentMemeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memes[position]
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(meme) } //it refers to onItemClickListener
            }
        }
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return memes.size
    }

    inner class MemeViewHolder(private val itemBinding: FragmentMemeListItemBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            itemView.apply {
                itemBinding.memeName.text = memes[position].name
                Glide.with(itemBinding.root.context).load(memes[position].url).into(itemBinding.memeImage)
            }
        }
    }

    private var onItemClickListener: ((Memes) -> Unit)? = null

    fun setOnItemClickListener(listener: (Memes) -> Unit) {
        onItemClickListener = listener
    }
}