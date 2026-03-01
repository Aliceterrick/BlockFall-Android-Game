package blockfall.pack.presentation

sealed class GameAction {
    object MoveLeft: GameAction()
    object MoveRight: GameAction()
    object MoveDown: GameAction()
    object Rotate: GameAction()
    object Pause: GameAction()
    object Resume: GameAction()
    object ComboFinished: GameAction()
    object Hold: GameAction()
    object Drop: GameAction()
}