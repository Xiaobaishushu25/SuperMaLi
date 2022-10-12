package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.EffectComponent
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.physics.CollisionHandler
import com.almasb.fxgl.physics.HitBox
import xbss.components.SquareComponent
import xbss.effects.BrickEffect
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-02-0:04
 * @version  1.0
 * @describe
 */
class PlayerAndWhat:CollisionHandler(GameType.PLAYER,GameType.WHAT) {
    override fun onHitBoxTrigger(player: Entity?, what: Entity?, boxA: HitBox?, boxB: HitBox?) {
        val squareComponent = what!!.getComponent(SquareComponent::class.java)
        val itemType = what.getString("type")
        if (boxA!!.name=="head"&&boxB!!.name=="bottom"){
                if (squareComponent.money>0){
                    squareComponent.minus()
                    FXGL.play("coin.wav")
                }
//                else if (itemType != "no"){
//                    FXGL.spawn("item", SpawnData(what.center.subtract(37.0/2,37.0/1.7)).put("type",itemType))
//                }
                what.getComponent(EffectComponent::class.java).startEffect(BrickEffect())
        }
//        if (boxA!!.name=="head"&&boxB!!.name=="bottom"){
//            what!!.getComponent(EffectComponent::class.java).startEffect(BrickEffect())
//            FXGL.spawn("item", SpawnData(what.center.subtract(37.0/2,37.0/1.4)).put("type","mushroom"))
//            FXGL.play("coin.wav")
//        }
//        if (boxA!!.name=="head"&&boxB!!.name=="bottom"){
//            if (player!!.getComponent(MaLiComponent::class.java).isBig){
//                FXGL.play("pushBreakBrick.wav")
//                brick!!.removeFromWorld()
//            }else{
//                val liftComponent = LiftComponent()
//                liftComponent.isGoingUp = true
//                liftComponent.yAxisDistanceDuration(-10.0, Duration.seconds(1.0))
//                brick!!.components.add(liftComponent)
//                brick.getComponent(EffectComponent::class.java).startEffect(BrickEffect())
//            }
//        }
    }
}