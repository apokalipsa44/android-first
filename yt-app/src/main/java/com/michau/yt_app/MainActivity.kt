package com.michau.yt_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnYouTube.setOnClickListener(this)
        btnPlayStandalone.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent = when (v?.id) {
            R.id.btnYouTube -> Intent(this, YoutubeActivity::class.java)
            R.id.btnPlayStandalone -> Intent(this, StandaloneActivity::class.java)
            else -> {
                throw IllegalArgumentException("Undef button  clicked")
            }
        }
        startActivity(intent)
    }

}
