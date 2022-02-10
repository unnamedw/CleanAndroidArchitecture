package com.example.cleanandroidarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        setContentView(R.layout.activity_detail)

        setSupportActionBar(binding.toolbar)

    }
}

class ViewPagerAdapter(private val items: List<View>): PagerAdapter() {

    override fun getCount(): Int {
        return items.size
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }
}