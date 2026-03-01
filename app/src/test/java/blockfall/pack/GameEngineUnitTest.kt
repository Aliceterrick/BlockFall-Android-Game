package blockfall.pack

import blockfall.pack.logic.GameEngine
import org.junit.Assert.*
import org.junit.Test

class GameEngineTest {

    @Test
    fun score_increases_when_lines_cleared() {
        val engine = GameEngine()

        engine.updateScore(2)

        val state = engine.buildGameState()

        assertEquals(200, state.score)
    }

    @Test
    fun combo_gives_bonus_score() {
        val engine = GameEngine()

        engine.updateScore(4)

        val state = engine.buildGameState()

        assertTrue(state.combo)
        assertEquals(1200, state.score) // 300 * 4
    }
    @Test
    fun pause_sets_game_to_pause_state() {
        val engine = GameEngine()

        val paused = engine.pause(true)

        assertTrue(paused.pause)
    }
    @Test
    fun level_increases_after_10_lines() {
        val engine = GameEngine()

        repeat(10) {
            engine.updateScore(1)
        }

        val state = engine.buildGameState()

        assertTrue(state.level == 1)
    }
    @Test
    fun game_over_when_spawn_fails() {
        val engine = GameEngine()
        repeat(10) {
            engine.drop()
            engine.spawnNewBlock()
        }

        val state = engine.spawnNewBlock()

        assertTrue(state.gameOver)
    }

    @Test
    fun hold_changes_current_block() {
        val engine = GameEngine()

        val before = engine.buildGameState().holded

        val state = engine.hold()

        assertNotEquals(before.type, state.holded.type)
    }
}

