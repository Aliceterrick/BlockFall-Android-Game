package blockfall.pack.logic.model

enum class BlockType{ V, I, O, T, S, Z, J, L, Total }

data class Block (
    var type: BlockType,
    val shapes: Array<Array<Array<Int>>>,
    var y: Int,
    var x: Int,
    var currentShape: Int,
)

var blocks = arrayOf(
    Block(
        type = BlockType.V,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,
    ),
    Block(
        type = BlockType.I,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 1, 0),
                arrayOf(0, 0, 1, 0),
                arrayOf(0, 0, 1, 0),
                arrayOf(0, 0, 1, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(1, 1, 1, 1),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 1, 0),
                arrayOf(0, 0, 1, 0),
                arrayOf(0, 0, 1, 0),
                arrayOf(0, 0, 1, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(1, 1, 1, 1),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,
    ),
    Block(
        type = BlockType.O,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 2, 2, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,

    ),
    Block(
        type = BlockType.T,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 3, 0),
                arrayOf(0, 3, 3, 3),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 3, 0),
                arrayOf(0, 0, 3, 3),
                arrayOf(0, 0, 3, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 3, 3, 3),
                arrayOf(0, 0, 3, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 3, 0),
                arrayOf(0, 3, 3, 0),
                arrayOf(0, 0, 3, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,

    ),
    Block(
        type = BlockType.S,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 4, 4),
                arrayOf(0, 4, 4, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 4, 0, 0),
                arrayOf(0, 4, 4, 0),
                arrayOf(0, 0, 4, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 4, 4),
                arrayOf(0, 4, 4, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 4, 0, 0),
                arrayOf(0, 4, 4, 0),
                arrayOf(0, 0, 4, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,

    ),
    Block(
        type = BlockType.Z,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(5, 5, 0, 0),
                arrayOf(0, 5, 5, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 5, 0),
                arrayOf(0, 5, 5, 0),
                arrayOf(0, 5, 0, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(5, 5, 0, 0),
                arrayOf(0, 5, 5, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 5, 0),
                arrayOf(0, 5, 5, 0),
                arrayOf(0, 5, 0, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,

    ),
    Block(
        type = BlockType.J,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 6, 0),
                arrayOf(0, 0, 6, 0),
                arrayOf(0, 6, 6, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 6, 0, 0),
                arrayOf(0, 6, 6, 6),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 0, 6, 6),
                arrayOf(0, 0, 6, 0),
                arrayOf(0, 0, 6, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 6, 6, 6),
                arrayOf(0, 0, 0, 6),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,

    ),
    Block(
        type = BlockType.L,
        shapes = arrayOf(
            arrayOf(
                arrayOf(0, 0, 7, 0),
                arrayOf(0, 0, 7, 0),
                arrayOf(0, 0, 7, 7),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 7, 7, 7),
                arrayOf(0, 7, 0, 0),
                arrayOf(0, 0, 0, 0),
            ),
            arrayOf(
                arrayOf(0, 7, 7, 0),
                arrayOf(0, 0, 7, 0),
                arrayOf(0, 0, 7, 0),
                arrayOf(0, 0, 0, 0)
            ),
            arrayOf(
                arrayOf(0, 0, 0, 7),
                arrayOf(0, 7, 7, 7),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
            )
        ),
        y = 0,
        x = 2,
        currentShape = 0,
    ),

)