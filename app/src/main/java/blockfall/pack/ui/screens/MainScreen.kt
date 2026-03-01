package blockfall.pack.ui.screens

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
import androidx.navigation.NavController
import blockfall.pack.ui.theme.NeonButton

@Composable
fun MainMenu(navController: NavController, modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxSize()) {

        MenuBackground()

        MenuContent(navController = navController)
    }
}
@Composable
fun MenuContent(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text("BlockFall")
        Spacer(modifier = Modifier.height(100.dp))
        NeonButton(text= "New Game", onClick = { navController.navigate("game") })

        Spacer(modifier = Modifier.height(16.dp))

        NeonButton(text= "Stats", onClick = { navController.navigate("stats") })

        Spacer(modifier = Modifier.height(16.dp))

        NeonButton (text = "Settings", onClick = { navController.navigate("settings") })
    }
}