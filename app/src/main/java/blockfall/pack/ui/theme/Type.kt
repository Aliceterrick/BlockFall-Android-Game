package blockfall.pack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import blockfall.pack.R

// Set of Material typography styles to start with
val GameFont = FontFamily(Font(R.font.jersey10regular))

val blockColors = arrayOf( Color.Black, Color.Red, Color(0xFFFFD100), Color(0xFFFF8B00), Color(0xFF2E7500), Color(0xFFA500FF), Color.Blue, Color(0xFFFF0088) )
val Typography = Typography(
    bodyLarge = TextStyle(fontFamily = GameFont, fontSize = 70.sp),
    titleLarge = TextStyle(fontFamily = GameFont, fontSize = 100.sp),
    labelLarge = TextStyle(fontFamily = GameFont, fontSize = 54.sp) // utilisé par les boutons
)

