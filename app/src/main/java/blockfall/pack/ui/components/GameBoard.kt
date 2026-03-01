package blockfall.pack.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import blockfall.pack.ui.theme.blockColors

@Composable
fun GameBoard(
    grid: Array<IntArray>,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .background(Color(0xFF272121))
    ) {
        if (grid.isEmpty() || grid[0].isEmpty()) return@Canvas

        val cellSize = minOf(size.width / grid[0].size, size.height / grid.size)
        val horizontalPadding = (size.width - cellSize * grid[0].size) / 2f
        val verticalPadding = (size.height - cellSize * grid.size) / 2f

        val spacing = 2.dp.toPx()

        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cellValue ->
                var idx= cellValue

                if (idx > 10)
                    idx -= 10
                val color = blockColors[idx]

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
