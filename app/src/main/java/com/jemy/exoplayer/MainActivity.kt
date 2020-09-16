package com.jemy.exoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val player by lazy { SimpleExoPlayer.Builder(this).build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializePlayer()
    }

    private fun initializePlayer() {
        val dataSourceFactory: DataSource.Factory =
                DefaultDataSourceFactory(this, "exoplayer")
        val audioUri = RawResourceDataSource.buildRawResourceUri(R.raw.audio)
        val audioItem = MediaItem.fromUri(audioUri)
        val audioSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(audioItem)
        player.playWhenReady = true
        player.addMediaSource(audioSource)
        exoPlayer.player = player
        player.prepare()
        player.play()
    }

    override fun onRestart() {
        super.onRestart()
        player.prepare()
        player.play()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        player.stop()
        player.release()
    }
}