package xbss.components

import com.almasb.fxgl.core.math.FXGLMath
import com.almasb.fxgl.core.util.LazyValue
import com.almasb.fxgl.dsl.FXGL
import com.almasb.fxgl.dsl.components.EffectComponent
import com.almasb.fxgl.entity.SpawnData
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.physics.PhysicsComponent
import com.almasb.fxgl.texture.AnimatedTexture
import com.almasb.fxgl.texture.AnimationChannel
import com.almasb.fxgl.time.LocalTimer
import javafx.geometry.Point2D
import javafx.scene.image.Image
import javafx.util.Duration
import xbss.Config
import xbss.gameEnum.Direction
import xbss.gameEnum.GameType


/**
 * @author  Xbss
 * @create 2022-09-30-22:09
 * @version  1.0
 * @describe
 */
class MaLiComponent:Component() {
    private val physics  = PhysicsComponent()
    private var jumps = 2
    private var moveIng = false
    private var distance = 0.0
    var moveDir = Direction.RIGHT_DOWN
    private var maLiMoveSpeed = Config.MALI_MOVE_SPEED
    private var nowMoveSpeed = 0.0
    private var isPlayIng = false
    var isBig =false
    var i =0
    var position =Point2D(0.0,0.0)
    private val effectComponent = EffectComponent()
    private val blockAllGroup = LazyValue{ FXGL.getGameWorld()
        .getGroup(GameType.ENEMY,GameType.PLAYER,GameType.BRICK,GameType.BORDER,GameType.STONE,GameType.SEA) }
    private val blockGroup = LazyValue{ FXGL.getGameWorld()
        .getGroup(GameType.ENEMY,GameType.PLAYER,GameType.BRICK,GameType.BORDER,GameType.STONE) }
    private lateinit var shootTimer: LocalTimer
    private val runChannel =    AnimationChannel(ArrayList<Image>() .apply {
        add(FXGL.image("role/stand_R.png"))
        add(FXGL.image("role/run_R.png"))
    }
        , Duration.seconds(0.2),2)
    private val jumpChannel =    AnimationChannel(ArrayList<Image>() .apply {
        add(FXGL.image("role/stand_R.png"))
        add(FXGL.image("role/jump.png"))
    }
        , Duration.seconds(0.6),2)
    private val texture  = AnimatedTexture(runChannel)
//    private val texture = AnimatedTexture(
//        AnimationChannel(ArrayList<Image>() .apply {
//            add(FXGL.image("role/stand_R.png"))
//            add(FXGL.image("role/run_R.png"))
//        }
//            , Duration.seconds(0.2),2)
//    )

    override fun onAdded() {
        shootTimer = FXGL.newLocalTimer()
        entity.viewComponent.addChild(texture)
        physics.onGroundProperty().addListener{_,_,newValue->
            if(newValue)
                jumps=2
        }
    }
    override fun onUpdate(tpf: Double) {
        moveIng = false //每一帧设置为移动中为false
        distance = tpf*nowMoveSpeed
        if(physics.isMovingX){
            if (position.x==entity.position.x) {
//                println("之前x的位置 :${position.x}  现在 ${entity.position.x}  堵住了${i++}")
//                handleBlock()
            }
            position = entity.position
//            println("现在的x${physics.velocityX}  ${i++}")
//            println("现在的任我游x是${entity.position.x}")
            if (!isPlayIng&&physics.isOnGround){
                texture.loopAnimationChannel(runChannel)
                isPlayIng =true
            }
        }else if (physics.isMovingY){
            if (!isPlayIng){
                texture.loopAnimationChannel(jumpChannel)
                isPlayIng =true
            }
        }else{
            texture.stop()
            isPlayIng =false
//            println("没有动弹${i++}")
//            texture.stop()
//            isPlayIng =false
        }


//        physics.velocityX*=0.7
//        if((FXGLMath.abs(physics.velocityX)<Config.MALI_MOVE_SPEED*0.5)&&!physics.isMovingY ){
//            physics.velocityX =0.0
//            texture.stop()
//            isPlayIng =false
//        }

//        nowMoveSpeed *=0.9
//        if (nowMoveSpeed<maLiMoveSpeed*0.5){
//            nowMoveSpeed=0.0
//            texture.stop()
//            isPlayIng=false
//        }
    }
    fun moveUp(){
        if (jumps == 0)
            return;
        physics.velocityY = -450.0;
        jumps--;
    }
    fun moveDown(){
        if (moveIng)
            return
        moveIng = true
        nowMoveSpeed = maLiMoveSpeed.toDouble()
//        moveDir = Direction.DOWN
        entity.rotation = 180.0
    }
    fun moveLeft(){
        if (moveIng)
            return
        moveIng = true
        entity.scaleX = -1.0
        nowMoveSpeed = maLiMoveSpeed.toDouble()
        moveDir = Direction.LEFT_DOWN
        physics.velocityX=-1*Config.MALI_MOVE_SPEED.toDouble()
    }
    fun moveRight(){
//        texture.loop()
        if (moveIng)
            return
        moveIng = true
        entity.scaleX = 1.0
        nowMoveSpeed = maLiMoveSpeed.toDouble()
        moveDir = Direction.RIGHT_DOWN
        physics.velocityX=Config.MALI_MOVE_SPEED.toDouble()
    }
    fun shoot(){
        if (shootTimer.elapsed(Config.SHOOT_DELAY)){
            FXGL.spawn("bullet", SpawnData(entity.center.subtract(-10.0,10/2.0))
                .put("dir",moveDir.vector)
                .put("high",1000.0)) //这里给子弹一个飞不到的高度，因为初始发射总是向下，不需要判断什么时候向上，只有在撞击时才向上
//                .put("bulletOwner",entity.type))
            shootTimer.capture()
        }
    }
    private fun handleBlock(){
        println("调用了解决方法${i++}")
        physics.velocityY = -14.0
    }
    private fun move(){
        val len = distance.toInt()
        for (i in 0 until  len){
            entity.translate(moveDir.vector.x,moveDir.vector.y)
        }
//        var isCollision = false
//        val hasEffect = effectComponent.hasEffect(ShipEffect::class.java)
//        val blockList = if(hasEffect)(blockGroup.get().entitiesCopy as ArrayList<Entity>).apply { remove(entity)  } else (blockAllGroup.get().entitiesCopy as ArrayList<Entity>).apply { remove(entity)  }
//        for (i in 0 until  len){
//            entity.translate(moveDir.vector.x,moveDir.vector.y)
//            for (j in blockList.indices){
//                if (entity.isColliding(blockList[j])){
//                    isCollision = true
//                    break
//                }
//            }
//            if (isCollision){
//                entity.translate(-moveDir.vector.x,-moveDir.vector.y)
//                break
//            }
//        }
    }

    fun stop() {
        physics.velocityX = 0.0
    }
}