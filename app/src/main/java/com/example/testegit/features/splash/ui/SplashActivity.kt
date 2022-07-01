package com.example.testegit.features.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.teste.commons.R
import com.example.testegit.core.ui.MainActivity
import com.example.testegit.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.contentMotionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}
            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) { openMainActivity() }
        })
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java)).apply {
            overridePendingTransition(R.anim.activity_slide_from_bottom, R.anim.activity_stay)
            finish()
        }
    }
}