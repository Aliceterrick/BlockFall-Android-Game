import android.media.MediaPlayer
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager

import blockfall.pack.R

class MusicManager(private val context: Context) {

    private val mediaPlayer = MediaPlayer.create(context, R.raw.mainloop3)
    private val audioManager =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val focusRequest: AudioFocusRequest

    init {
        mediaPlayer.isLooping = true

        val focusChangeListener =
            AudioManager.OnAudioFocusChangeListener { focusChange ->
                when (focusChange) {
                    AudioManager.AUDIOFOCUS_LOSS -> {
                        stop()
                    }
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                        pause()
                    }
                    AudioManager.AUDIOFOCUS_GAIN -> {
                        play()
                    }
                }
            }

        focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setOnAudioFocusChangeListener(focusChangeListener)
            .build()
    }

    fun play() {
        val result = audioManager.requestAudioFocus(focusRequest)
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }
    }

    fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        audioManager.abandonAudioFocusRequest(focusRequest)
    }

    fun release() {
        mediaPlayer.release()
    }
}