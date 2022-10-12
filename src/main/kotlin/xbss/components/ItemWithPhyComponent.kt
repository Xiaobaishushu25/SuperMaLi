package xbss.components

import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.physics.PhysicsComponent
import javafx.geometry.Point2D
import xbss.Config

class ItemWithPhyComponent : Component() {
    private val physics =PhysicsComponent()
    var position = Point2D(0.0,0.0)
    override fun onAdded() {
        val type = entity.getString("type")
        entity.viewComponent.addChild(texture("map/item/$type.png"))
    }
}
