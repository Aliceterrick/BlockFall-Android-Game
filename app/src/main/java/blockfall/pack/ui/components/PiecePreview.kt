package blockfall.pack.ui.components
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import blockfall.pack.ui.theme.blockColors

@Composable
fun PiecePreview(
    pieceGrid: Array<Array<Int>>,
    modifier: Modifier = Modifier
) {
    Canvas( modifier = modifier
            .background(Color(0xFF272121))
            .border(2.dp, Color.DarkGray )) {
        if (pieceGrid.isEmpty()) return@Canvas

        val rows = pieceGrid.size
        val cols = pieceGrid[0].size

        val cellSize = minOf(size.width / cols, size.height / rows)

        val horizontalPadding = (size.width - cellSize * cols) / 2f
        val verticalPadding = (size.height - cellSize * rows) / 2f

        val spacing = 2.dp.toPx()

        pieceGrid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cellValue ->
                val color = blockColors[cellValue]

                    drawRect(
                        color = color,
                        topLeft = Offset(
                            x = horizontalPadding + x * cellSize + (spacing / 2f),
                            y = verticalPadding + y * cellSize + (spacing / 2f)
                        ),
                        size = Size(cellSize - spacing, cellSize - spacing)
                    )
            }
        }
    }
}