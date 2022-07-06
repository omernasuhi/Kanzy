package com.kanzy.music.features.playVideo


import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.kanzy.music.R

const val YOUTUBE_VIDEO_ID = "Evfe8GEn33w"
const val YOUTUBE_PLAYLIST = "UCU3jy5C8MB-JvSw_86SFV2w"

class PlayVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "YoutubeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = layoutInflater.inflate(R.layout.fragment_play_video, null) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            400, 500
        )
        layout.addView(playerView)

        playerView.initialize("AIzaSyD2cNCQAN5LhAlsT9XWC9xypKI-dUHgxVc", this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialized Youtube Player successfully", Toast.LENGTH_SHORT).show()

        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored) {
            youTubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
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
            val errorMessage =
                "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
            Toast.makeText(this@PlayVideoActivity, "Good, video is playing ok", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onStopped() {
            Toast.makeText(this@PlayVideoActivity, "Video has stopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@PlayVideoActivity, "Video has paused", Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(
                this@PlayVideoActivity,
                "Click Ad now, make the video creator rich!",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            Toast.makeText(this@PlayVideoActivity, "Video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            Toast.makeText(
                this@PlayVideoActivity,
                "Congratulations! You've completed another video.",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }
}