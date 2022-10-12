package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.PhysicsComponent
import xbss.Config
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-05-23:58
 * @version  1.0
 * @describe
 */
class PlayerAndTrigger:CollisionHandler(GameType.PLAYER,GameType.TRIGGER) {
    override fun onCollisionBegin(a: Entity?, trigger: Entity?) {
//        val id = trigger!!.getInt("id")
//        FXGL.getGameWorld().getEntitiesByType(GameType.NPC).apply {
//            for (s in this){
//                if (s.getInt("id")==id){
//                    s.getComponent(PhysicsComponent::class.java).velocityX = -Config.NPC_MOVE_SPEED.toDouble()
//                }
//            }
//        }
    }
}