package blockfall.pack.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import blockfall.pack.logic.model.menuGrid
import blockfall.pack.ui.theme.blockColors

@Composable
fun MenuBackground() {
    val bg = MaterialTheme.colorScheme.background
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val spacing = 2.dp.toPx()
        val cellSize = minOf(size.width / menuGrid[0].size, size.height / menuGrid.size)
        val horizontalPadding = (size.width - cellSize * menuGrid[0].size) / 2f
        val verticalPadding = (size.height - cellSize * menuGrid.size) / 2f
        menuGrid.forEachIndexed { y, row ->
            row.forEachIndexed { x, cellValue ->
                var color = blockColors[cellValue]
                if (cellValue == 0)
                    color = bg

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