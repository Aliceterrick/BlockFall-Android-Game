package blockfall.pack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import blockfall.pack.presentation.StatsViewModel
import blockfall.pack.ui.theme.NeonBlue
import blockfall.pack.ui.theme.NeonButton
import blockfall.pack.ui.theme.NeonGreen
import blockfall.pack.ui.theme.NeonPink

@Composable
fun StatsScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {

        MenuBackground()

        StatsMenuContent(navController = navController)
    }
}

@Composable
fun StatsMenuContent(navController: NavController, viewModel: StatsViewModel = viewModel()) {
    val stats by viewModel.stats.collectAsState()

    val gamesPlayed = stats.first
    val bestScore = stats.second

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Stats",
            fontSize = 60.sp,
            color = NeonBlue,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Games played : $gamesPlayed",
            fontSize = 50.sp,
            color = NeonPink
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Best score : $bestScore",
            fontSize = 50.sp,
            color = NeonGreen
        )
        Spacer(modifier = Modifier.height(32.dp))
        NeonButton(text = "Reset", onClick = { viewModel.resetStats() }, fontSize= 50.sp)
        Spacer(Modifier.height(20.dp))
        NeonButton(text= "Main menu", onClick = { navController.popBackStack() }, fontSize= 50.sp)
    }
}