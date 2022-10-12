package xbss.collisions

import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.PhysicsComponent
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-05-23:14
 * @version  1.0
 * @describe :npc撞到管子改向
 */
class NpcAndPipe:CollisionHandler(GameType.NPC,GameType.PIPE) {
    override fun onCollisionBegin(npc: Entity?, pipe: Entity?) {
        npc!!.getComponent(PhysicsComponent::class.java).velocityX = -npc.getComponent(PhysicsComponent::class.java).velocityX
    }
}