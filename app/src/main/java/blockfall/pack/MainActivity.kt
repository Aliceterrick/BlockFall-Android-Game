package blockfall.pack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import blockfall.pack.ui.theme.BlockFallTheme
import blockfall.pack.ui.screens.GameScreen
import blockfall.pack.ui.screens.MainMenu
import blockfall.pack.ui.screens.StatsScreen
import blockfall.pack.ui.screens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme = remember { mutableStateOf(true) }
            val isMusicOn = remember { mutableStateOf(true) }
            BlockFallTheme (darkTheme = isDarkTheme.value) {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "menu",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("menu") {
                            MainMenu(navController = navController)
                        }
                        composable("game") {
                            GameScreen(navController = navController, isMusicOn = isMusicOn.value)
                        }
                        composable("stats") {
                            StatsScreen(navController = navController)
                        }
                        composable("settings") {
                            SettingsScreen(
                                navController = navController,
                                isDarkTheme = isDarkTheme.value,
                                onThemeToggle = { isDarkTheme.value = it },
                                isMusicOn = isMusicOn.value,
                                onMusicToggle = { isMusicOn.value = it })

                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    BlockFallTheme {
    }
}