package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.EffectComponent
import com.almasb.fxgl.dsl.spawn
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import xbss.effects.NpcEffect
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-05-22:34
 * @version  1.0
 * @describe
 */
class BulletAndNpc:CollisionHandler(GameType.BULLET,GameType.NPC) {
    override fun onCollisionBegin(bullet: Entity?, npc: Entity?) {
        bullet!!.removeFromWorld()
        when(npc!!.type){
            GameType.NPC ->  npc.getComponent(EffectComponent::class.java).startEffect(NpcEffect())
            GameType.PIPE -> spawn("explode",bullet.position)
        }
//        npc!!.getComponent(EffectComponent::class.java).startEffect(NpcEffect())
    }
}