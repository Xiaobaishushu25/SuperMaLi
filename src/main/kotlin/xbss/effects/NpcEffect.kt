package xbss.effects

import com.almasb.fxgl.dsl.components.Effect
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.Entity
import javafx.util.Duration

/**
 * @author  Xbss
 * @create 2022-10-05-16:23
 * @version  1.0
 * @describe
 */
class NpcEffect:Effect(Duration.seconds(0.3)) {
    override fun onEnd(entity: Entity) {
        entity.removeFromWorld()
    }

    override fun onStart(entity: Entity) {
        entity.viewComponent.clearChildren()
        val texture = texture("role/npc/fungus3.png")
        texture.translateY = 10.0
//        entity.viewComponent.addChild(texture("role/npc/fungus3.png"))
        entity.viewComponent.addChild(texture)
    }
}