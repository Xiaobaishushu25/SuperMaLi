package xbss.components

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.physics.PhysicsComponent
import javafx.geometry.Rectangle2D
import xbss.Config

class NpcComponent : Component() {
    private var isOnScreen = false
    private var isMoving = false
    private val physics = PhysicsComponent()
    override fun onUpdate(tpf: Double) {
        val visibleArea: Rectangle2D = FXGL.getGameScene().viewport.visibleArea
        //判断当前实体是否在屏幕内
        if (!isOnScreen&&visibleArea.contains(entity.position)){
            isOnScreen = true
        }
        //如果在屏幕内且没有移动的话，就给他一个速度
        if (isOnScreen&& !isMoving){
            physics.velocityX = -Config.NPC_MOVE_SPEED.toDouble()
            isMoving = true
        }

    }
}
