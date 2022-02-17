package com.example.cleanandroidarchitecture.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.databinding.ActivityMainBinding
import com.example.cleanandroidarchitecture.ui.adapter.PostAdapter
import com.example.cleanandroidarchitecture.ui.custom.CustomDecoration
import com.example.cleanandroidarchitecture.ui.viewmodel.MainViewModel
import com.example.cleanandroidarchitecture.util.NetworkConnectionManager
import com.example.cleanandroidarchitecture.util.PreferencesHelper
import com.example.cleanandroidarchitecture.util.PreferencesHelper.setAppInitFlag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val vm: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    private val postAdapter = PostAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.listPost.apply {
            adapter = postAdapter
            addItemDecoration(CustomDecoration(this@MainActivity))
        }
        binding.vm = this.vm
        vm.posts.observe(this, {
            Log.d(AppConstants.TAG_APPLICATION, it.toString())
            postAdapter.submitDataSet(it.toTypedArray())
        })

        vm.fetchData()

        val networkManager = NetworkConnectionManager(applicationContext)
        Log.d(AppConstants.TAG_NETWORK_TEST, "Is internet available? ${networkManager.isInternetAvailable()}")

        PreferencesHelper.getPreferences(applicationContext).setAppInitFlag(value = true)

        startToolbarAnimation()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startToolbarAnimation() {
        val startColor = ContextCompat.getColor(this, R.color.design_default_color_primary)
        val endColor = ContextCompat.getColor(this, R.color.design_default_color_secondary)
        val toolBarAnimator = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        toolBarAnimator.addUpdateListener {
//            val position = it.animatedFraction
//            val blendedColor = getBlendedColor(startColor, endColor, position)
//            val background = ColorDrawable(blendedColor)
            (it.animatedValue as Int).also { color ->
                binding.toolbar.setBackgroundColor(color)
                this.window.statusBarColor = color
            }
        }

        val originalBackground = ContextCompat.getColor(this, R.color.design_default_color_primary)
        binding.toolbar.setBackgroundColor(originalBackground)
        this.window.statusBarColor = originalBackground
        toolBarAnimator.duration = 3000
        toolBarAnimator.startDelay = 1000
        toolBarAnimator.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBlendedColor(start: Int, end: Int, ratio: Float): Int {
        val inverseRatio = 1f - ratio
        val r = Color.red(end) * ratio + Color.red(start) * inverseRatio
        val g = Color.green(end) * ratio + Color.green(start) * inverseRatio
        val b = Color.blue(end) * ratio + Color.blue(start) * inverseRatio

        return Color.rgb(r, g, b)
    }

}