package com.newyork.times.presentation.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newyork.times.databinding.ItemNewsListBinding
import com.newyork.times.domain.models.NewsEntity

class NewsListAdapter :
    ListAdapter<NewsEntity, NewsListAdapter.NewsListViewHolder>(DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<NewsEntity>() {
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity) =
            oldItem == newItem
    }

    private var newsEntityList = mutableListOf<NewsEntity>()
    private var onItemClickListener: ((NewsEntity) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListViewHolder {
        return NewsListViewHolder(
            ItemNewsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bind(newsEntityList[position], onItemClickListener)
    }

    override fun getItemCount() = newsEntityList.size

    fun updateList(list: List<NewsEntity>) {
        newsEntityList.clear()
        newsEntityList.addAll(list)
        submitList(list)
    }

    fun setOnItemClickListener(listener: (NewsEntity) -> Unit) {
        onItemClickListener = listener
    }

    class NewsListViewHolder(private val binding: ItemNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsEntity: NewsEntity, onItemClickListener: ((NewsEntity) -> Unit)?) {
            binding.newsEntity = newsEntity
            binding.executePendingBindings()

            Glide.with(binding.root.context)
                .load(newsEntity.imageUrl)
                .into(binding.itemNewsImage)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(newsEntity)
            }
        }
    }


}
