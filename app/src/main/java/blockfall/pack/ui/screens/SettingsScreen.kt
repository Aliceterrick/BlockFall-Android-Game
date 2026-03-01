package blockfall.pack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import blockfall.pack.ui.theme.NeonButton
import blockfall.pack.ui.theme.NeonToggle

@Composable
fun SettingsScreen(
    navController: NavController,
    isMusicOn: Boolean,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    onMusicToggle: (Boolean) -> Unit,

    ) {
    Box(Modifier.fillMaxSize()) {

        MenuBackground()

        SettingsMenuContent(navController = navController, isMusicOn = isMusicOn, isDarkTheme = isDarkTheme, onThemeToggle = onThemeToggle, onMusicToggle = onMusicToggle)
    }
}

@Composable
fun SettingsMenuContent(
    navController: NavController,
    isMusicOn: Boolean,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    onMusicToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dark Mode", fontSize= 40.sp)
        NeonToggle(
            checked = isDarkTheme,
            onToggle = onThemeToggle
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text("Music and Sounds Effects", fontSize = 40.sp)
        NeonToggle(
            checked = isMusicOn,
            onToggle = onMusicToggle
        )
        Spacer(modifier = Modifier.height(32.dp))
        NeonButton(text= "Main menu", onClick = { navController.popBackStack() })
    }
}