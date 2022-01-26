package com.example.noticiascrypto.ui.Fragments.MainNews

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.databinding.NewsItemBinding

class NewsAdapter(

    private val context: Context,
    private val itemClickListener: OnNewsClickListener

): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var newsList = listOf<Data>()

    fun setNewsList(newsList: List<Data>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    interface OnNewsClickListener {
        fun onNewsClick(data: Data, position: Int)
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = NewsViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
             } ?:return@setOnClickListener

            itemClickListener.onNewsClick(newsList[position],position)
        }
        return holder

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is NewsViewHolder -> holder.bind(newsList[position])
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    private inner class NewsViewHolder(
            val binding: NewsItemBinding

    ): BaseViewHolder<Data>(binding.root){
        override fun bind(item: Data) = with(binding) {

            Glide.with(context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(ivArticleImage)
            tvTitle.text = item.title
            tvDescription.text = item.content
        }

    }

}