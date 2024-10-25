package com.example.gezerhome

import android.content.Context
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

class SimplePlayer {

    private var player: SimpleExoPlayer? = null

    fun initializePlayer(context: Context) {
        if (player == null) {
            val trackSelector = DefaultTrackSelector(context)
            val loadControl = DefaultLoadControl()
            val renderersFactory = DefaultRenderersFactory(context)

            player = SimpleExoPlayer.Builder(context, renderersFactory)
                .setTrackSelector(trackSelector)
                .setLoadControl(loadControl)
                .build()
        }
    }

    fun releasePlayer() {
        player?.release()
        player = null
    }

    fun getPlayer(): SimpleExoPlayer? {
        return player
    }
}
