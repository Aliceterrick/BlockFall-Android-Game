package blockfall.pack.ui.screens

import MusicManager
import kotlinx.coroutines.delay
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.navigation.NavController
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import blockfall.pack.audio.GameSoundManager
import blockfall.pack.presentation.GameAction
import blockfall.pack.presentation.StatsViewModel
import blockfall.pack.ui.theme.NeonPink
import blockfall.pack.ui.theme.NeonButton
import blockfall.pack.ui.components.GameBoard
import blockfall.pack.presentation.GameViewModel
import blockfall.pack.ui.components.PiecePreview

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
    statsViewModel: StatsViewModel = viewModel(),
    context: Context = LocalContext.current,
    navController: NavController,
    isMusicOn: Boolean
) {
    val state by gameViewModel.state.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val musicManager = remember { MusicManager(context) }
    val soundManager = remember { GameSoundManager(context) }

    if (isMusicOn) {
        DisposableEffect(lifecycleOwner) {

            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> musicManager.play()
                    Lifecycle.Event.ON_STOP -> musicManager.pause()
                    Lifecycle.Event.ON_DESTROY -> musicManager.release()
                    else -> {}
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
                musicManager.release()
            }
        }
    }
    LaunchedEffect(state.pause, state.gameOver) {
        if (isMusicOn) {
            when {
                state.gameOver -> musicManager.stop()
                state.pause -> musicManager.pause()
                else -> musicManager.play()
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GameTouchLayer (gameViewModel) { GameContent(gameViewModel = gameViewModel) }
        LaunchedEffect(state.gameOver) {
            if (state.gameOver) {
                if (isMusicOn)
                    soundManager.playEffect("gameOver")
                statsViewModel.onGameFinished(state.score)
            }
        }
        LaunchedEffect(state.combo) {
            if (state.combo && isMusicOn) {
                soundManager.playEffect("combo")
            }
        }
        LaunchedEffect(state.clearedLine) {
            if (state.clearedLine && isMusicOn) {
                soundManager.playEffect("clearedLine")
            }
        }
        LaunchedEffect(state.levelPassed) {
            if (state.levelPassed && isMusicOn) {
                soundManager.playEffect("levelPassed")
            }
        }
        if (state.gameOver) {
            Overlay(text =  "Game Over",
                buttonText = "Star New Game",
                onResume = { navController.navigate("game")
                { popUpTo("game") { inclusive = true }  } },
                onQuit = { navController.navigate("menu")
                { popUpTo("game") { inclusive = true } } }
            )
        }
        if (state.pause) {
            Overlay(text = "Pause",
                buttonText = "Resume",
                onResume = { gameViewModel.onAction(GameAction.Resume)},
                onQuit ={ navController.navigate("menu")
                { popUpTo("game") { inclusive = true } } }
            )
        }
        if (state.combo)
            AnimatedComboText(onFinished = {gameViewModel.onAction(GameAction.ComboFinished)})
    }
}

@Composable
fun GameTouchLayer(
    gameViewModel: GameViewModel,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                var accX = 0f
                var accY = 0f

                detectDragGestures(
                    onDragStart = { accX = 0f; accY = 0f },
                    onDragEnd = { accX = 0f; accY = 0f },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        accX += dragAmount.x
                        accY += dragAmount.y

                        val threshold = 40f

                        when {
                            accX > threshold -> {
                                gameViewModel.onAction(GameAction.MoveRight)
                                accX = 0f
                            }
                            accX < -threshold -> {
                                gameViewModel.onAction(GameAction.MoveLeft)
                                accX = 0f
                            }
                            accY > threshold -> {
                                gameViewModel.onAction(GameAction.MoveDown)
                                accY = 0f
                            }
                        }
                    }
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        gameViewModel.onAction(GameAction.Rotate)
                    }
                )
            }
    ) {
        content()
    }
}

@Composable
fun Overlay(text: String,
            buttonText: String,
            onResume: () -> Unit,
            onQuit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            NeonButton(text= buttonText, onClick = onResume)
            Spacer(modifier = Modifier.height(10.dp))
            NeonButton(text= "Main Menu", onClick = onQuit)
        }
    }
}

@Composable
fun AnimatedComboText(
    onFinished: () -> Unit
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        visible = false
        delay(300)
        onFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Text(
                text = "C-C-C-COOOOMBO!",
                color = NeonPink,
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun GameContent(modifier: Modifier = Modifier, gameViewModel: GameViewModel) {
    val state by gameViewModel.state.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("BlockFall")
        Row() {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Score: ${state.score}", fontSize= 40.sp)
            Spacer(modifier = Modifier.width(20.dp))
            Text("Level: ${state.level}", fontSize= 40.sp)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row() {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Hold", fontSize= 35.sp)

                        PiecePreview(
                            pieceGrid = state.holded.shapes[state.holded.currentShape],
                            modifier = Modifier
                                .size(80.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Next", fontSize= 35.sp)

                        PiecePreview(
                            pieceGrid = state.nextBlock.shapes[state.nextBlock.currentShape],
                            modifier = Modifier
                                .size(80.dp)
                        )
                    }
                }
                GameBoard(
                    grid = state.grid,
                    modifier = Modifier
                        .width(200.dp)
                        .aspectRatio(10f / 20f)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Button(onClick = { gameViewModel.onAction(GameAction.Hold) }) { Text("Hold", fontSize= 30.sp) }
                            Button(onClick = { gameViewModel.onAction(GameAction.Drop) }) { Text("Drop", fontSize= 30.sp) }
                        }
                        Button(onClick = { gameViewModel.onAction(GameAction.Pause) }) { Text( "❚❚", fontSize= 30.sp) }

                    }
                }

            }
        }
    }
}
