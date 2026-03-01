package blockfall.pack.logic.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "game_stats")

class StatsRepository(private val context: Context) {

    private val GAMES_PLAYED = intPreferencesKey("games_played")
    private val BEST_SCORE = intPreferencesKey("best_score")

    val statsFlow: Flow<Pair<Int, Int>> = context.dataStore.data
        .map { prefs ->
            val games = prefs[GAMES_PLAYED] ?: 0
            val best = prefs[BEST_SCORE] ?: 0
            games to best
        }

    suspend fun updateAfterGame(score: Int) {
        context.dataStore.edit { prefs ->
            val currentGames = prefs[GAMES_PLAYED] ?: 0
            val currentBest = prefs[BEST_SCORE] ?: 0

            prefs[GAMES_PLAYED] = currentGames + 1
            if (score > currentBest) {
                prefs[BEST_SCORE] = score
            }
        }
    }
    suspend fun resetStats() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}