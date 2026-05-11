package com.palestine.roots.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.palestine.roots.R
import com.palestine.roots.databinding.ActivityDetailBinding
import com.palestine.roots.domain.model.Site
import com.palestine.roots.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()

    private var currentSite: Site? = null

    companion object {
        const val EXTRA_SITE_ID = "extra_site_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val siteId = intent.getStringExtra(EXTRA_SITE_ID)
        if (siteId == null) {
            finish()
            return
        }

        setupToolbar()
        loadSite(siteId)
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.ivShare.setOnClickListener {
            shareSite()
        }
    }

    private fun loadSite(siteId: String) {
        lifecycleScope.launch {
            try {
                val site = viewModel.getSiteById(siteId)
                if (site != null) {
                    currentSite = site
                    displaySite(site)
                } else {
                    Toast.makeText(this@DetailActivity, "الموقع غير موجود", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "حدث خطأ", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun displaySite(site: Site) {
        binding.tvSiteName.text = site.name
        binding.tvSiteCity.text = site.city
        binding.tvDescription.text = site.description
        binding.tvHistory.text = site.history

        if (site.foundationYear.isNullOrEmpty()) {
            binding.chipFoundationYear.visibility = View.GONE
        } else {
            binding.chipFoundationYear.visibility = View.VISIBLE
            binding.chipFoundationYear.text = site.foundationYear
        }

        Glide.with(this)
            .load(site.imageUrl)
            .placeholder(R.drawable.placeholder_site)
            .error(R.drawable.placeholder_site)
            .centerCrop()
            .into(binding.ivSiteImage)

        updateFavoriteButton(site.isFavorite)

        binding.ivFavorite.setOnClickListener {
            val newFavorite = !site.isFavorite
            viewModel.toggleFavorite(site.id, newFavorite)
            currentSite = site.copy(isFavorite = newFavorite)
            updateFavoriteButton(newFavorite)
        }

        binding.btnOpenMap.setOnClickListener {
            openInGoogleMaps(site)
        }
    }

    private fun updateFavoriteButton(isFavorite: Boolean) {
        binding.ivFavorite.setImageResource(
            if (isFavorite) R.drawable.ic_favorite_filled
            else R.drawable.ic_favorite_border
        )
    }

    private fun openInGoogleMaps(site: Site) {
        val uri = "geo:${site.latitude},${site.longitude}?q=${site.latitude},${site.longitude}(${site.name})"
        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val webUri = "https://www.google.com/maps/search/?api=1&query=${site.latitude},${site.longitude}"
            startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse(webUri)))
        }
    }

    private fun shareSite() {
        val site = currentSite ?: return
        val shareText = "${site.name}\n${site.city}\n${site.description}"
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(shareIntent, "مشاركة الموقع"))
    }
}
