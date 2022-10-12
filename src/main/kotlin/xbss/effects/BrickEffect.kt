package xbss.effects

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.Effect
import com.almasb.fxgl.dsl.texture
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.getComponent
import javafx.util.Duration
import xbss.components.SquareComponent
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-02-11:41
 * @version  1.0
 * @describe
 */
class BrickEffect:Effect(Duration.seconds(0.2)) {
    override fun onEnd(entity: Entity) {
        entity.viewComponent.children[0].translateY = 0.0
        if (entity.type == GameType.WHAT){
            val itemType = entity.getString("type")
            if (itemType != "no"){
                FXGL.spawn("item", SpawnData(entity.center.subtract(37.0/2,37.0/1.7)).put("type",itemType))
            }
        }
    }

    override fun onStart(entity: Entity) {
        if(entity.type==GameType.WHAT){
            entity.viewComponent.clearChildren()
            entity.viewComponent.addChild(texture("map/nowhat.png"))
        }
        entity.viewComponent.children[0].translateY = -10.0
    }
}