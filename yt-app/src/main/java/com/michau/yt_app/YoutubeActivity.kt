package com.michau.yt_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_youtube.*

const val YT_VIDEO_ID = "DT1EUadSD20"
const val YT_PLAYLIST_ID = "PLPf69IGxwfBGwcs6SaaR4Z8N5fwoc_RRD"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
///        setContentView(R.layout.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 180)
//        button1.text = "Button added"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_LEY), this)
    }

    override fun onInitializationSuccess(
            provider: YouTubePlayer.Provider?,
            youTubePlayer: YouTubePlayer?,
            wasRestored: Boolean
    ) {
        Log.d(TAG, "Init success!! provider is: {${provider?.javaClass}")
        Log.d(TAG, "Init success!! player is: {${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Youtube initialize success", Toast.LENGTH_LONG).show()
        if (!wasRestored) {             //plays from start not on resume
            youTubePlayer?.loadVideo(YT_VIDEO_ID)
        } else {
            youTubePlayer?.play()       //resumes clip after device was rotated
        }
    }

    override fun onInitializationFailure(
            provider: YouTubePlayer.Provider?,
            youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0
        if (youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMsg = "Error initializing player ($youTubeInitializationResult)"
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    private val youtubePlayerEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "playing now", Toast.LENGTH_SHORT)
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "paused now", Toast.LENGTH_SHORT)
        }

        override fun onPaused() {
        }
    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "hate commercials", Toast.LENGTH_SHORT)
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "you made it", Toast.LENGTH_SHORT)
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }
}
