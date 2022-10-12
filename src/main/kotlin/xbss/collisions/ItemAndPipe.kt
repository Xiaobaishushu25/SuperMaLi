package xbss.collisions

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.getComponent
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.PhysicsComponent
import xbss.Config
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-04-23:38
 * @version  1.0
 * @describe
 */
class ItemAndPipe:CollisionHandler(GameType.ITEM,GameType.PIPE) {
    override fun onCollisionBegin(item: Entity?, pipe: Entity?) {
        //撞到管道后变为原来的反方向运动
        val velocityX = item!!.getComponent(PhysicsComponent::class.java).velocityX
        item.getComponent(PhysicsComponent::class.java).velocityX=-velocityX
    }
}