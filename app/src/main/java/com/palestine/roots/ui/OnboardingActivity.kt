package com.palestine.roots.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.palestine.roots.databinding.ActivityOnboardingBinding
import com.palestine.roots.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityOnboardingBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var pagerAdapter: OnboardingPagerAdapter

    private val onboardingItems = listOf(
        OnboardingPagerAdapter.OnboardingItem(
            icon = R.drawable.ic_history,
            title = "تاريخ عريق",
            description = "اكتشف تاريخ فلسطين العريق عبر المواقع الأثرية والمعالم التاريخية المنتشرة في جميع أنحاء البلاد."
        ),
        OnboardingPagerAdapter.OnboardingItem(
            icon = R.drawable.ic_map,
            title = "خريطة تفاعلية",
            description = "تصفح المواقع على الخريطة التفاعلية واعثر على أقرب المعالم التاريخية إليك بسهولة."
        ),
        OnboardingPagerAdapter.OnboardingItem(
            icon = R.drawable.ic_favorite,
            title = "المفضلة",
            description = "احفظ المواقع المفضلة لديك وعد إليها في أي وقت لتستكشفها وتشاركها مع الآخرين."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        pagerAdapter = OnboardingPagerAdapter(onboardingItems)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)
                updateButton(position)
            }
        })

        setupDots()
        updateDots(0)
        updateButton(0)

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < onboardingItems.size - 1) {
                binding.viewPager.currentItem = currentItem + 1
            } else {
                viewModel.completeOnboarding()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupDots() {
        val dotsLayout = binding.dotsLayout
        dotsLayout.removeAllViews()
        for (i in onboardingItems.indices) {
            val dot = android.view.View(this).apply {
                val size = if (i == 0) 24 else 12
                layoutParams = android.widget.LinearLayout.LayoutParams(size, 12).apply {
                    marginStart = 4
                    marginEnd = 4
                }
                setBackgroundResource(R.drawable.dot_indicator)
            }
            dotsLayout.addView(dot)
        }
    }

    private fun updateDots(position: Int) {
        val dotsLayout = binding.dotsLayout
        for (i in 0 until dotsLayout.childCount) {
            val dot = dotsLayout.getChildAt(i)
            val size = if (i == position) 24 else 12
            dot.layoutParams = android.widget.LinearLayout.LayoutParams(size, 12).apply {
                marginStart = 4
                marginEnd = 4
            }
            dot.isSelected = (i == position)
        }
    }

    private fun updateButton(position: Int) {
        binding.btnNext.text = if (position == onboardingItems.size - 1) {
            "ابدأ الآن"
        } else {
            "التالي"
        }
    }
}
