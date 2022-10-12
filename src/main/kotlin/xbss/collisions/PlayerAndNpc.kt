package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.EffectComponent
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.getComponent
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.HitBox
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import javafx.util.Duration
import xbss.effects.NpcEffect
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-02-19:29
 * @version  1.0
 * @describe
 */
class PlayerAndNpc:CollisionHandler(GameType.PLAYER,GameType.NPC) {
    override fun onHitBoxTrigger(player: Entity?, npc: Entity?, boxA: HitBox?, boxB: HitBox?) {
        when(boxA!!.name){
            "foot" -> {
                FXGL.play("stampEnemy.wav")
                npc!!.getComponent(EffectComponent::class.java).startEffect(NpcEffect())
            }
            else -> {
                FXGL.play("die.wav")
            }
        }
    }
}