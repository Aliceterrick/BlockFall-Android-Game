package blockfall.pack.logic.model

import android.util.Log
class Board {
    val raws: Int = 20
    val cols: Int = 10

    var grid: Array<IntArray> = Array(raws) { IntArray(cols) { BlockType.V.ordinal } }

    fun clearLine(idx: Int) {
        for(y in idx downTo 1) {
           for(x in 0 until 10)
               grid[y][x] = grid[y - 1][x]
        }
        for(x in 0 until 10)
            grid[0][x] = 0
    }
    fun clear(): Int {
        var cleared = 0
        var y = 19
        while (y >= 0) {
            val full = (0 until 10).all { x -> grid[y][x] != 0 }
            if (full) {
                clearLine(y)
                cleared++
            } else {
                y--
            }
        }
        return cleared
    }
    fun isValidMove(block: Block): Boolean {
        for(y in 0 until 4) {
            for(x in 0 until 4) {
                if (block.shapes[block.currentShape][y][x] == 0)
                    continue
                if (block.y + y !in 0 until 20 || block.x +x !in 0 until 10) {
                    return false
                }
                if (block.shapes[block.currentShape][y][x] != 0 && grid[block.y + y][block.x + x] in 1..7) {
                    return false
                }
            }
        }
        return true
    }
    fun isDownSide(): Boolean {
        for(y in 19 downTo  1) {
            for(x in 0 until 10) {
                if(y == 19 && grid[y][x] > 10) {
                    return true
                }
                if(grid[y][x] == 0 || grid[y][x] > 10)
                    continue
                if(grid[y -  1][x] > 10) {
                    return true
                }
            }
        }
        return false
    }
    private fun erasePreviousPos() {
        for(y in 0 until 20) {
            for(x in 0 until 10)
                if (grid[y][x] > 10)
                    grid[y][x] = 0
        }
    }
    fun drawBlock(block: Block) {
        this.erasePreviousPos()
        for(y in 0 until 4) {
            for(x in 0 until 4) {
                if (block.shapes[block.currentShape][y][x] != 0) {
                    grid[block.y + y][block.x + x] = block.type.ordinal + 10
                }
            }
        }
    }
    fun spawnBlock(block: Block): Boolean {
        if (!isValidMove(block))
            return false
        this.drawBlock(block)
        return true
    }
    fun lockBlock() {
        for(y in 0 until 20) {
            for(x in 0 until 10) {
                if (grid[y][x] > 10)
                    grid[y][x] -= 10
            }
        }
    }
}



