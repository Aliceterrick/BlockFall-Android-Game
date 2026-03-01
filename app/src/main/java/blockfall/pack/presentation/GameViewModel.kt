package blockfall.pack.presentation

import androidx.lifecycle.ViewModel
import blockfall.pack.logic.GameEngine
import blockfall.pack.logic.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.isActive
import android.util.Log
import kotlinx.coroutines.delay

class GameViewModel : ViewModel() {
    private val engine = GameEngine()
    private var locState = MutableStateFlow(engine.tick())
    val state: StateFlow<GameState> = locState

    init {
        this.mainLoop()
    }

    private fun mainLoop() {
        viewModelScope.launch {
            while(isActive) {
                delay(engine.tack)
                locState.value = engine.tick()
            }
        }
    }

    fun onAction(action: GameAction) {
        if ((locState.value.pause && action != GameAction.Resume) || locState.value.gameOver)
            return
        when (action) {
            GameAction.MoveLeft -> locState.value = engine.moveLeft()
            GameAction.MoveRight -> locState.value = engine.moveRight()
            GameAction.MoveDown -> locState.value = engine.moveDown()
            GameAction.Rotate -> locState.value = engine.rotate()
            GameAction.Pause -> this.pauseHandler()
            GameAction.Resume -> this.resumeHandler()
            GameAction.ComboFinished -> engine.resetCombo()
            GameAction.Hold -> locState.value = engine.hold()
            GameAction.Drop -> locState.value = engine.drop()
        }
    }

    fun pauseHandler() {
        locState.value = engine.pause(true)
    }

    fun resumeHandler() {
        locState.value = engine.pause(false)
    }
}