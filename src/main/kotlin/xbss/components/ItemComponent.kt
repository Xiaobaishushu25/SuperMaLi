package xbss.components

import com.almasb.fxgl.animation.Animation
import com.almasb.fxgl.animation.AnimationBuilder
import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import com.almasb.fxgl.physics.PhysicsComponent
import com.almasb.fxgl.physics.box2d.dynamics.BodyType
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.geometry.Point2D
import javafx.util.Duration
import com.almasb.fxgl.dsl.animationBuilder
import com.almasb.fxgl.entity.SpawnData
import xbss.Config


class ItemComponent : Component() {
    override fun onAdded() {
        entity.zIndex = -1
        val type = entity.getString("type")
        entity.viewComponent.addChild(texture("map/item/$type.png"))
        val node = entity.viewComponent.children[0]
        val keyValue = KeyValue(node.translateYProperty(), node.layoutY -35.0)
        val keyFrame = KeyFrame(Duration.seconds(1.2), keyValue)
        val timeline = Timeline(keyFrame)
        timeline.play()
        timeline.setOnFinished {
            entity.zIndex = 0
            when(type){
                "mushroom" ->{
                    val mushroom = FXGL.spawn("itemWithPhy", SpawnData(entity.position.subtract(0.0,37.0)).put("type", "mushroom"))
                    entity.removeFromWorld()
                    mushroom.getComponent(PhysicsComponent::class.java).velocityX = Config.ITEM_MOVE_SPEED.toDouble()
                }
            }
//            val physicsComponent = PhysicsComponent().apply {
//                setBodyType(BodyType.DYNAMIC)
//                addGroundSensor(HitBox("GROUND_SENSOR",  BoundingShape.box(37.0,37.0)))
//                setFixtureDef(FixtureDef().friction(0.0f))
//            }
//            entity.addComponent(physicsComponent)
//            entity.getComponent(PhysicsComponent::class.java).setOnPhysicsInitialized {
//                physicsComponent.velocityX = 60.0
//            }
        }
    }
}
