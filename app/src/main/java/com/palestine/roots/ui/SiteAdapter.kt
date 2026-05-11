package com.palestine.roots.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.HapticFeedbackConstants
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.palestine.roots.R
import com.palestine.roots.domain.model.Site

class SiteAdapter(
    private val onSiteClick: (Site) -> Unit,
    private val onFavoriteClick: (Site) -> Unit
) : ListAdapter<Site, SiteAdapter.SiteViewHolder>(SiteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_site, parent, false)
        return SiteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val site = getItem(position)
        holder.bind(site)
    }

    inner class SiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivSiteImage: ImageView = itemView.findViewById(R.id.ivSiteImage)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val ivFavorite: ImageView = itemView.findViewById(R.id.ivFavorite)
        private val tvSiteName: TextView = itemView.findViewById(R.id.tvSiteName)
        private val tvSiteCity: TextView = itemView.findViewById(R.id.tvSiteCity)

        fun bind(site: Site) {
            tvSiteName.text = site.name
            tvSiteCity.text = site.city
            tvCategory.text = site.category

            Glide.with(itemView.context)
                .load(site.imageUrl)
                .placeholder(R.drawable.placeholder_site)
                .error(R.drawable.placeholder_site)
                .centerCrop()
                .into(ivSiteImage)

            ivFavorite.setImageResource(
                if (site.isFavorite) R.drawable.ic_favorite_filled
                else R.drawable.ic_favorite_border
            )

            itemView.setOnClickListener {
                onSiteClick(site)
            }

            ivFavorite.setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                onFavoriteClick(site)
            }

            itemView.setOnLongClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                onFavoriteClick(site)
                true
            }
        }
    }

    class SiteDiffCallback : DiffUtil.ItemCallback<Site>() {
        override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean {
            return oldItem == newItem
        }
    }
}
