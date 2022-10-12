package xbss

import com.almasb.fxgl.ui.Position
import javafx.geometry.Point2D
import javafx.util.Duration

/**
 * @author  Xbss
 * @create 2022-09-19-20:05
 * @version  1.0
 * @describe
 */
object Config {
    const val CELL_SIZE = 37
    const val MALI_MOVE_SPEED = 180
    const val ITEM_MOVE_SPEED = 60
    const val NPC_MOVE_SPEED = 50
    const val BULLET_SPEED = 900
    val SHOOT_DELAY: Duration = Duration.seconds(0.35)
    val EXPLODE_TIME: Duration = Duration.seconds(0.35)
    const val MAX_HP = 5
    const val TANK_MAX_LEVEL= 5
    val FREEZE_TIME: Duration = Duration.seconds(10.0)
    val HELMET_TIME: Duration = Duration.seconds(15.0)
    val spawnEnemyPosition = arrayListOf<Point2D>(Point2D(30.0,30.0),Point2D(330.0,30.0),Point2D(580.0,30.0))
}