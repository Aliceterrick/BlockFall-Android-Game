package blockfall.pack.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import blockfall.pack.R

class GameSoundManager(context: Context) {

    private val maxStreams = 4

    private val soundPool: SoundPool
    private val soundMap = mutableMapOf<String, Int>()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(maxStreams)
            .setAudioAttributes(audioAttributes)
            .build()

        soundMap["combo"] = soundPool.load(context, R.raw.combo, 1)
        soundMap["clearedLine"] = soundPool.load(context, R.raw.effectclearedline, 1)
        soundMap["levelPassed"] = soundPool.load(context, R.raw.effectlevelpassed, 1)
        soundMap["gameOver"] = soundPool.load(context, R.raw.effectgameover, 1)
    }

    fun playEffect(name: String, volume: Float = 1f) {
        soundMap[name]?.let { id ->
            soundPool.play(id, volume, volume, 1, 0, 1f)
        }
    }

    fun release() {
        soundPool.release()
    }
}