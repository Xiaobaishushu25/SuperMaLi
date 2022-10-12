package xbss.collisions

import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.ProjectileComponent
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.physics.CollisionHandler
import xbss.Config
import xbss.components.BulletComponent
import xbss.gameEnum.Direction
import xbss.gameEnum.GameType

/**
 * @author  Xbss
 * @create 2022-10-05-19:32
 * @version  1.0
 * @describe
 */
class BulletAndGround:CollisionHandler(GameType.BULLET,GameType.GROUND) {
    override fun onCollisionBegin(bullet: Entity?, ground: Entity?) {
        val direction = bullet!!.getComponent(ProjectileComponent::class.java).direction
        if(direction.x>0){
            bullet.getComponent(ProjectileComponent::class.java).direction = Direction.RIGHT_UP.vector
        }else{
            bullet.getComponent(ProjectileComponent::class.java).direction = Direction.LEFT_UP.vector
        }
        //设定一个高度，子弹飞到这个高度就要转向下
        bullet.getComponent(BulletComponent::class.java).high = bullet.position.y-Config.CELL_SIZE+10
//        FXGL.spawn("bullet", SpawnData(bullet!!.position).put("dir",Direction.UP.vector).put("high",bullet.position.y+50.0))
//        bullet.removeFromWorld()
    }
}