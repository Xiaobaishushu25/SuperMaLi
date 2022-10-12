package xbss.components

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.ProjectileComponent
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.entity.getComponent
import xbss.gameEnum.Direction

/**
 * @author  Xbss
 * @create 2022-10-05-21:36
 * @version  1.0
 * @describe
 */
class BulletComponent:Component() {
    private var pro = ProjectileComponent()
    var high = 0.0
    override fun onAdded() {
        high = entity.getDouble("high")
    }

    override fun onUpdate(tpf: Double) {
//        println("子弹的高度 ${entity.position.y}  预订高度$high")
//        if (pro.direction.x*Direction.UP.vector.x>0&&pro.direction.y*Direction.UP.vector.y>0){
        if (pro.direction.y<0){//小于0说明是向上飞的，要限制高度
            if (entity.position.y < high){
                pro.direction = if (pro.direction.x>0) Direction.RIGHT_DOWN.vector else Direction.LEFT_DOWN.vector
//            entity.getComponent(ProjectileComponent::class.java).direction = Direction.DOWN.vector
//                high = 1000.0
            }
        }
    }
}