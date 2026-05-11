package com.palestine.roots.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.palestine.roots.R
import com.palestine.roots.databinding.ActivityHomeBinding
import com.palestine.roots.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var siteAdapter: SiteAdapter
    private var currentLang = "ar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        setupProvinceChips()
        setupSearch()
        setupToolbarActions()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        siteAdapter = SiteAdapter(
            onSiteClick = { site ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_SITE_ID, site.id)
                }
                startActivity(intent)
            },
            onFavoriteClick = { site ->
                viewModel.toggleFavorite(site.id, !site.isFavorite)
            }
        )
        binding.rvSites.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            adapter = siteAdapter
        }
    }

    private fun setupProvinceChips() {
        val chipGroup = binding.chipGroupProvinces
        chipGroup.removeAllViews()

        val allChip = Chip(this).apply {
            text = "الكل"
            isClickable = true
            isCheckable = true
            isChecked = true
            setOnClickListener {
                viewModel.loadAllSites()
                clearChipSelection(chipGroup, this)
            }
        }
        chipGroup.addView(allChip)

        viewModel.provinces.forEach { province ->
            val chip = Chip(this).apply {
                text = province
                isClickable = true
                isCheckable = true
                setOnClickListener {
                    viewModel.filterByCity(province)
                    clearChipSelection(chipGroup, this)
                }
            }
            chipGroup.addView(chip)
        }
    }

    private fun clearChipSelection(chipGroup: com.google.android.material.chip.ChipGroup, selectedChip: Chip) {
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            chip.isChecked = (chip == selectedChip)
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener { text ->
            val query = text?.toString()?.trim() ?: ""
            viewModel.onSearchQueryChanged(query)
        }
    }

    private fun setupToolbarActions() {
        binding.ivMap.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        binding.ivFavorites.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        binding.ivLanguage.setOnClickListener {
            currentLang = if (currentLang == "ar") "en" else "ar"
            viewModel.setLanguage(currentLang)
        }

        binding.ivDarkMode.setOnClickListener {
            lifecycleScope.launch {
                val currentDarkMode = viewModel.isDarkMode.first()
                val newMode = !currentDarkMode
                viewModel.toggleDarkMode(newMode)
                AppCompatDelegate.setDefaultNightMode(
                    if (newMode) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is HomeViewModel.UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvSites.visibility = View.GONE
                    }
                    is HomeViewModel.UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvSites.visibility = View.VISIBLE
                        siteAdapter.submitList(state.sites)
                    }
                    is HomeViewModel.UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvSites.visibility = View.GONE
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.language.collectLatest { lang ->
                currentLang = lang
            }
        }

        lifecycleScope.launch {
            viewModel.isDarkMode.collectLatest { isDark ->
                // Dark mode state handled by AppCompatDelegate
            }
        }
    }
}
