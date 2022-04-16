package com.jefriap.submission2made.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.jefriap.submission2made.databinding.ActivitySplashScreenBinding
import com.jefriap.submission2made.ui.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var imageSplash: ImageView
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageSplash = binding.icon

        splashScreen()
    }

    private fun splashScreen(){
        imageSplash.alpha = 0f
        binding.netflim.alpha = 0f

        imageSplash.animate().setDuration(5000).alpha(1f)
        binding.netflim.animate().setDuration(5000).alpha(1f).withEndAction {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}