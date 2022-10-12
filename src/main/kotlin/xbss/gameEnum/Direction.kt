package xbss.gameEnum

import javafx.geometry.Point2D
import java.util.Vector

/**
 * @author  Xbss
 * @create 2022-09-29-12:36
 * @version  1.0
 * @describe
 */
enum class Direction(val vector: Point2D) {
    RIGHT_UP(Point2D(1.0,-1.0)),
    RIGHT_DOWN(Point2D(1.0,1.0)),
    LEFT_UP(Point2D(-1.0,-1.0)),
    LEFT_DOWN(Point2D(-1.0,1.0))
}