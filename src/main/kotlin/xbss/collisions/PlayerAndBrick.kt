package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.Effect
import com.almasb.fxgl.dsl.components.EffectComponent
import com.almasb.fxgl.dsl.components.LiftComponent
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.getComponent
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.HitBox
import javafx.animation.KeyFrame
import javafx.util.Duration
import xbss.components.MaLiComponent
import xbss.components.SquareComponent
import xbss.effects.BrickEffect
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-02-0:04
 * @version  1.0
 * @describe
 */
class PlayerAndBrick:CollisionHandler(GameType.PLAYER,GameType.BRICK) {
    override fun onHitBoxTrigger(player: Entity?, brick: Entity?, boxA: HitBox?, boxB: HitBox?) {
        val squareComponent = brick!!.getComponent(SquareComponent::class.java)
        if (boxA!!.name=="head"&&boxB!!.name=="bottom"){
            if (player!!.getComponent(MaLiComponent::class.java).isBig){
                FXGL.play("pushBreakBrick.wav")
                brick.removeFromWorld()
            }else{
                if (squareComponent.money>0){
                    squareComponent.minus()
                    FXGL.play("coin.wav")
                }
                FXGL.play("hit.wav")
                brick.getComponent(EffectComponent::class.java).startEffect(BrickEffect())
            }
        }
    }

//    override fun onCollisionBegin(player: Entity?, brick: Entity?) {
//        if (player!!.getComponent(MaLiComponent::class.java).isBig){
//            FXGL.play("pushBreakBrick.wav")
//            brick!!.removeFromWorld()
//        }else{
//            println("进来了")
//            val liftComponent = LiftComponent()
//            liftComponent.isGoingUp = true
//            liftComponent.yAxisDistanceDuration(-10.0, Duration.seconds(1.0))
//            brick!!.components.add(liftComponent)
//            brick.getComponent(EffectComponent::class.java).startEffect(BrickEffect())
//        }
//    }
}