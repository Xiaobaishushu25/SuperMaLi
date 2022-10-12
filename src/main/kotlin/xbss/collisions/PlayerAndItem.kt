package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.physics.CollisionHandler
import javafx.scene.transform.Scale
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-03-19:48
 * @version  1.0
 * @describe
 */
class PlayerAndItem:CollisionHandler(GameType.PLAYER,GameType.ITEM) {
    override fun onCollisionBegin(player: Entity?, item: Entity?) {
        item!!.removeFromWorld()
        when(item.getString("type")){
            "mushroom" -> {
                FXGL.play("bigger.wav")
                val view = player!!.viewComponent.children[0]
                val scale = Scale(2.0, 2.0)
                view.transforms.addAll(scale)
            }
        }
    }
}