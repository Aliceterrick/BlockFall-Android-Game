package blockfall.pack.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import blockfall.pack.logic.data.StatsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StatsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = StatsRepository(application)

    val stats = repository.statsFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0 to 0
        )

    fun onGameFinished(score: Int) {
        viewModelScope.launch {
            repository.updateAfterGame(score)
        }
    }
    fun resetStats() {
        viewModelScope.launch {
            repository.resetStats()
        }
    }
}