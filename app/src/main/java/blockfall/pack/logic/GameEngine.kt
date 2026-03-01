package blockfall.pack.logic

import blockfall.pack.logic.model.Board
import blockfall.pack.logic.model.Block
import blockfall.pack.logic.model.blocks
import blockfall.pack.logic.model.BlockType

data class GameState (
    val grid: Array<IntArray>,
    val nextBlock: Block,
    val holded: Block,
    val score: Int,
    val level: Int,
    val levelPassed: Boolean,
    val clearedLine: Boolean,
    val combo: Boolean,
    val gameOver: Boolean,
    val pause: Boolean
)

class GameEngine(private val board: Board = Board()) {
    private var currentBlock: Block
    private var nextBlock: Block
    private var holded: Block
    private var score: Int = 0
    private var level: Int = 0
    private var clearedLines = 0
    private var gameOver = false
    private var pause = false
    private var combo = false
    private var levelPassed = false
    private var clearedLine = false
    var tack: Long = 750
    var reached = false

    init {
        currentBlock = blocks[(1..7).random()].copy()
        nextBlock = blocks[(1..7).random()].copy()
        holded = blocks[0].copy()
        board.spawnBlock(currentBlock)
    }
    fun spawnNewBlock(): GameState {
        currentBlock = nextBlock
        nextBlock = blocks[(1..7).random()].copy()
        if (!board.spawnBlock(currentBlock)) {
            gameOver = true
            return this.buildGameState()
        }
        return this.buildGameState()
    }

    fun pause(pause: Boolean): GameState {
        this.pause = pause
        return buildGameState()
    }

    fun updateScore(nbrCleared: Int) {
        combo = nbrCleared > 3
        if(nbrCleared == 0)
            return
        if (level == 0 && !combo)
            score += 100 * nbrCleared
        else if (level == 0)
            score += 300 * nbrCleared
        else if (combo)
            score += 300*level * nbrCleared
        else
            score += 100*level * nbrCleared
        clearedLines += nbrCleared
        if (level > 0 && clearedLines / 10 > level) {
            tack -= level * 10
            levelPassed = true
        } else
            levelPassed = false
        level = clearedLines / 10
    }
    fun tick(): GameState {
        if (pause)
            return buildGameState()
        moveDown()
        if(board.isDownSide()) {
            if (reached){
                board.lockBlock()
                val nbrCleared = board.clear()
                clearedLine = nbrCleared > 0
                this.updateScore(nbrCleared)
                reached = false
                return this.spawnNewBlock()
            } else {
                reached = true
            }

        }
        return this.buildGameState()
    }

    fun handleMoveAndDisplay(): GameState {
        board.drawBlock(currentBlock)
        return this.buildGameState()
    }
    fun moveLeft(): GameState {
        currentBlock.x--
        if(!board.isValidMove(currentBlock)) {
            currentBlock.x++
            return buildGameState()
        }
        return this.handleMoveAndDisplay()
    }
    fun moveRight(): GameState {
        currentBlock.x++
        if(!board.isValidMove(currentBlock)) {
            currentBlock.x--
            return buildGameState()
        }
        return this.handleMoveAndDisplay()
    }
    fun moveDown(): GameState {
        currentBlock.y++
        if(!board.isValidMove(currentBlock)) {
            currentBlock.y--
            return buildGameState()
        }
        return this.handleMoveAndDisplay()
    }
    fun rotate(): GameState {
        currentBlock.currentShape = (currentBlock.currentShape + 1).mod(4)
        if (board.isValidMove(currentBlock)) {
            this.handleMoveAndDisplay()
            return this.buildGameState()
        }
        currentBlock.currentShape = (currentBlock.currentShape - 1).mod(4)
        return this.buildGameState()
    }

    fun hold(): GameState {
        if (holded.type != BlockType.V) {
            var tmp = holded.copy()
            holded = currentBlock.copy()
            currentBlock = tmp.copy()
            currentBlock.y = 0
            currentBlock.x = 2
            if(!board.isValidMove(currentBlock)) {
                gameOver = true
                return buildGameState()
            }
            board.drawBlock(currentBlock)
            return buildGameState()
        }
        holded = currentBlock.copy()
        return this.spawnNewBlock()
    }

    fun buildGameState(): GameState {
        return GameState(
            grid = board.grid.map { it.clone() }.toTypedArray(),
            nextBlock = nextBlock.copy(),
            holded = holded.copy(),
            score = score,
            level = level,
            combo = combo,
            gameOver = gameOver,
            pause = pause,
            levelPassed = levelPassed,
            clearedLine = clearedLine
        )
    }
    fun resetCombo() {
        combo = false
    }

    fun drop(): GameState {
        currentBlock.y++

        while(currentBlock.y < 20 && board.isValidMove(currentBlock))
            currentBlock.y++
        if (currentBlock.y == 20 || !board.isValidMove(currentBlock))
            currentBlock.y--
        board.drawBlock(currentBlock)
        board.lockBlock()
        val nbrCleared = board.clear()
        clearedLine = nbrCleared > 0
        this.updateScore(nbrCleared)
        return this.spawnNewBlock()
    }
}
